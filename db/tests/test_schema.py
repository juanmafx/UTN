import json
import pathlib
import sqlite3
import sys
import unittest

DB_DIR = pathlib.Path(__file__).resolve().parents[1]
SCHEMA = (DB_DIR / "schema.sql").read_text(encoding="utf-8")
QUERIES = [q.strip() for q in (DB_DIR / "queries.sql").read_text(encoding="utf-8").split(";") if q.strip()]
EXPECTED_TABLES = {
    "bank_accounts", "brands", "championships", "document_types", "employees", "library_members",
    "match_days", "match_statuses", "matches", "people", "product_types", "products", "students",
    "teams", "vehicles",
}


class SchemaTest(unittest.TestCase):
    def setUp(self):
        self.db = sqlite3.connect(":memory:")
        self.db.execute("PRAGMA foreign_keys = ON")
        self.db.executescript(SCHEMA)
        self.db.executescript(
            """
            INSERT INTO document_types VALUES (1, 'ID');
            INSERT INTO people VALUES (1000, 1, 'Ada', 'Lovelace', '2000-01-01', 1.70);
            INSERT INTO championships VALUES (1, 'League'), (2, 'Cup');
            INSERT INTO teams VALUES (1, 'North'), (2, 'South'), (3, 'East');
            INSERT INTO match_statuses VALUES (3, 'Played'), (4, 'Suspended'), (5, 'Scheduled');
            INSERT INTO match_days VALUES (1, 1, 1, '2018-05-10'), (2, 2, 1, '2018-07-01');
            INSERT INTO matches VALUES (1, 1, 3, 1, 2, 2, 0), (2, 1, 4, 2, 3, NULL, NULL), (3, 2, 5, 1, 3, NULL, NULL);
            INSERT INTO vehicles VALUES (20, 'old', 'old', 'TAXI', 2010);
            """
        )

    def tearDown(self):
        self.db.close()

    def assert_rejected(self, sql):
        with self.assertRaises(sqlite3.IntegrityError):
            self.db.execute(sql)

    def run_query(self, index, **parameters):
        return self.db.execute(QUERIES[index], parameters).fetchall()

    def test_all_tables_exist(self):
        rows = self.db.execute("SELECT name FROM sqlite_master WHERE type = 'table'").fetchall()
        self.assertEqual(EXPECTED_TABLES, {row[0] for row in rows})

    def test_foreign_key_check_is_clean(self):
        self.assertEqual([], self.db.execute("PRAGMA foreign_key_check").fetchall())

    def test_person_requires_existing_document_type(self):
        self.assert_rejected("INSERT INTO people VALUES (2000, 99, 'A', 'B', '2000-01-01', 1.5)")

    def test_grade_scale_is_enforced(self):
        self.assert_rejected("INSERT INTO students VALUES (1, 1000, 11, 5, 5)")

    def test_valid_student_is_accepted(self):
        self.db.execute("INSERT INTO students VALUES (1, 1000, 10, 0, 5)")

    def test_person_cannot_be_two_students(self):
        self.db.execute("INSERT INTO students VALUES (1, 1000, 5, 5, 5)")
        self.assert_rejected("INSERT INTO students VALUES (2, 1000, 5, 5, 5)")

    def test_negative_balance_is_rejected(self):
        self.assert_rejected("INSERT INTO bank_accounts VALUES (1, -0.01, 'I')")

    def test_account_type_code_is_enforced(self):
        self.assert_rejected("INSERT INTO bank_accounts VALUES (1, 0, 'X')")

    def test_negative_price_is_rejected(self):
        self.db.execute("INSERT INTO brands VALUES ('B1', 'Brand')")
        self.db.execute("INSERT INTO product_types VALUES (1, 'Tool')")
        self.assert_rejected("INSERT INTO products VALUES (1, 1, 'B1', 'Hammer', -1, '2026-01-01')")

    def test_product_requires_existing_brand(self):
        self.db.execute("INSERT INTO product_types VALUES (1, 'Tool')")
        self.assert_rejected("INSERT INTO products VALUES (1, 1, 'NONE', 'Hammer', 1, '2026-01-01')")

    def test_vehicle_kind_is_enforced(self):
        self.assert_rejected("INSERT INTO vehicles VALUES (1, 'a', 'b', 'BOAT', 2000)")

    def test_team_cannot_play_itself(self):
        self.assert_rejected("INSERT INTO matches VALUES (9, 1, 3, 1, 1, 0, 0)")

    def test_negative_goals_are_rejected(self):
        self.assert_rejected("INSERT INTO matches VALUES (9, 1, 3, 1, 3, -1, 0)")

    def test_duplicate_day_number_in_championship_is_rejected(self):
        self.assert_rejected("INSERT INTO match_days VALUES (9, 1, 1, '2018-05-11')")

    def test_query_teams_sorted(self):
        self.assertEqual(["East", "North", "South"], [row[1] for row in self.run_query(0)])

    def test_query_match_days_in_range(self):
        rows = self.run_query(1, start_date="2018-05-01", end_date="2018-06-06")
        self.assertEqual([1], [row[0] for row in rows])

    def test_query_match_day_by_number(self):
        self.assertEqual(2, len(self.run_query(2, day_number=1)))

    def test_query_matches_by_status(self):
        rows = self.run_query(3, first_status_id=3, second_status_id=4)
        self.assertEqual([1, 2], [row[0] for row in rows])

    def test_query_matches_join_is_scoped_to_championship(self):
        rows = self.run_query(4, day_number=1, championship_id=1)
        self.assertEqual([1, 2], [row[0] for row in rows])

    def test_update_vehicle_binds_parameters(self):
        payload = "x'; DROP TABLE vehicles; --"
        self.db.execute(QUERIES[5], {"name": payload, "brand": "mw", "vehicle_id": 20})
        self.assertEqual((payload, "mw"), self.db.execute("SELECT name, brand FROM vehicles").fetchone())


def main():
    suite = unittest.defaultTestLoader.loadTestsFromTestCase(SchemaTest)
    result = unittest.TextTestRunner(verbosity=1).run(suite)
    failed = len(result.failures) + len(result.errors)
    if len(sys.argv) > 1:
        pathlib.Path(sys.argv[1]).write_text(
            json.dumps(
                {
                    "engine": "SQLite " + sqlite3.sqlite_version,
                    "tests": result.testsRun,
                    "passed": result.testsRun - failed,
                    "failed": failed,
                },
                indent=2,
            )
            + "\n",
            encoding="utf-8",
        )
    return 1 if failed else 0


if __name__ == "__main__":
    sys.exit(main())
