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
import java.util.concurrent.locks.ReentrantLock;

public class FileCollection<T> extends DynamicCollection<T> {

    private final ReentrantLock fileLock = new ReentrantLock();

    public FileCollection() {
        super();
    }

    public void saveToJsonFile(String filename) throws IOException, CustomException {
        fileLock.lock();
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                List<T> items = new ArrayList<>();
                for (int i = 0; i < this.size(); i++) {
                    items.add(this.get(i));
                }
                String json = gson.toJson(items);
                writer.write(json);
            }
        } finally {
            fileLock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromJsonFile(String filename) throws IOException {
        fileLock.lock();
        try {
            Gson gson = new Gson();
            try (FileReader reader = new FileReader(filename)) {
                Type collectionType = new TypeToken<ArrayList<MaterialConsumption>>(){}.getType();
                List<MaterialConsumption> loadedCollection = gson.fromJson(reader, collectionType);
                for (MaterialConsumption item : loadedCollection) {
                    this.add((T) item);
                }
            }
        } finally {
            fileLock.unlock();
        }
    }
}