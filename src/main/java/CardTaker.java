
import java.util.List;
import java.util.Random;

public class CardTaker {
    Random random = new Random();
    private List<Card>firstGroupCards;
    private List<Card>secondGroupCards;
    private List<Card>thirdGroupCards;

    public CardTaker(List<Card> firstGroupCards, List<Card>secondGroupCards, List<Card>thirdGroupCards){
        this.firstGroupCards = firstGroupCards;
        this.secondGroupCards = secondGroupCards;
        this.thirdGroupCards = thirdGroupCards;
    }
    public Card takeCard (Card selectedCard) {
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
    public  Card takeCardFromStack(List<Card> cardsOnStacks) {

        if (cardsOnStacks.isEmpty()) {
            return null;
        }
        int indexOfCard = random.nextInt(cardsOnStacks.size());
        Card returnedCard = cardsOnStacks.get(indexOfCard);
        cardsOnStacks.remove(returnedCard);
        return returnedCard;
    }
}
