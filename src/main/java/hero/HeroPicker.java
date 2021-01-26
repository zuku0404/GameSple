package hero;

import coin.Color;
import game.ColorsWithoutGold;
import game.Table;
import player.Player;

import java.util.List;

public class HeroPicker {
    private Player currentPlayer;
    private Table table;
    private Hero selectedHero;
    private int numberOfHero;

    public HeroPicker(Player currentPlayer, Table table, Hero selectedHero, int numberOfHero) {
        this.currentPlayer = currentPlayer;
        this.table = table;
        this.selectedHero = selectedHero;
        this.numberOfHero = numberOfHero;
    }

    public void takeHeroByPlayer() {
        if(checkIfPlayerCanTakeHero()) {
            currentPlayer.takeHero(selectedHero);
            table.removeHeroFromTable(numberOfHero);
//            currentPlayer.getHeroList().add(selectedHero);
//            currentPlayer.setPoints(currentPlayer.getPoints() + selectedHero.getPoints());
//            table.getHeroesOnTableMap().remove(numberOfHero);
        }
        else {
            throw new IllegalArgumentException("You have not enough card ");
        }
    }
    private boolean checkIfPlayerCanTakeHero() {
        List<Color> colorsListWithoutGold = ColorsWithoutGold.listOfColorsWithoutGold();
        for (Color color : colorsListWithoutGold) {
            if (currentPlayer.getCardsUser().get(color) < selectedHero.getCostHero().get(color)) {
                return false;
            }
        }
        return true;
    }
}