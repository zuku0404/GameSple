package player;

import card.Card;
import coin.Color;
import hero.Hero;
import lombok.Getter;

import java.util.*;

@Getter
public class Player {
    private int points;
    private Map<Color, Integer> cardsUser = new HashMap<>();
    private Map<Color, Integer> coinsUser = new HashMap<>();
    private List<Hero> heroList = new ArrayList<>();
    private Map<Color, Integer> totalNumberOfEachColors = new HashMap<>();
    private Map<Integer, Card> reservedCardUser;
    private int playerNumber;

    public Player(int playerNumber) {
        this.points = 0;
        this.playerNumber = playerNumber;
        this.reservedCardUser = new HashMap<>();
        for (Color color : Color.values()) {
            if(color == Color.GOLD){
                coinsUser.put(color, 0);
                totalNumberOfEachColors.put(color,coinsUser.get(color));
            }
            else {
                cardsUser.put(color, 0);
                coinsUser.put(color, 0);
                totalNumberOfEachColors.put(color, cardsUser.get(color) + coinsUser.get(color));
            }
        }
    }
    public void takeHero(Hero selectedHero){
        heroList.add(selectedHero);
        points+=selectedHero.getPoints();
    }
    public void takeCard(Card selectedCard){
        cardsUser.replace(selectedCard.getColor(), cardsUser.get(selectedCard.getColor()) + 1);
        points += selectedCard.getPoints();
    }
    public int calculateActualNumberOfPlayerCoins() {
        int numberAllPlayerCoins = 0;
        for(Map.Entry<Color, Integer> color : coinsUser.entrySet()){
            numberAllPlayerCoins += coinsUser.get(color.getKey());
        }
        return numberAllPlayerCoins;
    }

    public int getNumberOfSelectedColorCoins(Color color){
        return coinsUser.get(color);
    }
    public void setNumberOfSelectedColorCoins(Color color, int numberOfCoins){
        coinsUser.replace(color,numberOfCoins);
    }

    public void setTotalNumberOfEachColors() {
        for (Color color : Color.values()) {
            if (color == Color.GOLD) {
                totalNumberOfEachColors.put(color, coinsUser.get(color));
            } else {
                totalNumberOfEachColors.put(color, cardsUser.get(color) + coinsUser.get(color));
            }
        }
    }

    public Map<Color, Integer> getTotalNumberOfEachColors() {
        return totalNumberOfEachColors;
    }
}
