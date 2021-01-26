package game;

import card.Card;
import card.CardSeller;
import coin.CoinsTaker;
import coin.Color;
import coin.GoldCoinTaker;
import hero.Hero;
import hero.HeroPicker;
import javafx.scene.control.Tab;
import player.Player;

import java.util.Map;
import java.util.Scanner;

public class PlayerMovementOption {
    private Scanner scanner = new Scanner(System.in);
    private static Map<Integer, Color> coinsIdMap = GameSetter.setCoinsWithIdMap();

    public void chooseMove(Player currentPlayer, Table table, int numberOfDecision) {
        boolean isActionCompleteSuccessful = false;
        while (!isActionCompleteSuccessful) {
            if (numberOfDecision == 1) {
                CardSeller cardSeller = new CardSeller(table, currentPlayer);
                System.out.print("Which card would you like to buy (Enter number): ");
                try {
                    int selectedCardNumber = scanner.nextInt();
                    Card selectedCard = table.getCardsOnTableMap().get(selectedCardNumber);
                    cardSeller.buySelectedCard(selectedCard);
                    currentPlayer.takeCard(selectedCard);
//                    currentPlayer.getCardsUser().replace(selectedCard.getCardColor(), currentPlayer.getCardsUser().get(selectedCard.getCardColor()) + 1);
//                    currentPlayer.setPoints(currentPlayer.getPoints() + selectedCard.getPoints());
                    table.putNextCardOnTable(selectedCard, selectedCardNumber);
//                    if (cardTaker.takeCard(selectedCard) != null) {
//                        table.getCardsOnTableMap().replace(selectedCardNumber, cardTaker.takeCard(selectedCard));
//                    } else {
//                        table.getCardsOnTableMap().remove(selectedCardNumber);
//                    }
                    isActionCompleteSuccessful = true;
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    numberOfDecision = GameOptionWindow.showDecisionOption();
                }
            } else if (numberOfDecision == 2) {
                int minimalNumberCoinsOnStackOnTable = 1;
                int amountCoinsToChangeFromSingleStack = 1;
                int maxAmountCoinsToTakeInSingleMove = 3;

                System.out.println("Which three different coins would you like to take");
                try {
                    isActionCompleteSuccessful = showProcedura(currentPlayer, table, minimalNumberCoinsOnStackOnTable,
                            amountCoinsToChangeFromSingleStack, maxAmountCoinsToTakeInSingleMove);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    numberOfDecision = GameOptionWindow.showDecisionOption();
                }

            } else if (numberOfDecision == 3) {
                int minimalNumberCoinsOnStackOnTable = 4;
                int amountCoinsToChangeFromSingleStack = 2;
                int maxAmountCoinsToTakeInSingleMove = 2;

                System.out.println("Which two coins would you like to take");
                try {
                    isActionCompleteSuccessful = showProcedura(currentPlayer, table, minimalNumberCoinsOnStackOnTable,
                            amountCoinsToChangeFromSingleStack, maxAmountCoinsToTakeInSingleMove);
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
                        HeroPicker heroPicker = new HeroPicker(currentPlayer, table, selectedHero, numberOfHero);
                        heroPicker.takeHeroByPlayer();
                        isActionCompleteSuccessful = true;
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

                    table.putNextCardOnTable(selectedCard, numberOfCardWhichYouWantReserved);
//                    if (cardTaker.takeCard(selectedCard) != null) {
//                        table.getCardsOnTableMap().replace(numberOfCardWhichYouWantReserved, cardTaker.takeCard(selectedCard));
//                    } else {
//                        table.getCardsOnTableMap().remove(numberOfCardWhichYouWantReserved);
//                    }
                    goldCoinTaker.takeOrNotGoldCoin(currentPlayer, table);
                    isActionCompleteSuccessful = true;
                }
            } else if (numberOfDecision == 6) {
                if (currentPlayer.getReservedCardUser().size() == 0) {
                    System.out.println("you do not have any cards reserved");
                    numberOfDecision = GameOptionWindow.showDecisionOption();
                } else {
                    CardSeller cardSeller = new CardSeller(table, currentPlayer);
                    System.out.print("Which card would you like to buy (Enter number): ");
                    try {
                        int cardNumber = scanner.nextInt();
                        Card selectedCard = currentPlayer.getReservedCardUser().get(cardNumber);
                        cardSeller.buySelectedCard(selectedCard);

                        currentPlayer.takeCard(selectedCard);

//                        currentPlayer.getCardsUser().replace(selectedCard.getCardColor(), currentPlayer.getCardsUser().get(selectedCard.getCardColor()) + 1);
//                        currentPlayer.setPoints(currentPlayer.getPoints() + selectedCard.getPoints());
                        currentPlayer.getReservedCardUser().remove(cardNumber);
                        isActionCompleteSuccessful = true;
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        numberOfDecision = GameOptionWindow.showDecisionOption();
                    }
                }
            } else {

                System.out.println("Number not exist try again: ");
                numberOfDecision = GameOptionWindow.showDecisionOption();
            }
        }

    }
    private boolean showProcedura(Player currentPlayer, Table table, int minimalNumberCoinsOnStackOnTable, int amountCoinsToChangeFromSingleStack, int maxAmountCoinsToTakeInSingleMove) {
        System.out.println(coinsIdMap.entrySet());
        CoinsTaker coinsTaker = new CoinsTaker(minimalNumberCoinsOnStackOnTable, amountCoinsToChangeFromSingleStack,
                maxAmountCoinsToTakeInSingleMove, table, currentPlayer);
        coinsTaker.chooseAndTakeCoins();
        return true;
    }
}