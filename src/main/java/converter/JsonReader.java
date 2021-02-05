package converter;

import card.Card;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hero.Hero;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    private JsonReader(){}
    private static Gson gson = new Gson();

    public static List<Card> createListFirstGroupCards(){
        List<Card>firstGroupCard = new ArrayList<>();
        try(Reader reader = Files.newBufferedReader(Paths.get("jsonData/firstGroupCard.json"))) {
            firstGroupCard = gson.fromJson(reader, new TypeToken<List<Card>>() {
            }.getType());
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return firstGroupCard;
    }
    public static List<Card> createListSecondGroupCards(){
        List<Card> secondGroupCard = new ArrayList<>();
        try(Reader reader = Files.newBufferedReader(Paths.get("jsonData/secondGroupCard.json"))) {
            secondGroupCard = gson.fromJson(reader, new TypeToken<List<Card>>() {
            }.getType());
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return secondGroupCard;
    }
    public static List<Card> createListThirdGroupCards(){
        List<Card> thirdGroupCard = new ArrayList<>();
        try(Reader reader = Files.newBufferedReader(Paths.get("jsonData/thirdGroupCard.json"))) {
            thirdGroupCard = gson.fromJson(reader, new TypeToken<List<Card>>() {
            }.getType());
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return thirdGroupCard;
    }

    public static List<Hero> createListFourthGroupHeroes(){
        List<Hero> fourthGroupHero = new ArrayList<>();
        try(Reader reader = Files.newBufferedReader(Paths.get("jsonData/fourthGroupHero.json"))) {
            fourthGroupHero = gson.fromJson(reader, new TypeToken<List<Hero>>() {
            }.getType());
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return fourthGroupHero;
    }
}
