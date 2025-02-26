package javalabs.lab6.lab6_1;
import javalabs.lab6.lab6_1.Model.MaterialConsumption;

public class MaterialConsumptionCollection {
    private MaterialConsumption[] items;
    private int count;

    public MaterialConsumptionCollection() {
        items = new MaterialConsumption[100];
        count = 0;
    }

    public void add(MaterialConsumption item) {
        if (count >= items.length) {
            MaterialConsumption[] newArray = new MaterialConsumption[items.length * 2];
            System.arraycopy(items, 0, newArray, 0, items.length);
            items = newArray;
        }
        items[count++] = item;
    }

    public void update(int index, MaterialConsumption item) throws CustomException {
        if (index < 0 || index >= count) {
            throw new CustomException("Index out of bounds");
        }
        items[index] = item;
    }

    public void delete(int index) throws CustomException {
        if (index < 0 || index >= count) {
            throw new CustomException("Index out of bounds");
        }
        for (int i = index; i < count - 1; i++) {
            items[i] = items[i + 1];
        }
        count--;
    }

    public void printAll() throws CustomException {
        if (count == 0) {
            throw new CustomException("There are no items in the collection");
        }
        for (int i = 0; i < count; i++) {
            System.out.println(i + ": \n" + items[i]);
        }
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
