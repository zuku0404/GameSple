package card;

import game.CardsAndHeroesLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CardFromStackTaker {
    private Random random = new Random();
    private static List<Card> firstGroupCards = CardsAndHeroesLoader.createCardListFirstGroup();
    private static List<Card> secondGroupCards = CardsAndHeroesLoader.createSecondGroupCards();
    private static List<Card> thirdGroupCards = CardsAndHeroesLoader.createThirdGroupCards();

    public Card takeCard(Card selectedCard) {
        int groupOfSelectedCard = selectedCard.getGroup();
        List<Card> selectedGroupCards;

        if (groupOfSelectedCard == 1) {
            selectedGroupCards = firstGroupCards;
        } else if (groupOfSelectedCard == 2) {
            selectedGroupCards = secondGroupCards;
        } else {
            selectedGroupCards = thirdGroupCards;
        }
        return takeCardFromStack(selectedGroupCards);
    }

    public Map<Integer, Card> setCardsOnStart() {
        Map<Integer, Card> cardsOnTheTable = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            int keyForFirstGroup = i + 1;
            cardsOnTheTable.put(keyForFirstGroup, takeCardFromStack(firstGroupCards));
            cardsOnTheTable.put(keyForFirstGroup + 4, takeCardFromStack(secondGroupCards));
            cardsOnTheTable.put(keyForFirstGroup + 8, takeCardFromStack(thirdGroupCards));
        }
        return cardsOnTheTable;
    }

    private Card takeCardFromStack(List<Card> cardsOnStacks) {
        if (cardsOnStacks.isEmpty()) {
            return null;
        }
        int indexOfCard = random.nextInt(cardsOnStacks.size());
        Card returnedCard = cardsOnStacks.get(indexOfCard);
        cardsOnStacks.remove(returnedCard);
        return returnedCard;
    }
}