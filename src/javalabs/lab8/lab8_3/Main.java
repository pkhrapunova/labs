package javalabs.lab8.lab8_3;


public class Main {
    public static void main(String[] args) {


        MaterialConsumptionModel  model = new MaterialConsumptionModel();
        MaterialConsumptionView view = new MaterialConsumptionView();
        CollectionController controller = new CollectionController(model, view);

        controller.run();



    }
}
