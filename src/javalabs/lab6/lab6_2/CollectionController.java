package javalabs.lab6.lab6_2;
import javalabs.lab6.lab6_2.Model.*;
import javalabs.lab6.lab6_2.Model.Record;
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

    private void addElement(Scanner scanner) {
        System.out.println("Adding a new entry...");
        System.out.println("Select a class to add:\n" +
                "1 - MaterialConsumption\n" +
                "2 - Client\n" +
                "3 - Master\n" +
                "4 - Material\n" +
                "5 - Record\n");

        String choice = scanner.nextLine();  // Чтение выбора пользователя
        T newElement = createElement(choice, scanner);  // Создание элемента на основе выбора пользователя
        collection.add(newElement);  // Добавление элемента в коллекцию
    }


    private void updateElement(Scanner scanner) throws CustomException {
        System.out.print("Enter the index to update: ");
        int index = Integer.parseInt(scanner.nextLine());
        String choice = scanner.nextLine();
        T newElement = createElement(choice, scanner);  // Функция для создания элемента
        collection.update(index, newElement);
    }

    private void deleteElement(Scanner scanner) throws CustomException {
        System.out.print("Enter the index to delete: ");
        int index = Integer.parseInt(scanner.nextLine());
        collection.delete(index);
    }

    private T createElement(String choice, Scanner scanner)  {
        switch (choice) {
            case "1":
                MaterialConsumption materialConsumption = new MaterialConsumption();
                materialConsumption.scan();
                return (T) materialConsumption;
            case "2":
                Client client = new Client();
                client.scan();
                return (T) client;
            case "3":
                Master master = new Master();
                master.scan();
                return (T) master;
            case "4":
                Material material = new Material();
                material.scan();
                return (T) material;
            case "5":
                Record record = new Record();
                record.scan();
                return (T) record;
            default:
                throw new IllegalArgumentException("Invalid choice! Please select a valid class.");
        }
    }


}
