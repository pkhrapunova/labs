package lab3;

import jdk.jfr.Category;

public class Main {
    public static void main(String[] args) {
        Master master = new Master("sfb","jhg","+987654","jhgfiuytr");
        Client client= new Client();

        client.scan();

        System.out.println(client.toString());
    }
}
