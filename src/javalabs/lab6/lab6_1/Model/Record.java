package javalabs.lab6.lab6_1.Model;

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
        return "    DateTime: " + dateTime +
                ";\n    Price: " + price +
                "$;\nMaster:" + master.toString() +
                ";\nClient:" + client.toString();
    }

    public void scan(){
        Scanner input = new Scanner(System.in);
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

        System.out.println("Enter master details:");
        this.master.scan();

        System.out.println("Enter client details:");
        this.client.scan();
    }



}
