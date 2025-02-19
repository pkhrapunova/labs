package javalabs.lab6.lab6_1;
import java.util.Scanner;

public enum Categories {
    LIPSTICK("Luxurious Lipstick"),
    EYE_SHADOW("Vibrant Eye Shadow"),
    SHAMPOO("Silky Shampoo"),
    STYLING_GEL("Ultimate Styling Gel"),
    NAIL_FILE("Precision Nail File"),
    NAIL_POLISH("Glossy Nail Polish"),
    HAIR_TONIC("Revitalizing Hair Tonic"),
    CUTICLE_OIL("Nourishing Cuticle Oil"),
    FOOT_SCRUB("Exfoliating Foot Scrub");

    private final String description;

    Categories(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name() + " (" + description + ")";
    }

    public static Categories scan() {
        Scanner scanner = new Scanner(System.in);
        Categories[] values = Categories.values();

        for (Categories category : values) {
            System.out.println(category.ordinal() + " - " + category.name());
        }

        System.out.println("Enter Category: ");
        int inputCategory = -1;

        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number! Please enter a valid Category:");
                scanner.next();
            }
            inputCategory = scanner.nextInt();
            if (inputCategory >= 0 && inputCategory < values.length) {
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
        return values[inputCategory];
    }
}
