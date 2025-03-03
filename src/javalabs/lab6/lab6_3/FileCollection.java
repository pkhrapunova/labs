package javalabs.lab6.lab6_3;

import java.io.*;
import javalabs.lab6.lab6_3.Model.*;
import javalabs.lab6.lab6_3.Model.Record;

public class FileCollection<T> extends Collection<T> { // Наследуем Collection

    // Метод для сохранения коллекции в текстовый файл
    public void saveToTextFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < size(); i++) {
                T item = get(i);
                writer.write(item.toString()); // Записываем каждый элемент в файл
                writer.newLine(); // Переход на новую строку
            }
            System.out.println("Collection saved to " + filename);
        }
    }

    public void loadFromTextFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("MaterialConsumption")) {
                    add((T) MaterialConsumption.fromString(line));
                } else if (line.startsWith("Client")) {
                    add((T) Client.fromString(line));
                } else if (line.startsWith("Master")) {
                    add((T) Master.fromString(line));
                } else if (line.startsWith("Material")) {
                    add((T) Material.fromString(line));
                } else if (line.startsWith("Record")) {
                    add((T) Record.fromString(line));
                }
            }
            System.out.println("Collection loaded from " + filename);
        }
    }

    // Метод для сохранения коллекции в XML-файл
    public void saveToXmlFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<collection>\n");
            for (int i = 0; i < size(); i++) {
                T item = get(i);
                writer.write("  <item>" + item.toString() + "</item>\n"); // Записываем каждый элемент в XML
            }
            writer.write("</collection>\n");
            System.out.println("Collection saved to " + filename);
        }
    }

    public void loadFromXmlFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("<item>")) {
                    String itemData = line.trim().replace("<item>", "").replace("</item>", "");
                    if (itemData.startsWith("MaterialConsumption")) {
                        add((T) MaterialConsumption.fromString(itemData));
                    } else if (itemData.startsWith("Client")) {
                        add((T) Client.fromString(itemData));
                    } else if (itemData.startsWith("Master")) {
                        add((T) Master.fromString(itemData));
                    } else if (itemData.startsWith("Material")) {
                        add((T) Material.fromString(itemData));
                    } else if (itemData.startsWith("Record")) {
                        add((T) Record.fromString(itemData));
                    }
                }
            }
            System.out.println("Collection loaded from " + filename);
        }
    }
}