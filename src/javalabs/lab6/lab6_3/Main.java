package javalabs.lab6.lab6_3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileCollection<Object> collection = new FileCollection<>(); // Используем FileCollection
        CollectionController<Object> controller = new CollectionController<>(collection);

        controller.run(scanner);
        scanner.close();
    }
}
