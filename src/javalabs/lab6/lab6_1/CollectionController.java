package javalabs.lab6.lab6_1;
import javalabs.lab6.lab6_1.Model.MaterialConsumption;

import java.util.Scanner;

public class CollectionController {
    private final MaterialConsumptionCollection collection = new MaterialConsumptionCollection();

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select item:");
            System.out.println("1 - add element");
            System.out.println("2 - update element");
            System.out.println("3 - delete element");
            System.out.println("4 - print all");
            System.out.println("0 - exit");

            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        addElement();
                        break;
                    case "2":
                        updateElement();
                        break;
                    case "3":
                        deleteElement();
                        break;
                    case "4":
                        collection.printAll();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Incorrect input, try again.");
                        break;
                }
            } catch (CustomException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addElement() {
        System.out.println("Adding a new entry...");
        MaterialConsumption newElement = new MaterialConsumption();
        newElement.scan();
        collection.add(newElement);
    }

    private void updateElement() throws CustomException {
        if (collection.isEmpty()) {
            throw new CustomException("The collection is empty, nothing to update.");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index to update: ");
        int index = Integer.parseInt(scanner.nextLine());
        MaterialConsumption newElement = new MaterialConsumption();
        newElement.scan();
        collection.update(index, newElement);
    }

    private void deleteElement() throws CustomException {
        if (collection.isEmpty()) {
            throw new CustomException("Error: The collection is empty, nothing to delete.");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index to delete: ");
        int index = Integer.parseInt(scanner.nextLine());
        collection.delete(index);
    }
}
