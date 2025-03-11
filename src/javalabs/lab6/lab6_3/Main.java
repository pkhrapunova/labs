package javalabs.lab6.lab6_3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MaterialConsumptionModel  model = new MaterialConsumptionModel();
        MaterialConsumptionView view = new MaterialConsumptionView();
        CollectionController controller = new CollectionController(model, view);

        controller.run(scanner);
        scanner.close();
    }
}
