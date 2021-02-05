package main;

import exceptions.InvalidValueException;
import game.Game;

public class Main {

    public static void main(String[] args) {
        try {
            Game game = new Game(2);
            game.runGame();
        }catch (InvalidValueException ex){
            System.out.println(ex.getMessage());
        }

    }
}
