package game;

import card.Card;
import card.CardFromStackTaker;
import coin.Color;
import hero.Hero;
import lombok.Getter;

import java.util.Map;

@Getter
public class Table {
    private Map<Color, Integer> coinsOnTableMap;
    private Map<Integer, Card> cardsOnTableMap;
    private Map<Integer, Hero> heroesOnTableMap;

    public Table(Map<Color, Integer> coinsOnTableMap, Map<Integer, Card> cardsOnTableMap, Map<Integer, Hero> heroesOnTableMap) {
        this.coinsOnTableMap = coinsOnTableMap;
        this.cardsOnTableMap = cardsOnTableMap;
        this.heroesOnTableMap = heroesOnTableMap;
    }

    public void removeHeroFromTable(int numberOfHero) {
        heroesOnTableMap.remove(numberOfHero);
    }

    public void putNextCardOnTable(Card selectedCard, int selectedCardNumber){
        CardFromStackTaker cardFromStackTaker = new CardFromStackTaker();
        if (cardFromStackTaker.takeCard(selectedCard) != null) {
                        cardsOnTableMap.replace(selectedCardNumber, cardFromStackTaker.takeCard(selectedCard));
                    } else {
                        cardsOnTableMap.remove(selectedCardNumber);
                    }
    }
}

