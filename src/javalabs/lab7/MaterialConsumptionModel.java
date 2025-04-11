package javalabs.lab7;
import javalabs.lab7.Model.MaterialConsumption;

import java.io.IOException;

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
    public MaterialConsumption get(int selectedItem) throws CustomException {
        return materialConsumptions.get(selectedItem);
    }

    public int getSize() {
        return materialConsumptions.size();
    }
    public void saveToFile(String filePath) throws CustomException {
        try {
            FileCollection<MaterialConsumption> fileCollection = new FileCollection<>();
            for (int i = 0; i < materialConsumptions.size(); i++) {
                fileCollection.add(materialConsumptions.get(i));
            }
            fileCollection.saveToJsonFile(filePath);
        } catch (IOException e) {
            throw new CustomException("Error saving file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filePath) throws CustomException {
        try {
            FileCollection<MaterialConsumption> fileCollection = new FileCollection<>();
            fileCollection.loadFromJsonFile(filePath);

            for (int i = 0; i < fileCollection.size(); i++) {
                materialConsumptions.add(fileCollection.get(i));
            }
        } catch (IOException e) {
            throw new CustomException("Error loading file: " + e.getMessage());
        }
    }
}
