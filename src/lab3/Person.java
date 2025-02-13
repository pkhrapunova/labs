package lab3;
import java.util.Scanner;

public abstract class Person {
    private String lastName;
    private String firstName;
    private String numberPhone;

    public Person(String firstName, String lastName, String numberPhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberPhone = numberPhone;
    }
    public Person() {
        this.firstName ="";
        this.lastName = "";
        this.numberPhone = "";
    }
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    @Override
    public String toString() {
        return "LastName= " + lastName +
                ", firstName= " + firstName +
                ", numberPhone= " + numberPhone;
    }
    public void scan(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter first name");
        this.setFirstName(input.nextLine());
        System.out.println("Enter last name");
        this.setLastName(input.nextLine());
        System.out.println("Enter number phone");
        this.setNumberPhone(input.nextLine());
    }
    public abstract void displayRole();
}
