package javalabs.lab8.lab8_3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javalabs.lab8.lab8_3.Model.MaterialConsumption;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileCollection<T> extends DynamicCollection<T> {

    public FileCollection() {
        super();
    }

    public synchronized void saveToJsonFile(String filename) throws IOException, CustomException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            List<T> items = new ArrayList<>();
            for (int i = 0; i < this.size(); i++) {
                items.add(this.get(i));
            }
            String json = gson.toJson(items);
            writer.write(json);
        }
    }

    @SuppressWarnings("unchecked")
    public synchronized void loadFromJsonFile(String filename) throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            Type collectionType = new TypeToken<ArrayList<MaterialConsumption>>(){}.getType();
            List<MaterialConsumption> loadedCollection = gson.fromJson(reader, collectionType);
            for (MaterialConsumption item : loadedCollection) {
                this.add((T) item);
            }
        }
    }
}