package game;

import card.Card;
import card.CardSetter;
import coin.Color;
import hero.Hero;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;

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

    public void removeHero(int numberOfHero) {
        heroesOnTableMap.remove(numberOfHero);
    }

    public void replaceSelectedCard(Card selectedCard, int selectedCardNumber) {
        CardSetter cardSetter = new CardSetter();
        Optional<Card> card = cardSetter.takeCard(selectedCard);
        if (card.isPresent()) {
            cardsOnTableMap.replace(selectedCardNumber, card.get());
        } else {
            cardsOnTableMap.remove(selectedCardNumber);
        }
    }

    public int getNumberOfSelectedColorCoins(Color color) {
        return coinsOnTableMap.get(color);
    }

    public void setNumberOfSelectedColorCoins(Color color, int numberOfCoins) {
        coinsOnTableMap.replace(color, numberOfCoins);
    }
}

