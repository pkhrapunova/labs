package javalabs.lab3;
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
        this.categories = null;
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
        return "    Name: '" + name + '\'' +
                ";\n    Cost: " + cost +
                "$;\n    Categories: " + (categories != null ? categories.toString() : "None");
    }

    public void scan() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name:");
        this.setName(input.nextLine());

        double cost;
        while (true) {
            System.out.println("Enter cost:");
            if (input.hasNextDouble()) {
                cost = input.nextDouble();
                if (cost < 0) {
                    System.out.println("Cost cannot be negative. Please enter a valid number for cost.");
                    input.nextLine();
                } else {
                    input.nextLine();
                    break;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for cost.");
                input.next();
            }
        }
        this.setCost(cost);

        System.out.println("Select a category:");
        this.categories = Categories.scan();
    }


}
