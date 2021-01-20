import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                cardsUser.put(color, 4);
                coinsUser.put(color, 0);
                totalNumberOfEachColors.put(color, cardsUser.get(color) + coinsUser.get(color));
            }

        }
    }
    public void setPoints(int points) {
        this.points = points;
    }
}
