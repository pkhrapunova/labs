package javalabs.lab6.lab6_3;

import java.util.Scanner;

public class MaterialConsumptionView {
    public void Menu() {
        System.out.println("""
                    Select item:
                    1 - add element
                    2 - update element
                    3 - delete element
                    4 - print all
                    5 - save
                    6 - load
                    0 - exit
                    Input:""");
    }
    public int getChoice(Scanner scanner) {
        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.println("Wrong input. Please enter a valid integer.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        }
    }

    public int getValidIndex(Scanner scanner, String action, int maxIndex) {
        while (true) {
            try {
                System.out.print("Enter the index to " + action + ": ");
                int index = getChoice(scanner);
                if (index < 0 || index >= maxIndex) {
                    throw new CustomException("Wrong index. Please enter a number between 0 and " + (maxIndex - 1) + ".");
                }
                return index;
            } catch (CustomException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
