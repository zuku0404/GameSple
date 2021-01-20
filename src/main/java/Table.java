import lombok.Getter;

import java.util.Map;

@Getter
public class Table {
    private Map<Color, Integer> coinsOnTableMap;
    private Map<Integer, Card> cardsOnTableMap;
    private Map<Integer, Hero> heroesOnTableMap;

    public Table(Map<Color, Integer> coinsOnTableMap, Map<Integer, Card> cardsOnTableMap, Map<Integer, Hero> heroesOnTableMap) {
            this.coinsOnTableMap = coinsOnTableMap;
            this.cardsOnTableMap = cardsOnTableMap;
            this.heroesOnTableMap = heroesOnTableMap;
    }
}

