package javalabs.lab6.lab6_3;

import java.io.*;

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

    // Метод для загрузки коллекции из текстового файла
    public void loadFromTextFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Логика для преобразования строки в объект типа T
                add((T) line); // Добавляем строку в коллекцию
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

    // Метод для загрузки коллекции из XML-файла
    public void loadFromXmlFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("<item>")) {
                    // Извлекаем данные между тегами <item> и </item>
                    String itemData = line.trim().replace("<item>", "").replace("</item>", "");
                    // Логика для преобразования строки в объект типа T
                    add((T) itemData); // Добавляем строку в коллекцию
                }
            }
            System.out.println("Collection loaded from " + filename);
        }
    }
}