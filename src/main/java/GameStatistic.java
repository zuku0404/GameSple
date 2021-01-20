import java.util.Map;
public class GameStatistic {
    public void showPlayerStatus(Player player) {
    System.out.println("Player number: " + player.getPlayerNumber());
    System.out.println("\nActual status of player: ");
    System.out.println("you have " + player.getPoints() + " points");
    System.out.println("your cards: " + player.getCardsUser().entrySet());
    System.out.println("your coins: " + player.getCoinsUser().entrySet());
    System.out.println("number of hero :  " + player.getHeroList().size() );
    System.out.println("your reserved cards: ");
    showCardsStatistic(player.getReservedCardUser());
}
    public void showTableStatus(Table table) {
        System.out.println("\nTABLE: ");
        System.out.print("Coins on the table:\n ");
        System.out.println(table.getCoinsOnTableMap().entrySet());
        System.out.print("\nCards on the table:\n ");
        showCardsStatistic(table.getCardsOnTableMap());
        System.out.println("Heroes on the table");
        showHeroesStatistic(table.getHeroesOnTableMap());
        }
    private void showCardsStatistic(Map<Integer, Card> mapOfCardToDisplay){
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
    private void showHeroesStatistic (Map<Integer, Hero> mapOfHeroesToDisplay){
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
