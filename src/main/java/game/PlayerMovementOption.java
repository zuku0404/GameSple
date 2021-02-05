package game;

import card.Card;
import card.CardDealer;
import coin.CoinsTaker;
import coin.Color;
import exceptions.InvalidActionException;
import exceptions.InvalidValueException;
import hero.Hero;
import hero.HeroDealer;
import message.Message;
import message.Messenger;
import player.Player;

import java.util.Map;
import java.util.Scanner;

public class PlayerMovementOption {
    private Table table;
    private Scanner scanner = new Scanner(System.in);
    private static Map<Integer, Color> mapIdOfColors = Color.createMapIdOfColors();

    public PlayerMovementOption(Table table) {
        this.table = table;
    }

    public void selectMove(Player currentPlayer, int numberOfDecision) {
        boolean isActionCompleteSuccessful = false;
        while (!isActionCompleteSuccessful) {
            try {
                if (numberOfDecision == 1) {
                    isActionCompleteSuccessful = buyCardAction(currentPlayer);

                } else if (numberOfDecision == 2) {
                    int minimalNumberCoinsOnStackOnTable = 1;
                    int amountCoinsToChangeFromSingleStack = 1;
                    int maxAmountCoinsToTakeInSingleMove = 3;
                    Messenger.display(Message.TAKE_THREE_COIN_QUESTION);

                    isActionCompleteSuccessful = takeCoinsAction(currentPlayer, minimalNumberCoinsOnStackOnTable,
                            amountCoinsToChangeFromSingleStack, maxAmountCoinsToTakeInSingleMove);

                } else if (numberOfDecision == 3) {
                    int minimalNumberCoinsOnStackOnTable = 4;
                    int amountCoinsToChangeFromSingleStack = 2;
                    int maxAmountCoinsToTakeInSingleMove = 2;
                    Messenger.display(Message.TAKE_TWO_COIN_QUESTION);

                    isActionCompleteSuccessful = takeCoinsAction(currentPlayer, minimalNumberCoinsOnStackOnTable,
                            amountCoinsToChangeFromSingleStack, maxAmountCoinsToTakeInSingleMove);

                } else if (numberOfDecision == 4) {
                    isActionCompleteSuccessful = isPlayerBuyHero(currentPlayer);

                } else if (numberOfDecision == 5) {
                    isActionCompleteSuccessful = isPlayerReservedCard(currentPlayer);

                } else if (numberOfDecision == 6) {
                    isActionCompleteSuccessful = isPlayerBuyReservedCard(currentPlayer);

                } else {
                    throw new InvalidValueException("Action not exist");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                numberOfDecision = GameStatistic.showDecisionOption();
            }
        }
    }

    private boolean isPlayerBuyReservedCard(Player currentPlayer) throws InvalidActionException, InvalidValueException {
        if (currentPlayer.getReservedCardUser().size() == 0) {
            throw new InvalidActionException("you do not have any cards reserved");
        } else {
            Messenger.display(Message.BUY_RESERVED_CARD_QUESTION);
            buyReservedCardAction(currentPlayer);
            return true;
        }
    }

    private void buyReservedCardAction(Player currentPlayer) throws InvalidValueException {
        CardDealer cardDealer = new CardDealer(table, currentPlayer);
        int cardNumber = scanner.nextInt();
        Card selectedCard = currentPlayer.getReservedCardUser().get(cardNumber);
        cardDealer.buySelectedCard(selectedCard);
        currentPlayer.takeCard(selectedCard);
        currentPlayer.getReservedCardUser().remove(cardNumber);
    }

    private boolean isPlayerReservedCard(Player currentPlayer) throws InvalidActionException {
        int numberOfReservedCards = currentPlayer.getReservedCardUser().size();
        if (numberOfReservedCards >= 3) {
            throw new InvalidActionException("too many reserved card");
        } else {
            Messenger.display(Message.RESERVE_CARD_QUESTION);
            reserveCardAction(currentPlayer, numberOfReservedCards);
            return true;
        }
    }

    private boolean isPlayerBuyHero(Player currentPlayer) throws InvalidActionException, InvalidValueException {
        if (table.getHeroesOnTableMap().isEmpty()) {
            throw new InvalidActionException("all heroes have been bought");
        } else {
            Messenger.display(Message.TAKE_HERO_QUESTION);
            buyHeroAction(currentPlayer);
            return true;
        }
    }

    private void buyHeroAction(Player currentPlayer) throws InvalidValueException {
            int numberOfHero = scanner.nextInt();
            Hero selectedHero = table.getHeroesOnTableMap().get(numberOfHero);
            if(selectedHero==null){
                throw new InvalidValueException("incorrect number of hero");
            }
            HeroDealer heroDealer = new HeroDealer(currentPlayer, table, selectedHero, numberOfHero);
            heroDealer.takeHeroByPlayer();
        }

    private boolean buyCardAction(Player currentPlayer) throws InvalidValueException {
        Messenger.display(Message.BUY_CARD_QUESTION);
        CardDealer cardDealer = new CardDealer(table, currentPlayer);
        int selectedCardNumber = scanner.nextInt();
        Card selectedCard = table.getCardsOnTableMap().get(selectedCardNumber);
        cardDealer.buySelectedCard(selectedCard);
        currentPlayer.takeCard(selectedCard);
        table.replaceSelectedCard(selectedCard, selectedCardNumber);
        return true;
    }

    private boolean takeCoinsAction(Player currentPlayer, int minimalNumberCoinsOnStackOnTable, int amountCoinsToChangeFromSingleStack,
                                    int maxAmountCoinsToTakeInSingleMove) throws InvalidValueException {
        System.out.println(mapIdOfColors.entrySet());
        CoinsTaker coinsTaker = new CoinsTaker(minimalNumberCoinsOnStackOnTable, amountCoinsToChangeFromSingleStack,
                maxAmountCoinsToTakeInSingleMove, table, currentPlayer);
        coinsTaker.selectAndTakeCoins();
        return true;
    }

    private void reserveCardAction(Player currentPlayer, int numberOfReservedCards) {
        CoinsTaker goldCoinTaker = new CoinsTaker(currentPlayer, table);
        int numberOfCardWhichYouWantReserved = scanner.nextInt();
        Card selectedCard = table.getCardsOnTableMap().get(numberOfCardWhichYouWantReserved);
        currentPlayer.getReservedCardUser().put(numberOfReservedCards + 1, selectedCard);
        table.replaceSelectedCard(selectedCard, numberOfCardWhichYouWantReserved);
        goldCoinTaker.takeGoldCoin();
    }
}