package ucu.edu.ua;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor

public class CachedDocument implements Document {

    private final String gcsPath;

    @Override
    public String parse() throws IOException, SQLException {

        String text = get();

        if (text == null) {

            text = new SmartDocument(gcsPath).parse();
            add(text);
        }

        return text;
    }

    private String get() throws SQLException {

        String select = "SELECT FROM DOCUMENT";

        try (Connection conn = getConnection();

            PreparedStatement selectStatement = conn.prepareStatement(select)) {
            selectStatement.setString(1, gcsPath);

            try (ResultSet result = selectStatement.executeQuery()) {
                if (result.next()) {
                    return result.getString("text");
                }
            }
        }
        return null;
    }

    private void add(String text) throws SQLException {

        String insert = "INSERT INTO";

        try (Connection conn = getConnection();

            PreparedStatement insertStatement = conn.prepareStatement(insert)) {
            insertStatement.setString(1, gcsPath);
            insertStatement.setString(2, text);
            insertStatement.executeUpdate();

        }

    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
    }
}
