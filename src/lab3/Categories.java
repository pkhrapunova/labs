package lab3;
import java.util.Scanner;

public enum Categories {
    LIPSTICK("Lipstick"),
    EYE_SHADOW("Eye Shadow"),
    SHAMPOO("Shampoo"),
    STYLING_GEL("Styling Gel"),
    NAIL_FILE("Nail File"),
    NAIL_POLISH("Nail Polish"),
    HAIR_TONIC("Hair Tonic"),
    CUTICLE_OIL("Cuticle Oil"),
    FOOT_SCRUB("Foot Scrub");

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

        Categories selectedCategory = values[inputCategory];
        System.out.println("Selected Category: " + selectedCategory);
        return selectedCategory;
    }
}
