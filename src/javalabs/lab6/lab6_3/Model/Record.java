package javalabs.lab6.lab6_3.Model;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Record {
    private LocalDateTime dateTime;
    private double price;
    private Master master;
    private Client client;

    public Record(LocalDateTime dateTime, double price, Master master, Client client) {
        this.dateTime = dateTime;
        this.price = price;
        this.master = master;
        this.client = client;
    }

    public Record() {
        this.dateTime = LocalDateTime.now();
        this.price = 0.0;
        this.master = new Master();
        this.client = new Client();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Record: DateTime: " + dateTime + "; Price: " + price + "$; " + master.toString() + " " + client.toString();
    }


    /*1111-11-11 11:11*/

    public void scan(Scanner input) {
        double price;
        while (true) {
            System.out.println("Enter price:");
            if (input.hasNextDouble()) {
                price = input.nextDouble();
                if (price < 0) {
                    System.out.println("Please enter a valid price.");
                } else {
                    input.nextLine();
                    break;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for price.");
                input.next();
            }
        }
        this.setPrice(price);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime dateTime;
        while (true) {
            System.out.println("Enter date and time (yyyy-MM-dd HH:mm):");
            String dateTimeStr = input.nextLine();
            try {
                dateTime = LocalDateTime.parse(dateTimeStr, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date and time format. Please enter the date and time in the format yyyy-MM-dd HH:mm.");
            }
        }
        this.setDateTime(dateTime);
        System.out.println("Enter master details:");
        this.master.scan(input);

        System.out.println("Enter client details:");
        this.client.scan(input);
    }

    public static Record fromString(String str) {
        // Пример строки: "Record: DateTime: 2023-10-01T12:00; Price: 75.0$; Master: LastName: Doe; FirstName: Jane; NumberPhone: +987654321; Post: Senior Stylist; Client: LastName: Smith; FirstName: John; NumberPhone: +123456789;"
        String[] parts = str.split("; ");

        double price = Double.parseDouble(parts[1].replace("Price: ", "").replace("$", ""));
        LocalDateTime dateTime = LocalDateTime.parse(parts[0].replace("Record: DateTime: ", ""));
        // Восстановление Master
        String masterStr = parts[2] + "; " + parts[3] + "; " + parts[4] + "; " + parts[5];
        Master master = Master.fromString(masterStr);

        // Восстановление Client
        String clientStr = parts[6] + "; " + parts[7] + "; " + parts[8];
        Client client = Client.fromString(clientStr);

        return new Record(dateTime, price, master, client);
    }
}
