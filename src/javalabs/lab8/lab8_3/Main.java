package javalabs.lab8.lab8_3;


import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        MaterialConsumptionModel model = new MaterialConsumptionModel();
        MaterialConsumptionView view = new MaterialConsumptionView();
        ReentrantLock collectionLock = new ReentrantLock();

        for (int i = 0; i < 10; i++) {
            CollectionController controller = new CollectionController(model, view, 6, collectionLock);
            controller.start();
        }
    }
}

