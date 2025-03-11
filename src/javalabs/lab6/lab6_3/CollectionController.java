package javalabs.lab6.lab6_3;

import javalabs.lab6.lab6_3.Model.MaterialConsumption;

import java.util.Scanner;

public class CollectionController{
    private final MaterialConsumptionModel model;
    private final MaterialConsumptionView view;

    public CollectionController(MaterialConsumptionModel model,MaterialConsumptionView view) {
        this.model = model;
        this.view = view;
    }

    public void run(Scanner scanner) {
        while (true) {
            view.Menu();
            int choice = view.getChoice(scanner);
            try {
                switch (choice) {
                    case 1:
                        addElement(scanner);
                        break;
                    case 2:
                        updateElement(scanner);
                        break;
                    case 3:
                        deleteElement(scanner);
                        break;
                    case 4:
                        printAll();
                        break;
                    case 5:
                        save(scanner);
                        break;
                    case 6:
                        load(scanner);
                        break;
                    case 0:
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

    private void addElement(Scanner scanner) {
        MaterialConsumption item = new MaterialConsumption();
        item.scan(scanner);
        model.add(item);
    }

    private void updateElement(Scanner scanner) throws CustomException {
        if (model.getSize() == 0) {
            System.out.println("Collection is empty. Nothing to update.");
            return;
        }

        int index = view.getValidIndex(scanner, "update", model.getSize());

        MaterialConsumption updateItem = new MaterialConsumption();
        updateItem.scan(scanner);
        model.update(index, updateItem);
    }

    private void deleteElement(Scanner scanner) throws CustomException {
        if (model.getSize() == 0) {
            System.out.println("Collection is empty. Nothing to delete.");
            return;
        }

        int index = view.getValidIndex(scanner, "delete", model.getSize());
        model.delete(index);
    }

    private void printAll() throws CustomException {
        MaterialConsumption[] materialConsumptions = model.getAll();
        if (materialConsumptions == null || materialConsumptions.length == 0) {
            System.out.println("The collection is empty.\n");
        } else {
            for (int i = 0; i < materialConsumptions.length; i++) {
                System.out.println((i + 1) + ". " + materialConsumptions[i]);
            }
        }
    }

    private void save(Scanner scanner) throws CustomException {
        model.saveToFile(scanner);
   }

    private void load(Scanner scanner) throws CustomException {
        model.loadFromFile(scanner);
    }

}