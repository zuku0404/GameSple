import java.util.Scanner;

public class GameOptionWindow {
    public static int showDecisionOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat would you like to do (write number): ");
        System.out.println("1:buy card, 2:take three coins different color, 3:take two coins in the same color, " +
                "4:buy a hero, 5:reserve card, 6 buy reserved card");
        System.out.print("Enter number: ");
        int numberDecision = scanner.nextInt();
        return numberDecision;
    }
}
