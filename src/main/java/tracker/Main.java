package tracker;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:habit_tracker.db";

        Connection conn = DriverManager.getConnection(url);
        HabitsDAO dao = new HabitsDAO(conn);
        dao.setup();

        System.out.println("Habit tracker.");

        InputHandler.handleInput(dao);

        System.out.println("Bye!");
    }
}
