package javalabs.lab6.lab6_3.Model;

import java.util.Scanner;

public class Material {
    private String name;
    private double cost;
    private Categories categories;

    public Material(String name, double cost, Categories categories) {
        this.name = name;
        this.cost = cost;
        this.categories = categories;
    }

    public Material() {
        this.name = "";
        this.cost = 0.0;
        this.categories = Categories.DEFAULT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Material: Name: '" + name + '\'' +
                "; Cost: " + cost +
                "$; Categories: " + (categories != null ? categories.toString() : "None");
    }

    public void scan(Scanner input) {
        System.out.println("Enter name:");
        this.setName(input.nextLine());

        double cost;
        while (true) {
            System.out.println("Enter cost:");
            if (input.hasNextDouble()) {
                cost = input.nextDouble();
                if (cost >= 0) {
                    //input.nextLine();
                    break;
                } else {
                    System.out.println("Cost cannot be negative. Please enter a valid number for cost.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for cost.");
            }
            input.nextLine();
        }
        this.setCost(cost);

        System.out.println("Select a category:");
        this.categories = Categories.scan(input);
    }
    public static Material fromString(String str) {
        // Пример строки: "Material: Name: 'Shampoo'; Cost: 15.0$; Categories: SHAMPOO (Silky Shampoo);"
        String[] parts = str.split("; ");
        String name = parts[0].replace("Material: Name: '", "").replace("'", "");
        double cost = Double.parseDouble(parts[1].replace("Cost: ", "").replace("$", ""));
        Categories categories = Categories.valueOf(parts[2].replace("Categories: ", "").split(" ")[0]);

        return new Material(name, cost, categories);
    }

}
