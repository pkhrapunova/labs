package javalabs.lab6.lab6_3;

import java.io.IOException;
import java.util.Scanner;

public class CollectionController<T> {
    private final FileCollection<T> collection; // Используем FileCollection

    public CollectionController(FileCollection<T> collection) {
        this.collection = collection;
    }

    public void run(Scanner scanner) {
        while (true) {
            System.out.println("""
                    Select item:
                    1 - add element
                    2 - update element
                    3 - delete element
                    4 - print all
                    5 - save to text file
                    6 - load from text file
                    7 - save to XML file
                    8 - load from XML file
                    0 - exit
                    """);

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
                    case "5":
                        saveToTextFile(scanner);
                        break;
                    case "6":
                        loadFromTextFile(scanner);
                        break;
                    case "7":
                        saveToXmlFile(scanner);
                        break;
                    case "8":
                        loadFromXmlFile(scanner);
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Incorrect input, try again.");
                        break;
                }
            } catch (Exception e) {
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
        T item = collection.createElement(choice, scanner); // Используем метод createElement из Collection
        collection.add(item); // Используем метод add из DynamicCollection
    }

    private void updateElement(Scanner scanner) throws CustomException {
        if (collection.isEmpty()) {
            System.out.println("Collection is empty. Nothing to update.");
            return;
        }

        System.out.print("Enter the index to update: ");
        int index = Integer.parseInt(scanner.nextLine());
        System.out.println("""
                Select a new class for replacement:
                1 - MaterialConsumption
                2 - Client
                3 - Master
                4 - Material
                5 - Record
                """);
        String choice = scanner.nextLine();
        T item = collection.createElement(choice, scanner); // Используем метод createElement из Collection
        collection.update(index, item); // Используем метод update из DynamicCollection
    }

    private void deleteElement(Scanner scanner) throws CustomException {
        if (collection.isEmpty()) {
            System.out.println("Collection is empty. Nothing to delete.");
            return;
        }

        System.out.print("Enter the index to delete: ");
        int index = Integer.parseInt(scanner.nextLine());
        collection.delete(index); // Используем метод delete из DynamicCollection
    }

    private void saveToTextFile(Scanner scanner) throws IOException {
        System.out.print("Enter the filename to save (text): ");
        String filename = scanner.nextLine();
        collection.saveToTextFile(filename); // Используем метод saveToTextFile из FileCollection
    }

    private void loadFromTextFile(Scanner scanner) throws IOException {
        System.out.print("Enter the filename to load (text): ");
        String filename = scanner.nextLine();
        collection.loadFromTextFile(filename); // Используем метод loadFromTextFile из FileCollection
    }

    private void saveToXmlFile(Scanner scanner) throws IOException {
        System.out.print("Enter the filename to save (XML): ");
        String filename = scanner.nextLine();
        collection.saveToXmlFile(filename); // Используем метод saveToXmlFile из FileCollection
    }

    private void loadFromXmlFile(Scanner scanner) throws IOException {
        System.out.print("Enter the filename to load (XML): ");
        String filename = scanner.nextLine();
        collection.loadFromXmlFile(filename); // Используем метод loadFromXmlFile из FileCollection
    }
}