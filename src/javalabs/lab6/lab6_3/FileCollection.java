package javalabs.lab6.lab6_3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javalabs.lab6.lab6_3.Model.MaterialConsumption;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileCollection<T> extends DynamicCollection<T> {

    public FileCollection() {
        super();
    }

    public void saveToJsonFile(Scanner scanner) throws IOException, CustomException {
        System.out.print("Enter the filename to save: ");
        String filename = scanner.nextLine();
        filename+=".json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            List<T> items = new ArrayList<>();
            for (int i = 0; i < this.size(); i++) {
                items.add(this.get(i));
            }
            String json = gson.toJson(items);
            writer.write(json);
            System.out.println("Collection saved to " + filename);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromJsonFile(Scanner scanner) throws IOException {
        System.out.print("Enter the filename to load: ");
        String filename = scanner.nextLine();
        filename+=".json";
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            Type collectionType = new TypeToken<ArrayList<MaterialConsumption>>(){}.getType();
            List<MaterialConsumption> loadedCollection = gson.fromJson(reader, collectionType);
            for (MaterialConsumption item : loadedCollection) {
                this.add((T) item);
            }
            System.out.println("Collection loaded from " + filename);
        }
    }
}