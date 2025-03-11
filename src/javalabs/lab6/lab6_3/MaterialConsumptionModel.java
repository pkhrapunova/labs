package javalabs.lab6.lab6_3;
import javalabs.lab6.lab6_3.Model.MaterialConsumption;

import java.io.IOException;
import java.util.Scanner;

public class MaterialConsumptionModel {
    private final DynamicCollection<MaterialConsumption> materialConsumptions;

    public MaterialConsumptionModel() {
        this.materialConsumptions = new DynamicCollection<>();
    }

    public void add(MaterialConsumption materialConsumption){
        materialConsumptions.add(materialConsumption);
    }
    public void update(int index,MaterialConsumption materialConsumption ) throws CustomException {
        materialConsumptions.update(index,materialConsumption);
    }
    public void delete(Integer index) throws CustomException {
        materialConsumptions.delete(index);
    }

    public MaterialConsumption[] getAll() throws CustomException {
        MaterialConsumption[] result = new MaterialConsumption[materialConsumptions.size()];
        for (int i = 0; i < materialConsumptions.size(); i++) {
            result[i] = materialConsumptions.get(i);
        }
        return result;
    }

    public int getSize() {
        return materialConsumptions.size();
    }
    public void saveToFile(Scanner scanner) throws CustomException {
        try {
            FileCollection<MaterialConsumption> fileCollection = new FileCollection<>();
            for (int i = 0; i < materialConsumptions.size(); i++) {
                fileCollection.add(materialConsumptions.get(i));
            }
            fileCollection.saveToJsonFile(scanner);
        } catch (IOException e) {
            throw new CustomException("Error saving file: " + e.getMessage());
        }
    }

    public void loadFromFile(Scanner scanner) throws CustomException {
        try {
            FileCollection<MaterialConsumption> fileCollection = new FileCollection<>();
            fileCollection.loadFromJsonFile(scanner);

            for (int i = 0; i < fileCollection.size(); i++) {
                materialConsumptions.add(fileCollection.get(i));
            }
        } catch (IOException e) {
            throw new CustomException("Error loading file: " + e.getMessage());
        }
    }
}
