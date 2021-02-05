package player;

import exceptions.InvalidValueException;

public class PlayersCreator {
    public Player[] createPlayers(int numberOfPlayers) throws InvalidValueException {
        if (numberOfPlayers > 4 || numberOfPlayers < 2) {
            throw new InvalidValueException("Incorrect count of players !! ");
        }
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(i + 1);
        }
        return players;
    }
}
