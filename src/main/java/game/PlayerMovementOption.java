package game;

import card.Card;
import card.CardDealer;
import coin.CoinsTaker;
import coin.Color;
import exceptions.InvalidActionException;
import exceptions.InvalidValueException;
import hero.Hero;
import hero.HeroDealer;
import player.Player;

import java.util.Map;
import java.util.Scanner;

import static game.Movement.*;
import static message.Message.*;
import static message.Messenger.*;

public class PlayerMovementOption {
    private Table table;
    private Scanner scanner = new Scanner(System.in);
    private static Map<Integer, Color> mapIdOfColors = Color.createMapIdOfColors();

    public PlayerMovementOption(Table table) {
        this.table = table;
    }

    public boolean selectMove(Player currentPlayer, Movement move) {
        try {
            switch (move) {
                case BUY_CARD:
                    return buyCardAction(currentPlayer);
                case TAKE_TREE_DIFFERENT_COINS:
                    return takeCoinsAction(currentPlayer, move);
                case TAKE_TWO_SAME_COINS:
                    return takeCoinsAction(currentPlayer, move);
                case BUY_HERO:
                    return isPlayerBuyHero(currentPlayer);
                case RESERVE_CARD:
                    return isPlayerReservedCard(currentPlayer);
                case BUY_RESERVED_CARD:
                    return isPlayerBuyReservedCard(currentPlayer);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }

    private boolean isPlayerBuyReservedCard(Player currentPlayer) throws
            InvalidActionException, InvalidValueException {
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
        if (selectedCard != null) {
            cardDealer.buySelectedCard(selectedCard);
            currentPlayer.takeCard(selectedCard);
            currentPlayer.getReservedCardUser().remove(cardNumber);
        } else {
            throw new InvalidValueException("incorrect card number ");
        }
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
        if (selectedHero == null) {
            throw new InvalidValueException("incorrect hero number ");
        }
        HeroDealer heroDealer = new HeroDealer(currentPlayer, table, selectedHero, numberOfHero);
        heroDealer.takeHeroByPlayer();
    }

    private boolean buyCardAction(Player currentPlayer) throws InvalidValueException {
        Messenger.display(Message.BUY_CARD_QUESTION);
        CardDealer cardDealer = new CardDealer(table, currentPlayer);
        int selectedCardNumber = scanner.nextInt();
        Card selectedCard = table.getCardsOnTableMap().get(selectedCardNumber);
        if (selectedCard != null) {
            cardDealer.buySelectedCard(selectedCard);
            currentPlayer.takeCard(selectedCard);
            table.replaceSelectedCard(selectedCard, selectedCardNumber);
            return true;
        } else {
            throw new InvalidValueException("Number of Action not exist");
        }
    }

    private boolean takeCoinsAction(Player currentPlayer, Movement move) throws InvalidValueException {
        System.out.println(mapIdOfColors.entrySet());
        CoinsTaker coinsTaker = new CoinsTaker(table, currentPlayer, move);
        coinsTaker.selectAndTakeCoins();
        return true;
    }

    private void reserveCardAction(Player currentPlayer, int numberOfReservedCards) {
        CoinsTaker goldCoinTaker = new CoinsTaker(table, currentPlayer, RESERVE_CARD);
        int numberOfCardWhichYouWantReserved = scanner.nextInt();
        Card selectedCard = table.getCardsOnTableMap().get(numberOfCardWhichYouWantReserved);
        currentPlayer.getReservedCardUser().put(numberOfReservedCards + 1, selectedCard);
        table.replaceSelectedCard(selectedCard, numberOfCardWhichYouWantReserved);
        goldCoinTaker.takeGoldCoin();
    }
}