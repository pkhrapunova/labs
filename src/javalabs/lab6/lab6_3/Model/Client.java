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

    public static Client fromString(String str) {
        // Пример строки: "Client: LastName: Smith; FirstName: John; NumberPhone: +123456789;"
        String[] parts = str.split("; ");
        String lastName = parts[0].replace("Client: LastName: ", "");
        String firstName = parts[1].replace("FirstName: ", "");
        String numberPhone = parts[2].replace("NumberPhone: ", "");

        return new Client(firstName, lastName, numberPhone);
    }
}
