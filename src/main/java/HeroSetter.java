import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HeroSetter {
    private Random random = new Random();
    private List<Hero> fourthGroupHero;
    private int playersNumber;

    public HeroSetter (int playersNumber, List<Hero> fourthGroupHero){
        this.fourthGroupHero = fourthGroupHero;
        this.playersNumber = playersNumber;
    }
    public Map<Integer, Hero> setHeroesOnStart() {
        Map<Integer, Hero> heroesOnTable = new HashMap<>();
        for (int i = 0; i < playersNumber + 1; i++) {
            int keyForFirstGroup = i + 1;
            heroesOnTable.put(keyForFirstGroup, takeHeroesFromStack(fourthGroupHero));
        }
        return heroesOnTable;
    }
    public Hero takeHeroesFromStack(List<Hero> heroesOnStacks) {
        int indexOfHero = random.nextInt(heroesOnStacks.size());
        Hero returnedHero = heroesOnStacks.get(indexOfHero);
        heroesOnStacks.remove(returnedHero);
        return returnedHero;
    }
}
