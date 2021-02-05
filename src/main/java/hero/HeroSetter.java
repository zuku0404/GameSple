package hero;

import converter.JsonReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HeroSetter {
    private Random random = new Random();
    private static List<Hero> fourthGroupHero = JsonReader.createListFourthGroupHeroes();
    private int numberOfPlayers;

    public HeroSetter (int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
    }
    public Map<Integer, Hero> setHeroesOnStart() {
        Map<Integer, Hero> heroesOnTable = new HashMap<>();
        for (int numberOfHero = 1; numberOfHero <= numberOfPlayers +1; numberOfHero++) {
            heroesOnTable.put(numberOfHero, takeHeroesFromStack(fourthGroupHero));
        }
        return heroesOnTable;
    }
    private Hero takeHeroesFromStack(List<Hero> heroesOnStacks) {
        int indexOfRandomHeroFromStack = random.nextInt(heroesOnStacks.size());
        Hero returnedHero = heroesOnStacks.get(indexOfRandomHeroFromStack);
        heroesOnStacks.remove(returnedHero);
        return returnedHero;
    }
}
