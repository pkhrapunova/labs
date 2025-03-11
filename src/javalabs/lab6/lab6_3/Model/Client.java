package javalabs.lab6.lab6_3.Model;

import java.util.Scanner;

public class Client extends Person {
    public Client(String firstName, String lastName, String numberPhone) {
        super(firstName, lastName, numberPhone);
    }

    public Client() {
        super();
    }

    @Override
    public String toString() {
        return "Client: " + super.toString();
    }

    public void scan(Scanner scanner) {
        super.scan(scanner);
    }

    @Override
    public boolean telephone(String phoneNumber) {
        return phoneNumber.matches("[+]?[0-9]{0,13}");
    }

}
