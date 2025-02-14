package lab3;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

//улучшить скан
        System.out.println("--- Creating MaterialConsumption ---");
        MaterialConsumption materialConsumption = new MaterialConsumption();
        materialConsumption.scan();
        System.out.println(materialConsumption);
    }
}

