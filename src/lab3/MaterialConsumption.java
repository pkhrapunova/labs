package lab3;
import java.util.Scanner;

public class MaterialConsumption {
    private int count;
    Material material;
    Record record;

    public MaterialConsumption(int count, Record record, Material material) {
        this.record = record;
        this.material = material;
        this.count = count;
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
                ", record=" + record.toString();
    }
}
