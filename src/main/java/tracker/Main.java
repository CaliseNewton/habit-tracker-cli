package tracker;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:test.db";

        Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
        HabitsDAO dao = new HabitsDAO(conn);
        dao.setup();

        Scanner scanner = new Scanner(System.in);

        String input = "";

        String key;

        System.out.println("Welcome!");

        while (!input.equals("0"))
        {
            System.out.println("What would you like to do? \n 1. Add \n 2. Remove \n 0. Exit ");
            input = scanner.nextLine(); // Read string input
            switch (input) {
                case "0":
                    break;
                case "1":
                    System.out.print("enter id: ");
                    key = scanner.nextLine();
                    System.out.print("enter name: ");
                    String name = scanner.nextLine();
                    dao.add(key, name);

                    System.out.print("Entry successful!");
                    break;

                case "2":
                    System.out.println("enter id: ");
                    key = scanner.nextLine();
                    dao.remove(key);

                    System.out.print("Removal successful!");
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }

        System.out.println("Bye!");
        scanner.close();
    }
}
