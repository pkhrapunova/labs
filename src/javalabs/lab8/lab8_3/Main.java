package javalabs.lab8.lab8_3;


public class Main {
    public static void main(String[] args) {
        MaterialConsumptionModel model = new MaterialConsumptionModel();
        MaterialConsumptionView view = new MaterialConsumptionView();

        for (int i = 0; i < 10; i++) {
            CollectionController controller = new CollectionController(model, view, 6);
            Thread thread = new Thread(controller::run);
            thread.start();
        }
    }
}


