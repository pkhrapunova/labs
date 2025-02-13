package lab3;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Creating Client ---");
        Client client = new Client();
        client.scan();
        System.out.println(client);
        client.displayRole();

        System.out.println("\n--- Creating Master ---");
        Master master = new Master();
        master.scan();
        System.out.println(master);
        master.displayRole();

        System.out.println("\n--- Creating Material ---");
        Material material = new Material();
        material.scan();
        System.out.println(material);

        System.out.println("\n--- Creating Record ---");
        Record record = new Record(LocalDateTime.now(), 100.0, master, client);
        record.scan();
        System.out.println(record);

        System.out.println("\n--- Creating MaterialConsumption ---");
        MaterialConsumption materialConsumption = new MaterialConsumption();
        materialConsumption.scan();
        System.out.println(materialConsumption);
    }
}

