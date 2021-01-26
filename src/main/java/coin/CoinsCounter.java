package coin;

import player.Player;

public class CoinsCounter {
    public int checkActualTotalNumberOfCoinsPosesByPlayer(Player currentPlayer) {
        int numberAllPlayerCoins = 0;
        for (Color color : currentPlayer.getCoinsUser().keySet()) {
            numberAllPlayerCoins += currentPlayer.getCoinsUser().get(color);
        }
        return numberAllPlayerCoins;
    }
}
