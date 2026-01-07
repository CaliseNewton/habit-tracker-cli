package tracker;

import java.sql.*;

public class HabitsDAO {
    private final Connection connection;

    public HabitsDAO(Connection connection) {
        this.connection = connection;
    }

    public void setup() {
        String sql = """
                    CREATE TABLE IF NOT EXISTS habit_tracker (
                        id INTEGER NOT NULL PRIMARY KEY,
                        habit_name TEXT
                    )
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public void add(String name){
        String sql = """
                INSERT INTO habit_tracker (habit_name)
                VALUES (?)
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
        }
    }

    public void remove(String key){
        String sql = """
                DELETE FROM habit_tracker
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

    public void get() {
        String sql = """
                SELECT * FROM habit_tracker
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                System.out.print(rs.getInt("id") + ".  ");
                System.out.println("habit name: " + rs.getString("habit_name"));
            }
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
        }
    }
}

