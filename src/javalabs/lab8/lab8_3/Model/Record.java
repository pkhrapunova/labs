package javalabs.lab8.lab8_3.Model;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Record {
    private LocalDate date;
    private double price;
    private Master master;
    private Client client;

    public Record(LocalDate date, double price, Master master, Client client) {
        this.date = date;
        this.price = price;
        this.master = master;
        this.client = client;
    }

    public Record() {
        this.date = LocalDate.now();
        this.price = 0.0;
        this.master = new Master();
        this.client = new Client();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDateTime(LocalDate date) {
        this.date = date;
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
        return "Record: Date: " + date+ "; Price: " + price + "$; " + master.toString() + " " + client.toString();
    }


    /*1111-11-11*/

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
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date;
        while (true) {
            System.out.println("Enter date (yyyy-MM-dd):");
            String dateStr = input.nextLine();
            try {
                date = LocalDate.parse(dateStr, dateFormatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
            }
        }
        this.setDateTime(date);
        System.out.println("Enter master details:");
        this.master.scan(input);

        System.out.println("Enter client details:");
        this.client.scan(input);
    }


}
