SELECT team_id, name FROM teams ORDER BY name;

SELECT match_day_id, day_number, played_on
FROM match_days
WHERE played_on BETWEEN :start_date AND :end_date
ORDER BY played_on ASC;

SELECT day_number, played_on
FROM match_days
WHERE day_number = :day_number
ORDER BY played_on ASC;

SELECT match_id, match_day_id, match_status_id
FROM matches
WHERE match_status_id IN (:first_status_id, :second_status_id)
ORDER BY match_id;

SELECT m.match_id, m.home_team_id, m.away_team_id, m.home_goals, m.away_goals
FROM matches AS m
JOIN match_days AS d ON d.match_day_id = m.match_day_id
JOIN championships AS c ON c.championship_id = d.championship_id
WHERE d.day_number = :day_number AND c.championship_id = :championship_id
ORDER BY m.match_id;

UPDATE vehicles
SET name = :name, brand = :brand
WHERE vehicle_id = :vehicle_id;
