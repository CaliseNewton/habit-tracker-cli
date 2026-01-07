package tracker;

import java.sql.*;

public class HabitsDAO {
    private final Connection connection;
    private final String url = "jdbc:sqlite:test.db";

    public HabitsDAO(Connection connection) {
        this.connection = connection;
    }

    public void setup() {
        String sql = """
                    CREATE TABLE IF NOT EXISTS test (
                        id INTEGER PRIMARY KEY,
                        name TEXT
                    )
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public void add(String key, String name){
        String sql = """
                INSERT INTO test (id, name)
                VALUES (?, ?)
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, key);
            stmt.setString(2, name);
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
        }
    }

    public void remove(String key){
        String sql = """
                DELETE FROM test
                WHERE id = ?
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, key);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}

