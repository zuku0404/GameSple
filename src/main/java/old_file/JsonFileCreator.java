package old_file;

import card.Card;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hero.Hero;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonFileCreator {
    public static void main(String[] args) {
        JsonFileCreator jsonFileCreator = new JsonFileCreator();
        jsonFileCreator.convert();
    }
    private Gson gson;

    public void convert() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        cardSerialization();
        heroSerialization();
    }

    private void heroSerialization() {
        List<Hero> fourthGroupHero = DataFromExternalSource.createHeroList();
        String fourthGroupPath= "jsonData/fourthGroupHero.json";
        try (FileWriter writer = new FileWriter(fourthGroupPath)) {
            gson.toJson(fourthGroupHero, writer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void cardSerialization() {
        Map<String, List<Card>> mapOfCardListAndPath = new HashMap<>();
        createInputCardsToJson(mapOfCardListAndPath);
        for(Map.Entry<String, List<Card>> path : mapOfCardListAndPath.entrySet()){
            try (FileWriter writer = new FileWriter(path.getKey())) {
                gson.toJson(mapOfCardListAndPath.get(path.getKey()), writer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private Map<String, List<Card>> createInputCardsToJson(Map<String, List<Card>> pathAndList) {
        String firstGroupPath = "jsonData/firstGroupCard.json";
        String secondGroupPath = "jsonData/secondGroupCard.json";
        String thirdGroupPath = "jsonData/thirdGroupCard.json";

        List<Card> firstGroupCard = DataFromExternalSource.createCardListFirstGroup();
        List<Card> secondGroupCard = DataFromExternalSource.createSecondGroupCards();
        List<Card> thirdGroupCard = DataFromExternalSource.createThirdGroupCards();

        pathAndList.put(firstGroupPath, firstGroupCard);
        pathAndList.put(secondGroupPath, secondGroupCard);
        pathAndList.put(thirdGroupPath, thirdGroupCard);
        return pathAndList;
    }
}
