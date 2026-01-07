package tracker;
import tracker.HabitsDAO;

import java.util.Scanner;

public class InputHandler {
    private static String input = "";
    private static Scanner scanner = new Scanner(System.in);

    public static void handleInput(HabitsDAO dao){
        String key;
        while (!input.equals("0"))
        {
            System.out.println("What would you like to do? \n 1. Add habit \n 2. Remove habit \n 3. View habits\n 4. Increment habit counter\n 0. Exit ");
            input = scanner.nextLine(); // Read string input
            switch (input) {
                case "0":
                    scanner.close();
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
                case "4":
                    dao.get();
                    System.out.println("Which habit would you like to increment ?");
                    key = scanner.nextLine();
                    dao.incrementCounter(key);
                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}
