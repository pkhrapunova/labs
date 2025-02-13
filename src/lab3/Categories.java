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
        return description;
    }

    public static void scan() {
        Scanner scanner = new Scanner(System.in);
        Categories[] values = Categories.values();
        for (Categories category : values) {
            System.out.println(category.ordinal() + " - " + category.name());
        }
        System.out.println("Enter Category: ");
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a number! Please enter a valid Category:");
            scanner.next();
        }
        int inputCategory = scanner.nextInt();
        Categories selectedCategory;
        switch (inputCategory) {
            case 0:
                selectedCategory = Categories.LIPSTICK;
                break;
            case 1:
                selectedCategory = Categories.EYE_SHADOW;
                break;
            case 2:
                selectedCategory = Categories.SHAMPOO;
                break;
            case 3:
                selectedCategory = Categories.STYLING_GEL;
                break;
            case 4:
                selectedCategory = Categories.NAIL_FILE;
                break;
            case 5:
                selectedCategory = Categories.NAIL_POLISH;
                break;
            case 6:
                selectedCategory = Categories.HAIR_TONIC;
                break;
            case 7:
                selectedCategory = Categories.CUTICLE_OIL;
                break;
            case 8:
                selectedCategory = Categories.FOOT_SCRUB;
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
                scan();
                return;
        }
        System.out.println("Selected Category: " + selectedCategory);
    }
}
