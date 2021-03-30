package coin;

import java.util.*;

import static java.util.stream.Collectors.*;

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
        return Arrays.stream(values())
                .filter(color -> color != GOLD)
                .collect(toList());
    }

    public static Map<Integer, Color> createMapIdOfColors() {
        Map<Integer, Color> map = new HashMap<>();

        int idNumber = 1;
        for (Color color : values()) {
            map.put(idNumber++, color);
        }
        return map;
    }
}
