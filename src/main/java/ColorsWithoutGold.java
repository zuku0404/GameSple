import java.util.ArrayList;
import java.util.List;

public class ColorsWithoutGold {
    public static List<Color> listOfColorsWithoutGold() {
        List<Color> colorsWithoutGold = new ArrayList<>();
        for (Color color : Color.values()) {
            if (color != Color.GOLD) {
                colorsWithoutGold.add(color);
            }
        }
        return colorsWithoutGold;
    }
}
