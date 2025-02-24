package javalabs.lab6.lab6_2;

public class Main {
    public static void main(String[] args) {
        Collection<Object> collection = new Collection<>();
        CollectionController<Object> controller = new CollectionController<>(collection);
        controller.run();
    }
}
