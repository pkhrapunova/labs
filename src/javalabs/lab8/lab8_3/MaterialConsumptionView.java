package javalabs.lab8.lab8_3;

import java.util.Random;


public class MaterialConsumptionView {
    public void Menu() {
        System.out.println("""
                    Select item:
                    1 - add element
                    2 - update element
                    3 - delete element
                    4 - print all
                    5 - save
                    6 - load
                    0 - exit
                    Input:""");
    }

    public int choice(){
        Random random = new Random();
        return random.nextInt(1,7);
    }

}
