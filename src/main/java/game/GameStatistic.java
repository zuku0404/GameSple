package game;

import card.Card;
import hero.Hero;

import player.Player;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import static message.Message.*;
import static message.Messenger.*;

public class GameStatistic {
    private static Scanner scanner = new Scanner(System.in);

    public void showPlayerStatus(Player player) {
        display(PLAYER_STATUS, String.valueOf(player.getPlayerNumber()), String.valueOf(player.getPoints()),
                player.getCardsUser().entrySet().toString(), player.getCoinsUser().entrySet().toString(),
                String.valueOf(player.getHeroList().size()));
        showCardsStatistic(player.getReservedCardUser());
    }

    public void showTableStatus(Table table) {
        display(TABLE_STATISTIC_FIRST_PART, table.getCoinsOnTableMap().entrySet().toString());
        showCardsStatistic(table.getCardsOnTableMap());
        display(TABLE_STATISTIC_SECOND_PART);
        showHeroesStatistic(table.getHeroesOnTableMap());
    }

    private void showCardsStatistic(Map<Integer, Card> mapOfCardToDisplay) {
        display(DASHED_LINE);
        display(CARD_HEADER_TABLE, "NUMBER", "COLOR", "POINTS", "GROUP", "COST");
        display(DASHED_LINE);
        for(Map.Entry<Integer,Card> cardOnTable : mapOfCardToDisplay.entrySet()){
            display(CARD_STATISTIC, String.valueOf(cardOnTable.getKey()), String.valueOf(mapOfCardToDisplay.get(cardOnTable.getKey()).getColor()),
                    String.valueOf(mapOfCardToDisplay.get(cardOnTable.getKey()).getPoints()), String.valueOf(mapOfCardToDisplay.get(cardOnTable.getKey()).getGroup()),
                    mapOfCardToDisplay.get(cardOnTable.getKey()).getCost().entrySet().toString());
        }
        display(DASHED_LINE);
    }

    private void showHeroesStatistic(Map<Integer, Hero> mapOfHeroesToDisplay) {
        display(DASHED_LINE);
        display(HERO_HEADER_TABLE, "NUMBER", "POINTS", "COST");
        display(DASHED_LINE);
        for(Map.Entry<Integer,Hero> heroOnTable : mapOfHeroesToDisplay.entrySet()){
            display(HERO_STATISTIC, String.valueOf(heroOnTable.getKey()), String.valueOf(
                    mapOfHeroesToDisplay.get(heroOnTable.getKey()).getPoints()),
                    mapOfHeroesToDisplay.get(heroOnTable.getKey()).getCostHero().entrySet().toString());
        }
        display(DASHED_LINE);
    }

    public static Optional<Movement> showDecisionOption() {
        display(DECISION_WINDOW);
        return Movement.choseMove(scanner.nextInt());
    }
}
