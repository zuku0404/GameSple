package card;

import coin.Color;
import exceptions.InvalidValueException;
import game.Table;
import player.Player;
import java.util.Map;

public class CardDealer {
    private Table table;
    private Player currentPlayer;

    public CardDealer(Table table, Player currentPlayer) {
        this.table = table;
        this.currentPlayer = currentPlayer;
    }

    public void buySelectedCard(Card selectedCard) throws InvalidValueException {
        Map<Color, Integer> selectedCardCost = selectedCard.getCardCost();
        if (hasPlayerEnoughColorPointsToBuyCard(selectedCardCost)) {
            checkPlayerNeedUseCoins(selectedCardCost);
        } else {
            throw new InvalidValueException("not enough color points");
        }
    }

    void checkPlayerNeedUseCoins(Map<Color, Integer> selectedCardCost) {
        Map<Color, Integer> playerCards = currentPlayer.getCardsUser();
        for (Color color : Color.getColorsWithoutGold()) {
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
            useColorCoinsWithGoldCoins(color, coins, colorCoinsOnTable, colorCoinsPosesByPlayer);

        } else {
            useOnlyColorCoins(color, coins, colorCoinsOnTable, colorCoinsPosesByPlayer);
        }
    }

    void useOnlyColorCoins(Color color, int coins, int colorCoinsOnTable, int coinsNeededToBuyCard) {
        currentPlayer.setNumberOfSelectedColorCoins(color, coins);
        table.setNumberOfSelectedColorCoins(color, colorCoinsOnTable + coinsNeededToBuyCard);
    }

    void useColorCoinsWithGoldCoins(Color color, int coins, int colorCoinsOnTable, int colorCoinsPosesByPlayer) {
        int goldCoinsOnTable = table.getNumberOfSelectedColorCoins(Color.GOLD);
        int goldCoinsPlayer = currentPlayer.getNumberOfSelectedColorCoins(Color.GOLD);
        int goldCoinsOnTableAfterTransaction = goldCoinsOnTable + Math.abs(coins);
        int goldCoinsPlayerAfterTransaction = goldCoinsPlayer - Math.abs(coins);

        table.setNumberOfSelectedColorCoins(Color.GOLD, goldCoinsOnTableAfterTransaction);
        table.setNumberOfSelectedColorCoins(color, colorCoinsOnTable + colorCoinsPosesByPlayer);
        currentPlayer.setNumberOfSelectedColorCoins(Color.GOLD, goldCoinsPlayerAfterTransaction);
        currentPlayer.setNumberOfSelectedColorCoins(color, 0);
    }

    boolean hasPlayerEnoughColorPointsToBuyCard(Map<Color, Integer> cardCost) {
        Map<Color, Integer> playerTotalNumberEachColors = currentPlayer.getTotalNumberOfEachColors();
        int numberOfGoldCoins = playerTotalNumberEachColors.get(Color.GOLD);
        for (Color color : Color.getColorsWithoutGold()) {
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
