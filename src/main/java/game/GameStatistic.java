package game;

import card.Card;
import hero.Hero;
import player.Player;

import java.util.Map;

public class GameStatistic {
    public void showPlayerStatus(Player player) {
        System.out.println("player.Player number: " + player.getPlayerNumber() + "\n" +
                "\nActual status of player: " +
                "\nyou have " + player.getPoints() + " points" +
                "\nyour cards: " + player.getCardsUser().entrySet() +
                "\nyour coins: " + player.getCoinsUser().entrySet() +
                "\nnumber of hero :  " + player.getHeroList().size() +
                "\nyour reserved cards: ");
        showCardsStatistic(player.getReservedCardUser());
    }

    public void showTableStatus(Table table) {
        System.out.println("\nTABLE: " +
                "\nCoins on the table:\n " +
                table.getCoinsOnTableMap().entrySet() +
                "\nCards on the table:\n ");
                showCardsStatistic(table.getCardsOnTableMap());
        System.out.println("Heroes on the table");
        showHeroesStatistic(table.getHeroesOnTableMap());
    }

    private void showCardsStatistic(Map<Integer, Card> mapOfCardToDisplay) {
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.printf("%10s %10s %10s %10s %30s", "NUMBER", "COLOR", "POINTS", "GROUP", "COST");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (Integer cardOnTableKey : mapOfCardToDisplay.keySet()) {
            System.out.format("%7d %13s %7d %11d %50s",
                    cardOnTableKey, mapOfCardToDisplay.get(cardOnTableKey).getCardColor(),
                    mapOfCardToDisplay.get(cardOnTableKey).getPoints(), mapOfCardToDisplay.get(cardOnTableKey).getGroup(),
                    mapOfCardToDisplay.get(cardOnTableKey).getCardCost().entrySet().toString());
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    private void showHeroesStatistic(Map<Integer, Hero> mapOfHeroesToDisplay) {
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.printf("%10s %13s %30s", "NUMBER", "POINTS", "COST");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (Integer heroOnTableKey : mapOfHeroesToDisplay.keySet()) {
            System.out.format("%7d %13s %50s",
                    heroOnTableKey, mapOfHeroesToDisplay.get(heroOnTableKey).getPoints(),
                    mapOfHeroesToDisplay.get(heroOnTableKey).getCostHero().entrySet().toString());
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------------------------------");

    }
}
