import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Hero {
    private Map<Color, Integer> costHero = new HashMap<>();
    private int points;
    private String imageCard;

    public Hero(int points, int whiteCardsCost, int redCoinCost, int greenCoinCost, int blueCardsCost, int blackCardsCost, String imageCard) {
        this.points = points;
        this.imageCard = imageCard;
        costHero.put(Color.RED,redCoinCost);
        costHero.put(Color.GREEN,greenCoinCost);
        costHero.put(Color.BLACK, blackCardsCost);
        costHero.put(Color.BLUE, blueCardsCost);
        costHero.put(Color.WHITE, whiteCardsCost);
    }
}
