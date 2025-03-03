package javalabs.lab6.lab6_3.Model;

import java.util.Scanner;

public class MaterialConsumption {
    private int count;
    private Material material;
    private Record record;

    public MaterialConsumption(int count, Record record, Material material) {
        this.record = record;
        this.material = material;
        this.count = count;
    }

    public MaterialConsumption() {
        this.count = 0;
        this.material = new Material();
        this.record = new Record();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "MaterialConsumption: Count: " + count +
                "; " + material +
                "; " + record;
    }

    public void scan(Scanner input) {
        System.out.println("Enter MaterialConsumption...");
        int count;
        while (true) {
            System.out.println("Enter count:");
            if (input.hasNextInt()) {
                count = input.nextInt();
                if (count < 0) {
                    System.out.println("Count cannot be negative. Please enter a valid integer.");
                    input.nextLine();
                } else {
                    input.nextLine();
                    break;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.next();
            }
        }
        this.setCount(count);

        System.out.println("Enter material details:");
        this.material.scan(input);

        System.out.println("Enter record details:");
        this.record.scan(input);
    }

    public static MaterialConsumption fromString(String str) {
        // Пример строки: "MaterialConsumption: Count: 5; Material: Name: 'Shampoo'; Cost: 15.0$; Categories: SHAMPOO (Silky Shampoo); Record: DateTime: 2023-10-01T12:00; Price: 75.0$; Master: LastName: Doe; FirstName: Jane; NumberPhone: +987654321; Post: Senior Stylist; Client: LastName: Smith; FirstName: John; NumberPhone: +123456789;"
        String[] parts = str.split("; ");
        int count = Integer.parseInt(parts[0].replace("MaterialConsumption: Count: ", ""));

        // Восстановление Material
        String materialStr = parts[1] + "; " + parts[2] + "; " + parts[3];
        Material material = Material.fromString(materialStr);

        // Восстановление Record
        String recordStr = parts[4] + "; " + parts[5] + "; " + parts[6] + "; " + parts[7] + "; " + parts[8] + "; " + parts[9];
        Record record = Record.fromString(recordStr);

        return new MaterialConsumption(count, record, material);
    }

}
