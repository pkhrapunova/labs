package javalabs.lab6.lab6_2;
import java.util.Scanner;

public class CollectionController<T> {
    private final Collection<T> collection;

    public CollectionController(Collection<T> collection) {
        this.collection = collection;
    }

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
                        addElement(scanner);
                        break;
                    case "2":
                        updateElement(scanner);
                        break;
                    case "3":
                        deleteElement(scanner);
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

    private void addElement(Scanner scanner) throws CustomException {
        System.out.println("Adding a new entry...");
        System.out.println("""
                Select a class to add:
                1 - MaterialConsumption
                2 - Client
                3 - Master
                4 - Material
                5 - Record
                """);

        String choice = scanner.nextLine();
        collection.add(choice);
    }

    private void updateElement(Scanner scanner) throws CustomException {
        if (collection.isEmpty()) {
            System.out.println("Collection is empty. Nothing to update.");
            return;
        }

        System.out.print("Enter the index to update: ");
        int index = Integer.parseInt(scanner.nextLine());
        System.out.println("Select a new class for replacement:");
        System.out.println("""
                Select a class to add:
                1 - MaterialConsumption
                2 - Client
                3 - Master
                4 - Material
                5 - Record
                """);
        String choice = scanner.nextLine();
        collection.update(index, choice);
    }

    private void deleteElement(Scanner scanner) throws CustomException {
        if (collection.isEmpty()) {
            System.out.println("Collection is empty. Nothing to delete.");
            return;
        }

        System.out.print("Enter the index to delete: ");
        int index = Integer.parseInt(scanner.nextLine());
        collection.delete(index);
    }
}
