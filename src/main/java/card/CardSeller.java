package card;

import coin.Color;
import game.ColorsWithoutGold;
import game.Table;
import player.Player;

import java.util.List;
import java.util.Map;

public class CardSeller {
    private Table table;
    private Player currentPlayer;

    public CardSeller(Table table, Player currentPlayer){
        this.table = table;
        this.currentPlayer = currentPlayer;
    }
    public void buySelectedCard(Card selectedCard) {
        List<Color> colorsListWithoutGold = ColorsWithoutGold.listOfColorsWithoutGold();
        Map<Color, Integer> playerTotalNumberOfEachColors = currentPlayer.getTotalNumberOfEachColors();
        Map<Color, Integer> costSelectedCardMap = selectedCard.getCardCost();

        if (checkPlayerEnoughColorsPoints(playerTotalNumberOfEachColors, costSelectedCardMap, colorsListWithoutGold)) {
            Map<Color, Integer> cardsUserMap = currentPlayer.getCardsUser();
            Map<Color, Integer> coinsUserMap = currentPlayer.getCoinsUser();
            Map<Color, Integer> coinsOnTableMap = table.getCoinsOnTableMap();

            for (Color color : colorsListWithoutGold) {
                int coinsNeededToBuyCard = costSelectedCardMap.get(color) - cardsUserMap.get(color);
                if (coinsNeededToBuyCard > 0) {
                    int coins = coinsUserMap.get(color) - coinsNeededToBuyCard;
                    if(coins<0){
                        coinsOnTableMap.replace(Color.GOLD,coinsOnTableMap.get(Color.GOLD)+Math.abs(coins));
                        coinsOnTableMap.replace(color, coinsOnTableMap.get(color) + coinsUserMap.get(color));
                        coinsUserMap.replace(Color.GOLD,Math.abs(coins));
                        coinsUserMap.replace(color, 0);
                    }
                    else {
                        coinsUserMap.replace(color, coins);
                        coinsOnTableMap.replace(color, coinsOnTableMap.get(color) + coinsNeededToBuyCard);
                    }

                }
            }

        } else {
            throw new IllegalArgumentException("not enough color points");
        }
    }
    private boolean checkPlayerEnoughColorsPoints(Map<Color, Integer> totalNumberOfColors, Map<Color, Integer> cardCost, List<Color> colorsListWithoutGold) {
        int numberOfGoldCoins = totalNumberOfColors.get(Color.GOLD);

        for (Color color : colorsListWithoutGold) {
            int playerTotalNumberOfColor = totalNumberOfColors.get(color);
            int cardColorCost = cardCost.get(color);
            int differencePlayerColorAndCardCost = cardColorCost-playerTotalNumberOfColor;

            if (differencePlayerColorAndCardCost>0) {
                if (differencePlayerColorAndCardCost <= numberOfGoldCoins) {
                    numberOfGoldCoins = numberOfGoldCoins - differencePlayerColorAndCardCost;
                }
                else
                    return false;
            }
        }
        return true;
    }
}
