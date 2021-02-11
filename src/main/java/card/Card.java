package card;

import coin.Color;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Card {
    private Color color;
    private int points;
    private int group;
    private Map<Color, Integer> cost = new HashMap<>();
    private String image;

    public Card(Color color, int group, int points, int whiteCoinsCost, int redCoinCost, int greenCoinCost, int blueCoinsCost, int blackCoinsCost, String image) {
        this.color = color;
        this.points = points;
        this.group = group;
        cost.put(Color.RED,redCoinCost);
        cost.put(Color.GREEN,greenCoinCost);
        cost.put(Color.BLACK, blackCoinsCost);
        cost.put(Color.BLUE, blueCoinsCost);
        cost.put(Color.WHITE, whiteCoinsCost);
        this.image = image;
    }
}
