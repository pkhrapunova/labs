package javalabs.lab6.lab6_3.Model;

import java.util.Scanner;

public class Master extends Person {
    private String post;

    public Master(String firstName, String lastName, String numberPhone, String post) {
        super(firstName, lastName, numberPhone);
        this.post = post;
    }

    public Master() {
        super();
        this.post = "";
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override

    public String toString() {
        return "Master: " + super.toString() + " Post: " + post + ";";
    }


    @Override
    public void scan(Scanner input) {
        super.scan(input);
        System.out.println("Enter post:");
        this.setPost(input.nextLine());
    }

    @Override
    public boolean telephone(String phoneNumber) {
        return phoneNumber.matches("[+]?[0-9]{0,13}");
    }

    public static Master fromString(String str) {
        // Пример строки: "Master: LastName: Doe; FirstName: Jane; NumberPhone: +987654321; Post: Senior Stylist;"
        String[] parts = str.split("; ");
        String lastName = parts[0].replace("Master: LastName: ", "");
        String firstName = parts[1].replace("FirstName: ", "");
        String numberPhone = parts[2].replace("NumberPhone: ", "");
        String post = parts[3].replace("Post: ", "");

        return new Master(firstName, lastName, numberPhone, post);
    }
}
