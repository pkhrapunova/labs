package javalabs.lab6.lab6_1.Model;

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
        return super.toString() + "\n    Post: " + post;
    }

    @Override
    public void scan(){
        super.scan();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter post:");
        this.setPost(input.nextLine());
    }
    @Override
    public  boolean telephone(String phoneNumber) {
        return phoneNumber.matches("[+]?[0-9]{0,13}");
    }
}
