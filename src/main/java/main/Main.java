package main;

import exceptions.InvalidValueException;
import game.Game;
import message.Message;
import message.Messenger;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Messenger.display(Message.WELCOME);
            int numberOfPlayers = scanner.nextInt();
            Game game = new Game(numberOfPlayers);
            game.runGame();
        } catch (InvalidValueException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
