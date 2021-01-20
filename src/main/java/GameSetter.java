import java.util.*;

public class GameSetter {

    public Map<Color, Integer> setCoinsOnStart(int numberOfPlayer) {
        Map<Color, Integer> coinsOnTable = new HashMap<>();
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
    public Map<Integer, Color> setCoinsWithIdMap() {
        Map<Integer, Color> coinsIdMap = new HashMap<>();
        int idNumber = 1;
        for (Color color : Color.values()) {
            coinsIdMap.put(idNumber, color);
            idNumber++;
        }
        return coinsIdMap;
    }

}
