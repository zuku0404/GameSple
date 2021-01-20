public class PlayerSetter {
    private Player[] players;
    public Player[] createPlayers(int playersNumber) {
        if (playersNumber > 4 || playersNumber < 2) {
            throw new IllegalArgumentException();
        }
        players = new Player[playersNumber];
        for (int i = 0; i < playersNumber; i++) {
            players[i] = new Player(i + 1);
        }
        return players;
    }
}
