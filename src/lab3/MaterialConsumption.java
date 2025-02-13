package lab3;
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
        return "count=" + count +
                ", material=" + material +
                ", record=" + record;
    }

    public void scan(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter count:");
        this.setCount(input.nextInt());
        input.nextLine();

        System.out.println("Enter material details:");
        this.material.scan();

        System.out.println("Enter record details:");
        this.record.scan();
    }
}
