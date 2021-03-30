package card;

import coin.Color;
import exceptions.InvalidValueException;
import game.Table;
import player.Player;
import java.util.Map;

import static coin.Color.*;

public class CardDealer {
    private Table table;
    private Player currentPlayer;

    public CardDealer(Table table, Player currentPlayer) {
        this.table = table;
        this.currentPlayer = currentPlayer;
    }

    public void buySelectedCard(Card selectedCard) throws InvalidValueException {
        Map<Color, Integer> selectedCardCost = selectedCard.getCost();
        if (hasPlayerEnoughColorPointsToBuyCard(selectedCardCost)) {
            checkPlayerNeedUseCoins(selectedCardCost);
        } else {
            throw new InvalidValueException("not enough color points");
        }
    }

    void checkPlayerNeedUseCoins(Map<Color, Integer> selectedCardCost) {
        Map<Color, Integer> playerCards = currentPlayer.getCardsUser();
        for (Color color : getColorsWithoutGold()) {
            int coinsNeededToBuyCard = selectedCardCost.get(color) - playerCards.get(color);
            if (coinsNeededToBuyCard > 0) {
                useCoinsToBuyCard(color, coinsNeededToBuyCard);
            }
        }
    }

    void useCoinsToBuyCard(Color color, int coinsNeededToBuyCard) {
        int colorCoinsPosesByPlayer = currentPlayer.getNumberOfSelectedColorCoins(color);
        int coins = colorCoinsPosesByPlayer - coinsNeededToBuyCard;
        int colorCoinsOnTable = table.getNumberOfSelectedColorCoins(color);

        if (coins < 0) {
            useColorAndGoldCoins(color, coins, colorCoinsOnTable, colorCoinsPosesByPlayer);
        } else {
            useOnlyColorCoins(color, coins, colorCoinsOnTable, colorCoinsPosesByPlayer);
        }
    }

    private void useOnlyColorCoins(Color color, int coins, int colorCoinsOnTable, int coinsNeededToBuyCard) {
        currentPlayer.setNumberOfSelectedColorCoins(color, coins);
        table.setNumberOfSelectedColorCoins(color, colorCoinsOnTable + coinsNeededToBuyCard);
    }

     private void useColorAndGoldCoins(Color color, int goldenCoinsUsed, int colorCoinsOnTable, int colorCoinsPosesByPlayer) {
        int goldCoinsOnTable = table.getNumberOfSelectedColorCoins(GOLD);
        int goldCoinsPlayer = currentPlayer.getNumberOfSelectedColorCoins(GOLD);
        int goldCoinsOnTableAfterTransaction = goldCoinsOnTable + Math.abs(goldenCoinsUsed);
        int goldCoinsPlayerAfterTransaction = goldCoinsPlayer - Math.abs(goldenCoinsUsed);

        table.setNumberOfSelectedColorCoins(GOLD, goldCoinsOnTableAfterTransaction);
        table.setNumberOfSelectedColorCoins(color, colorCoinsOnTable + colorCoinsPosesByPlayer);
        currentPlayer.setNumberOfSelectedColorCoins(GOLD, goldCoinsPlayerAfterTransaction);
        currentPlayer.setNumberOfSelectedColorCoins(color, 0);
    }

    boolean hasPlayerEnoughColorPointsToBuyCard(Map<Color, Integer> cardCost) {
        Map<Color, Integer> playerTotalNumberEachColors = currentPlayer.getTotalNumberOfEachColors();
        int numberOfGoldCoins = playerTotalNumberEachColors.get(GOLD);
        for (Color color : getColorsWithoutGold()) {
            int playerTotalNumberOfSelectedColor = playerTotalNumberEachColors.get(color);
            int selectedCardColorCost = cardCost.get(color);
            int subtractColorCardCostWithColorPosesByPlayer = selectedCardColorCost - playerTotalNumberOfSelectedColor;

            if (subtractColorCardCostWithColorPosesByPlayer > 0) {
                if (subtractColorCardCostWithColorPosesByPlayer <= numberOfGoldCoins) {
                    numberOfGoldCoins = numberOfGoldCoins - subtractColorCardCostWithColorPosesByPlayer;
                } else {
                    return false;
                }
            }

        }
        return true;
    }
}
