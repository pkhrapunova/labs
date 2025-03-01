package javalabs.lab6.lab6_3;

import javalabs.lab6.lab6_3.Model.*;
import javalabs.lab6.lab6_3.Model.Record;

import java.util.Scanner;

public class Collection<T> extends DynamicCollection<T> {

    public Collection() {
        super(); // Вызов конструктора DynamicCollection
    }

    @SuppressWarnings("unchecked")
    public T createElement(String choice, Scanner scanner) throws CustomException {
        switch (choice) {
            case "1":
                MaterialConsumption materialConsumption = new MaterialConsumption();
                materialConsumption.scan(scanner);
                return (T) materialConsumption;
            case "2":
                Client client = new Client();
                client.scan(scanner);
                return (T) client;
            case "3":
                Master master = new Master();
                master.scan(scanner);
                return (T) master;
            case "4":
                Material material = new Material();
                material.scan(scanner);
                return (T) material;
            case "5":
                Record record = new Record();
                record.scan(scanner);
                return (T) record;
            default:
                throw new CustomException("Invalid choice! Please select a valid class.");
        }
    }
}