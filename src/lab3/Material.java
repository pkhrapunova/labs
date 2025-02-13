package lab3;

import java.util.Scanner;

public class Material {
    private String name;
    private double cost;
    Categories categories;

    public Material(String name, double cost, Categories categories) {
        this.name = name;
        this.cost = cost;
        this.categories = categories;
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
        return "name='" + name + '\'' +
                ", cost=" + cost +
                ", categories=" + categories.toString();
    }

    public void scan(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name");
        this.setName(input.nextLine());
        System.out.println("Enter cost");
        this.setCost(input.nextInt());
        //this.setCategories();
    }
}
