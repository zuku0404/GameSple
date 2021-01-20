import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Card {
    private Color cardColor;
    private int points;
    private int group;
    private Map<Color, Integer> cardCost = new HashMap<>();
    private String imageCard;

    public Card(Color cardColor, int group,int points, int whiteCoinsCost, int redCoinCost, int greenCoinCost, int blueCoinsCost, int blackCoinsCost,String imageCard) {
        this.cardColor = cardColor;
        this.points = points;
        this.group = group;
        cardCost.put(Color.RED,redCoinCost);
        cardCost.put(Color.GREEN,greenCoinCost);
        cardCost.put(Color.BLACK, blackCoinsCost);
        cardCost.put(Color.BLUE, blueCoinsCost);
        cardCost.put(Color.WHITE, whiteCoinsCost);
        this.imageCard = imageCard;
    }
}
