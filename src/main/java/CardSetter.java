import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardSetter {
    public static Map<Integer, Card> setCardsOnStart (List<Card>firstGroupCards, List<Card>secondGroupCards, List<Card> thirdGroupCards, CardTaker cardTaker) {
        Map<Integer, Card> cardsOnTheTable = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            int keyForFirstGroup = i + 1;
            cardsOnTheTable.put(keyForFirstGroup, cardTaker.takeCardFromStack(firstGroupCards));
            cardsOnTheTable.put(keyForFirstGroup + 4, cardTaker.takeCardFromStack(secondGroupCards));
            cardsOnTheTable.put(keyForFirstGroup + 8, cardTaker.takeCardFromStack(thirdGroupCards));
        }
        return cardsOnTheTable;
    }
}
