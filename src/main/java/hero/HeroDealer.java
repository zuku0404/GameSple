package hero;

import coin.Color;
import exceptions.InvalidValueException;
import game.Table;
import player.Player;

import java.util.List;

public class HeroDealer {
    private Player currentPlayer;
    private Table table;
    private Hero selectedHero;
    private int numberOfHero;
    private List<Color> colorsListWithoutGold = Color.getColorsWithoutGold();

    public HeroDealer (Player currentPlayer, Table table, Hero selectedHero, int numberOfHero) {
        this.currentPlayer = currentPlayer;
        this.table = table;
        this.selectedHero = selectedHero;
        this.numberOfHero = numberOfHero;
    }

    public void takeHeroByPlayer() throws InvalidValueException {
        if(isPossibleTakeHero()) {
            currentPlayer.takeHero(selectedHero);
            table.removeHero(numberOfHero);
        }
        else {
            throw new InvalidValueException("You have not enough card ");
        }
    }
    private boolean isPossibleTakeHero() {
        for (Color color : colorsListWithoutGold) {
            if (currentPlayer.getCardsUser().get(color) < selectedHero.getNumberOfColorCostHero(color)) {
                return false;
            }
        }
        return true;
    }
}