package player;

public class PlayerSetter {
    public Player[] createPlayers(int playersNumber) {
        Player[] players;
        if (playersNumber > 4 || playersNumber < 2) {
            throw new IllegalArgumentException("Incorrect count of players !! ");
        }
        players = new Player[playersNumber];
        for (int i = 0; i < playersNumber; i++) {
            players[i] = new Player(i + 1);
        }
        return players;
    }
}
