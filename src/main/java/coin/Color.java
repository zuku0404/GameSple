package coin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Color {
    WHITE("white"),
    BLUE("blue"),
    BLACK("black"),
    RED("red"),
    GREEN("green"),
    GOLD("gold");

    private String colorName;


    Color(String colorName) {
        this.colorName = colorName;
    }
    public static List<Color> getColorsWithoutGold() {
        List<Color> colorsWithoutGold = new ArrayList<>();
        for (Color color : Color.values()) {
            if (color != Color.GOLD) {
                colorsWithoutGold.add(color);
            }
        }
        return colorsWithoutGold;
    }
    public static Map<Integer, Color> createMapIdOfColors() {
        Map<Integer, Color> map = new HashMap<>();
        int idNumber = 1;
        for (Color color : Color.values()) {
            map.put(idNumber, color);
            idNumber++;
        }
        return map;
    }
}
