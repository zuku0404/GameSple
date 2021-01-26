package coin;

import game.Table;
import player.Player;

public class GoldCoinTaker {

    public void takeOrNotGoldCoin(Player currentPlayer, Table table) {
        CoinsCounter coinsCounter = new CoinsCounter();
        int maxNumberCoinsPosesByPlayerInGame = 10;
        int actualTotalNumberOfCoinsPosesByPlayer = coinsCounter.checkActualTotalNumberOfCoinsPosesByPlayer(currentPlayer);
        int playerNumberOfGoldCoins = currentPlayer.getCoinsUser().get(Color.GOLD);
        int tableNumberOfGoldCoins = table.getCoinsOnTableMap().get(Color.GOLD);

        if (actualTotalNumberOfCoinsPosesByPlayer < maxNumberCoinsPosesByPlayerInGame && tableNumberOfGoldCoins != 0) {
            currentPlayer.getCoinsUser().replace(Color.GOLD, playerNumberOfGoldCoins + 1);
            table.getCoinsOnTableMap().replace(Color.GOLD, tableNumberOfGoldCoins - 1);
        }
    }
}
