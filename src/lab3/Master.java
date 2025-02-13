package lab3;
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
        return super.toString() + ", post='" + post + '\'';
    }

    @Override
    public void scan(){
        super.scan();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter post:");
        this.setPost(input.nextLine());
    }
    @Override
    public void displayRole() {
        System.out.println("Role: Master");
    }
}
