package game;

import card.*;
import coin.Color;
import hero.Hero;
import hero.HeroSetter;
import player.Player;
import player.PlayerSetter;

import java.util.*;

public class Game {
    private final static int SUFFICIENT_NUMBER_POINTS_TO_WIN = 15;
    private Player[] players;
    private int playersNumber;
    private Table table;
    private boolean isPlayerScoredSufficientPointsToWin;



    private Scanner scanner = new Scanner(System.in);
    private PlayerSetter playerSetter = new PlayerSetter();
    private GameStatistic gameStatistic = new GameStatistic();
    private PlayerMovementOption playerMovementOption = new PlayerMovementOption();
    private CardFromStackTaker cardFromStackTaker = new CardFromStackTaker();

    public Game(int playersNumber) {
        this.playersNumber = playersNumber;
        this.players = playerSetter.createPlayers(playersNumber);

        List<Hero> fourthGroupHero = CardsAndHeroesLoader.createHeroList();
        HeroSetter heroSetter = new HeroSetter(playersNumber, fourthGroupHero);

        Map<Integer, Card> cardsOnTable = cardFromStackTaker.setCardsOnStart();
        Map<Integer, Hero> heroesOnTable = heroSetter.setHeroesOnStart();
        GameSetter gameSetter = new GameSetter();
        Map<Color, Integer> coinsOnTable = gameSetter.setCoinsOnStart(playersNumber);
        this.table = new Table(coinsOnTable, cardsOnTable, heroesOnTable);
    }
    public void runGame() {
        while (!isPlayerScoredSufficientPointsToWin)
            for (int i = 0; i < playersNumber; i++) {
                Player currentPlayer = players[i];
                gameStatistic.showPlayerStatus(currentPlayer);
                gameStatistic.showTableStatus(table);
                int decisionNumberFromPlayer = GameOptionWindow.showDecisionOption();
                playerMovementOption.chooseMove(currentPlayer, table, decisionNumberFromPlayer);
                if (currentPlayer.getPoints() >= SUFFICIENT_NUMBER_POINTS_TO_WIN) {
                    System.out.println("IT IS FINAL ROUND !!");
                    isPlayerScoredSufficientPointsToWin = true;
                }
            }
        System.out.println("Final Score");
        for (Player player : players) {
            System.out.println("player.Player number: " + player.getPlayerNumber() + " scored: " + player.getPoints());
        }
    }
}
