package lab3;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Record {
    private LocalDateTime dateTime;
    private double price;
    Master master;
    Client client;

    public Record(LocalDateTime dateTime, double price, Master master, Client client) {
        this.dateTime = dateTime;
        this.price = price;
        this.master = master;
        this.client = client;
    }

    public Record() {
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
        return "DateTime=" + dateTime +
                ", price=" + price +
                ", master=" + master.toString() +
                ", client=" + client.toString() ;
    }
}
