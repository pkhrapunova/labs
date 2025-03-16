package javalabs.lab8.lab8_3;

import javalabs.lab8.lab8_3.Model.*;
import javalabs.lab8.lab8_3.Model.Record;

import java.time.LocalDate;
import java.util.Random;

public class DataGenerator {
    private static final Random random = new Random();

    private static final String[] FIRST_NAMES = {"Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hank"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis"};
    private static final String[] POSTS = {"Senior Stylist", "Junior Stylist", "Hair Specialist", "Nail Technician", "Makeup Artist"};
    private static final String[] MATERIAL_NAMES = {"Luxurious Lipstick", "Vibrant Eye Shadow", "Silky Shampoo", "Ultimate Styling Gel", "Precision Nail File"};

    public static Client generateClient() {
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        String phoneNumber = "+" + (random.nextInt(999999999) + 1000000000);
        return new Client(firstName, lastName, phoneNumber);
    }

    public static Master generateMaster() {
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        String phoneNumber = "+" + (random.nextInt(999999999) + 1000000000);
        String post = POSTS[random.nextInt(POSTS.length)];
        return new Master(firstName, lastName, phoneNumber, post);
    }

    public static Material generateMaterial() {
        String name = MATERIAL_NAMES[random.nextInt(MATERIAL_NAMES.length)];
        double cost =  1 + random.nextInt(100);
        Categories category = Categories.values()[random.nextInt(Categories.values().length)];
        return new Material(name, cost, category);
    }

    public static Record generateRecord() {
        LocalDate date = LocalDate.now().minusDays(random.nextInt(365));
        double price = 1.0 + random.nextInt(200);
        Master master = generateMaster();
        Client client = generateClient();
        return new Record(date, price, master, client);
    }

    public static MaterialConsumption generateMaterialConsumption() {
        int count = 1 + random.nextInt(10);
        Material material = generateMaterial();
        Record record = generateRecord();
        return new MaterialConsumption(count, record, material);
    }
}