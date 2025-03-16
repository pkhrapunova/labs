package javalabs.lab8.lab8_3;
import javalabs.lab8.lab8_3.Model.MaterialConsumption;

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

    public int getSize() {
        return materialConsumptions.size();
    }
    public void saveToFile(String filename) throws CustomException {
        if (materialConsumptions.size() == 0) { // Проверка на пустоту коллекции
            System.out.println("Коллекция пуста. Сохранение не требуется.");
            return; // Выход из метода, если коллекция пуста
        }

        try {
            FileCollection<MaterialConsumption> fileCollection = new FileCollection<>();
            for (int i = 0; i < materialConsumptions.size(); i++) {
                fileCollection.add(materialConsumptions.get(i));
            }
            fileCollection.saveToJsonFile(filename);
            System.out.println("Коллекция сохранена в файл: " + filename);
        } catch (IOException e) {
            throw new CustomException("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) throws CustomException {
        try {
            FileCollection<MaterialConsumption> fileCollection = new FileCollection<>();
            fileCollection.loadFromJsonFile(filename);

            for (int i = 0; i < fileCollection.size(); i++) {
                materialConsumptions.add(fileCollection.get(i));
            }
        } catch (IOException e) {
            throw new CustomException("Ошибка при загрузке файла: " + e.getMessage());
        }
    }
}
