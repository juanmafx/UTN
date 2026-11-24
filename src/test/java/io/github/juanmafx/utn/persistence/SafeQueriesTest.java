package io.github.juanmafx.utn.persistence;

import io.github.juanmafx.utn.testkit.Check;
import io.github.juanmafx.utn.testkit.Test;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SafeQueriesTest {
    @Test
    void allowsListedTable() {
        Check.equal("SELECT * FROM students", new SafeQueries(Set.of("students")).selectAllSql("students"));
    }

    @Test
    void rejectsUnlistedTable() {
        SafeQueries queries = new SafeQueries(Set.of("students"));
        Check.throwsType(IllegalArgumentException.class, () -> queries.selectAllSql("teachers"));
    }

    @Test
    void rejectsInjectionPayloads() {
        SafeQueries queries = new SafeQueries(Set.of("students"));
        for (String payload : List.of("students; DROP TABLE students", "students--", "\"students\"", "STUDENTS", "", " students")) {
            Check.throwsType(IllegalArgumentException.class, () -> queries.selectAllSql(payload));
        }
    }

    @Test
    void rejectsInvalidAllowlistEntry() {
        Check.throwsType(IllegalArgumentException.class, () -> new SafeQueries(Set.of("bad name")));
    }

    @Test
    void bindsParametersWithoutTouchingSqlText() throws SQLException {
        List<String> preparedSql = new ArrayList<>();
        List<Object> bound = new ArrayList<>();
        PreparedStatement statement = (PreparedStatement) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class<?>[] {PreparedStatement.class}, (proxy, method, args) -> {
                    if ("setObject".equals(method.getName())) {
                        bound.add(args[0]);
                        bound.add(args[1]);
                    }
                    return null;
                });
        Connection connection = (Connection) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class<?>[] {Connection.class}, (proxy, method, args) -> {
                    preparedSql.add((String) args[0]);
                    return statement;
                });
        String payload = "x' OR '1'='1";
        SafeQueries.prepare(connection, "SELECT name FROM students WHERE name = ?", List.of(payload));
        Check.equal(List.of("SELECT name FROM students WHERE name = ?"), preparedSql);
        Check.equal(List.of(1, payload), bound);
    }
}
