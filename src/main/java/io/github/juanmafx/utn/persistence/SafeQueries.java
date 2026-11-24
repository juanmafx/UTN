package io.github.juanmafx.utn.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public final class SafeQueries {
    private static final Pattern IDENTIFIER = Pattern.compile("[a-z_][a-z0-9_]{0,62}");

    private final Set<String> allowedTables;

    public SafeQueries(Set<String> allowedTables) {
        allowedTables.forEach(SafeQueries::requireIdentifier);
        this.allowedTables = Set.copyOf(allowedTables);
    }

    public String selectAllSql(String table) {
        requireIdentifier(table);
        if (!allowedTables.contains(table)) {
            throw new IllegalArgumentException("table is not allowed");
        }
        return "SELECT * FROM " + table;
    }

    public static PreparedStatement prepare(Connection connection, String sql, List<?> parameters) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            for (int index = 0; index < parameters.size(); index++) {
                statement.setObject(index + 1, parameters.get(index));
            }
            return statement;
        } catch (SQLException | RuntimeException exception) {
            statement.close();
            throw exception;
        }
    }

    private static void requireIdentifier(String identifier) {
        if (identifier == null || !IDENTIFIER.matcher(identifier).matches()) {
            throw new IllegalArgumentException("invalid SQL identifier");
        }
    }
}
