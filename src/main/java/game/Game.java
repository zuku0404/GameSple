package game;

import card.*;
import coin.Color;
import coin.CoinsSetter;
import exceptions.InvalidValueException;
import hero.Hero;
import hero.HeroSetter;
import message.Message;
import player.Player;
import player.PlayersCreator;

import java.util.*;

import static message.Messenger.*;

public class Game {
    private Player[] players;
    private Table table;
    private GameStatistic gameStatistic = new GameStatistic();
    private PlayerMovementOption playerMovementOption;

    public Game(int numberOfPlayers) throws InvalidValueException {
        PlayersCreator playersCreator = new PlayersCreator();
        this.players = playersCreator.createPlayers(numberOfPlayers);
        HeroSetter heroSetter = new HeroSetter(numberOfPlayers);
        Map<Integer, Hero> heroesOnTable = heroSetter.setHeroesOnStart();
        CardSetter cardSetter = new CardSetter();
        Map<Integer, Card> cardsOnTable = cardSetter.setCardsOnStart();
        CoinsSetter coinsSetter = new CoinsSetter();
        Map<Color, Integer> coinsOnTable = coinsSetter.setCoinsOnStart(numberOfPlayers);
        this.table = new Table(coinsOnTable, cardsOnTable, heroesOnTable);
        this.playerMovementOption = new PlayerMovementOption(table);
    }

    public void runGame() {
        boolean isPlayerScoredSufficientPointsToWin = false;
        while (!isPlayerScoredSufficientPointsToWin)
            for (Player currentPlayer : players) {
                if (makeSingleMove(currentPlayer)) {
                    isPlayerScoredSufficientPointsToWin = true;
                }
                currentPlayer.setTotalNumberOfEachColors();
            }
        showFinalScore();
    }

    private void showFinalScore() {
        display(Message.FINAL_SCORE_TAG);
        for (Player player : players) {
            display(Message.FINAL_SCORE_PLAYER, player.getPlayerNumber(), player.getPoints());
        }
    }

    private boolean makeSingleMove(Player currentPlayer) {
        gameStatistic.showPlayerStatus(currentPlayer);
        gameStatistic.showTableStatus(table);
        boolean isActionCompleteSuccessful = false;
        while (!isActionCompleteSuccessful) {
            Optional<Movement> movement = GameStatistic.showDecisionOption();
            if (movement.isPresent()) {
                Movement move = movement.get();
                isActionCompleteSuccessful = playerMovementOption.selectMove(currentPlayer, move);
            }
        }
        return checkIsFinalRound(currentPlayer);
    }

    private boolean checkIsFinalRound(Player currentPlayer) {
        int sufficientNumberPointsToWin = 15;
        if (currentPlayer.getPoints() >= sufficientNumberPointsToWin) {
            display(Message.FINAL_ROUND_TAG);
            return true;
        }
        return false;
    }
}
