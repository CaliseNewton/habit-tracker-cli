package tracker;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:habit_tracker.db";

        Connection conn = DriverManager.getConnection(url);
        HabitsDAO dao = new HabitsDAO(conn);
        dao.setup();

        Scanner scanner = new Scanner(System.in);

        String input = "";

        String key;

        System.out.println("Habit tracker.");

        while (!input.equals("0"))
        {
            System.out.println("What would you like to do? \n 1. Add habit \n 2. Remove habit \n 3. View habits \n 0. Exit ");
            input = scanner.nextLine(); // Read string input
            switch (input) {
                case "0":
                    break;
                case "1":
                    System.out.print("Enter habit name: ");
                    String name = scanner.nextLine();
                    dao.add(name);

                    System.out.print("Entry successful!");
                    break;

                case "2":
                    dao.get();
                    System.out.print("Select habit to remove: ");
                    key = scanner.nextLine();
                    dao.remove(key);

                    System.out.println("Removal successful!");
                    break;
                case "3":
                    dao.get();
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }

        System.out.println("Bye!");
        scanner.close();
    }
}
