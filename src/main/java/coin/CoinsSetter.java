package coin;

import java.util.*;

public class CoinsSetter {
    private Map<Color, Integer> coinsOnTable = new HashMap<>();

    public Map<Color, Integer> setCoinsOnStart(int numberOfPlayer) {
        int numberOfCoins;
        if (numberOfPlayer == 2 || numberOfPlayer == 3)
            numberOfCoins = numberOfPlayer + 2;
        else
            numberOfCoins = numberOfPlayer + 3;
        for (Color color : Color.values())
            if (color != Color.GOLD) {
                coinsOnTable.put(color, numberOfCoins);
            } else {
                coinsOnTable.put(color, 5);
            }
        return coinsOnTable;
    }
}
