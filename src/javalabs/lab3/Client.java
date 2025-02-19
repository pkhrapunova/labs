package javalabs.lab3;

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
    public  boolean telephone(String phoneNumber) {
        return phoneNumber.matches("[+]?[0-9]{0,13}");
    }
}
