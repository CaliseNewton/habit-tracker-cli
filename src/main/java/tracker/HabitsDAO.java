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
                        habit_name TEXT,
                        counter INTEGER DEFAULT 0
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
                System.out.print("habit : " + rs.getString("habit_name"));
                System.out.print(" counter: " + rs.getString("counter"));
                System.out.println("\n");
            }
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
        }
    }

    //TODO make generic update here and move 'increment' logic to higher layer
    public void incrementCounter(String id){
        String sql = """
                UPDATE habit_tracker
                SET counter = counter + 1
                WHERE id = ?
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
        }
    }
}

