
import java.util.*;

public class Game {
    private Player[] players;
    private int playersNumber;
    private Player currentPlayer;
    private Table table;
    private boolean isPlayerScoredSufficientPointsToWin;
    private int sufficientNumberOfPointsToWin = 15;

    private Map<Integer, Color> coinsIdMap;

    private Scanner scanner = new Scanner(System.in);
    private PlayerSetter playerSetter = new PlayerSetter();
    private GameStatistic gameStatistic = new GameStatistic();
    private CardTaker cardTaker;

    public Game(int playersNumber) {
        this.playersNumber = playersNumber;
        this.players = playerSetter.createPlayers(playersNumber);

        List<Card> firstGroupCards = CardsAndHeroesLoader.createCardListFirstGroup();
        List<Card> secondGroupCards = CardsAndHeroesLoader.createSecondGroupCards();
        List<Card> thirdGroupCards = CardsAndHeroesLoader.createThirdGroupCards();
        List<Hero> fourthGroupHero = CardsAndHeroesLoader.createHeroList();

        cardTaker = new CardTaker(firstGroupCards, secondGroupCards, thirdGroupCards);
        HeroSetter heroSetter = new HeroSetter(playersNumber, fourthGroupHero);

        Map<Integer, Card> cardsOnTable = CardSetter.setCardsOnStart(firstGroupCards, secondGroupCards, thirdGroupCards, cardTaker);
        Map<Integer, Hero> heroesOnTable = heroSetter.setHeroesOnStart();
        GameSetter gameSetter = new GameSetter();
        Map<Color, Integer> coinsOnTable = gameSetter.setCoinsOnStart(playersNumber);

        this.table = new Table(coinsOnTable, cardsOnTable, heroesOnTable);
        coinsIdMap = gameSetter.setCoinsWithIdMap();
    }

    public void runGame() {
        while (!isPlayerScoredSufficientPointsToWin)
            for (int i = 0; i < playersNumber; i++) {
                boolean isActionCompleted = false;
                currentPlayer = players[i];
                gameStatistic.showPlayerStatus(currentPlayer);
                gameStatistic.showTableStatus(table);
                int numberOfDecision = GameOptionWindow.showDecisionOption();
                makeDecision(numberOfDecision, isActionCompleted);
                if (currentPlayer.getPoints() >= sufficientNumberOfPointsToWin) {
                    System.out.println("IT IS FINAL ROUND !!");
                    isPlayerScoredSufficientPointsToWin = true;
                }
            }
        System.out.println("Final Score");
        for (Player player : players) {
            System.out.println("Player number: " + player.getPlayerNumber() + " scored: " + player.getPoints());
        }
    }

    private void makeDecision(int numberOfDecision, boolean isActionCompleted) {
        while (!isActionCompleted) {
            if (numberOfDecision == 1) {
                try {
                    CardSeller cardSeller = new CardSeller(table, currentPlayer, cardTaker);
                    System.out.print("Which card would you like to buy (Enter number): ");
                    int cardNumber = scanner.nextInt();
                    Card selectedCard = table.getCardsOnTableMap().get(cardNumber);
                    cardSeller.buySelectedCard(selectedCard);
                    currentPlayer.getCardsUser().replace(selectedCard.getCardColor(), currentPlayer.getCardsUser().get(selectedCard.getCardColor()) + 1);
                    currentPlayer.setPoints(currentPlayer.getPoints() + selectedCard.getPoints());
                    if (cardTaker.takeCard(selectedCard) != null) {
                        table.getCardsOnTableMap().replace(cardNumber, cardTaker.takeCard(selectedCard));
                    } else {
                        table.getCardsOnTableMap().remove(cardNumber);
                    }
                    isActionCompleted = true;
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                    numberOfDecision = GameOptionWindow.showDecisionOption();
                } catch (Exception ex) {
                    System.out.println("Number of card not exist");
                    numberOfDecision = GameOptionWindow.showDecisionOption();
                }
            } else if (numberOfDecision == 2) {

                int minimalNumberCoinsOnStackOnTable = 1;
                int amountCoinsToChangeFromSingleStack = 1;
                int maxAmountCoinsToTakeInSingleMove = 3;

                System.out.println("Which three different coins would you like to take");
                System.out.println(coinsIdMap.entrySet());
                try {
                    CoinsTaker coinsTaker = new CoinsTaker(minimalNumberCoinsOnStackOnTable, amountCoinsToChangeFromSingleStack,
                            maxAmountCoinsToTakeInSingleMove, table, currentPlayer, coinsIdMap);
                    coinsTaker.chooseAndTakeCoins();
                    isActionCompleted = true;
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    numberOfDecision = GameOptionWindow.showDecisionOption();
                }
            } else if (numberOfDecision == 3) {
                int minimalNumberCoinsOnStackOnTable = 4;
                int amountCoinsToChangeFromSingleStack = 2;
                int maxAmountCoinsToTakeInSingleMove = 2;

                System.out.println("Which two coins would you like to take");
                System.out.println(coinsIdMap.entrySet());
                try {
                    CoinsTaker coinsTaker = new CoinsTaker(minimalNumberCoinsOnStackOnTable, amountCoinsToChangeFromSingleStack,
                            maxAmountCoinsToTakeInSingleMove, table, currentPlayer, coinsIdMap);
                    coinsTaker.chooseAndTakeCoins();
                    isActionCompleted = true;
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    numberOfDecision = GameOptionWindow.showDecisionOption();
                }

            } else if (numberOfDecision == 4) {
                if (table.getHeroesOnTableMap().isEmpty()) {
                    System.out.println("all heroes have been bought");
                    numberOfDecision = GameOptionWindow.showDecisionOption();
                } else {
                    try {
                        System.out.print("Which hero would you like to buy: ");
                        int numberOfHero = scanner.nextInt();
                        Hero selectedHero = table.getHeroesOnTableMap().get(numberOfHero);
                        HeroTaker heroTaker = new HeroTaker(currentPlayer, table, selectedHero, numberOfHero);
                        heroTaker.takeHero();
                        isActionCompleted = true;
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    } catch (Exception ex) {
                        System.out.println("incorrect input !!");
                        numberOfDecision = GameOptionWindow.showDecisionOption();
                    }
                }
            } else if (numberOfDecision == 5) {
                int numberOfReservedCards = currentPlayer.getReservedCardUser().size();
                if (numberOfReservedCards >= 3) {
                    System.out.println("too many reserved card");
                    numberOfDecision = GameOptionWindow.showDecisionOption();
                } else {
                    GoldCoinTaker goldCoinTaker = new GoldCoinTaker();
                    System.out.print("Which card do you want reserve: ");
                    int numberOfCardWhichYouWantReserved = scanner.nextInt();
                    Card selectedCard = table.getCardsOnTableMap().get(numberOfCardWhichYouWantReserved);
                    currentPlayer.getReservedCardUser().put(numberOfReservedCards + 1, selectedCard);
                    if (cardTaker.takeCard(selectedCard) != null) {
                        table.getCardsOnTableMap().replace(numberOfCardWhichYouWantReserved, cardTaker.takeCard(selectedCard));
                    } else {
                        table.getCardsOnTableMap().remove(numberOfCardWhichYouWantReserved);
                    }
                    goldCoinTaker.takeOrNotGoldCoin(currentPlayer, table);
                    isActionCompleted = true;
                }
            } else if (numberOfDecision == 6) {
                if (currentPlayer.getReservedCardUser().size() == 0) {
                    System.out.println("you do not have any cards reserved");
                    numberOfDecision = GameOptionWindow.showDecisionOption();
                } else {
                    try {
                        CardSeller cardSeller = new CardSeller(table, currentPlayer, cardTaker);
                        System.out.print("Which card would you like to buy (Enter number): ");
                        int cardNumber = scanner.nextInt();
                        Card selectedCard = currentPlayer.getReservedCardUser().get(cardNumber);
                        cardSeller.buySelectedCard(selectedCard);
                        currentPlayer.getCardsUser().replace(selectedCard.getCardColor(), currentPlayer.getCardsUser().get(selectedCard.getCardColor()) + 1);
                        currentPlayer.setPoints(currentPlayer.getPoints() + selectedCard.getPoints());
                        currentPlayer.getReservedCardUser().remove(cardNumber);
                        isActionCompleted = true;
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                        numberOfDecision = GameOptionWindow.showDecisionOption();
                    } catch (Exception ex) {
                        System.out.println("Number of card not exist");
                        numberOfDecision = GameOptionWindow.showDecisionOption();
                    }
                }
            } else {

                System.out.println("Number not exist try again: ");
                numberOfDecision = GameOptionWindow.showDecisionOption();
            }
        }

    }
}