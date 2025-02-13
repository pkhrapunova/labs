package lab3;
import java.util.Scanner;

public class Client extends Person{
    public Client(String firstName, String lastName, String numberPhone) {
        super(firstName, lastName, numberPhone);
    }

    public Client() {
        super();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void scan(){
        super.scan();
    }
    @Override
    public void displayRole() {
        System.out.println("Role: Client");
    }
}
