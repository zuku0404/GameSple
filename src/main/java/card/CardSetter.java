package card;

import converter.JsonReader;

import java.util.*;

public class CardSetter {
    private Random random = new Random();
    private static List<Card> firstGroupCards = JsonReader.createListFirstGroupCards();
    private static List<Card> secondGroupCards = JsonReader.createListSecondGroupCards();
    private static List<Card> thirdGroupCards = JsonReader.createListThirdGroupCards();

    public Map<Integer, Card> setCardsOnStart() {
        Map<Integer, Card> cardsOnTheTable = new HashMap<>();
        for (int numberOfCard = 1; numberOfCard <= 4; numberOfCard++) {
            cardsOnTheTable.put(numberOfCard, takeRandomCardFromStack(firstGroupCards));
            cardsOnTheTable.put(numberOfCard + 4, takeRandomCardFromStack(secondGroupCards));
            cardsOnTheTable.put(numberOfCard + 8, takeRandomCardFromStack(thirdGroupCards));
        }
        return cardsOnTheTable;
    }

    public Optional<Card> takeCard(Card selectedCard) {
        int groupOfSelectedCard = selectedCard.getGroup();
        List<Card> selectedGroupCards;
        if (groupOfSelectedCard == 1) {
            selectedGroupCards = firstGroupCards;
        } else if (groupOfSelectedCard == 2) {
            selectedGroupCards = secondGroupCards;
        } else {
            selectedGroupCards = thirdGroupCards;
        }
        return isStackEmpty(selectedGroupCards);
    }

    private Optional<Card> isStackEmpty(List<Card> selectedGroupCards) {
        if (selectedGroupCards.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(takeRandomCardFromStack(selectedGroupCards));
        }
    }

    private Card takeRandomCardFromStack(List<Card> cardsOnStacks) {
        int indexOfCard = random.nextInt(cardsOnStacks.size());
        Card returnedCard = cardsOnStacks.get(indexOfCard);
        cardsOnStacks.remove(returnedCard);
        return returnedCard;
    }
}