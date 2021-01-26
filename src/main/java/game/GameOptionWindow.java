package game;

import java.util.Scanner;

public class GameOptionWindow {
    private static Scanner scanner = new Scanner(System.in);

    private GameOptionWindow(){}

    public static int showDecisionOption() {
        System.out.println("\nWhat would you like to do (write number): ");
        System.out.println("1:buy card, 2:take three coins different color, 3:take two coins in the same color, " +
                "4:buy a hero, 5:reserve card, 6 buy reserved card");
        System.out.print("Enter number: ");
        return scanner.nextInt();
    }
}
