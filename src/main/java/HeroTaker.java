import java.util.List;

public class HeroTaker {
    private Player currentPlayer;
    private Table table;
    private Hero selectedHero;
    private int numberOfHero;

    public HeroTaker (Player currentPlayer, Table table, Hero selectedHero, int numberOfHero){
        this.currentPlayer = currentPlayer;
        this.table = table;
        this.selectedHero = selectedHero;
        this.numberOfHero = numberOfHero;
    }

    public void takeHero() {
        List<Color> colorsListWithoutGold = ColorsWithoutGold.listOfColorsWithoutGold();
        for (Color color : colorsListWithoutGold) {
            if (currentPlayer.getCardsUser().get(color) < selectedHero.getCostHero().get(color)){
                throw new IllegalArgumentException("You have not enough card ");
            }
        }
        currentPlayer.getHeroList().add(selectedHero);
        currentPlayer.setPoints(currentPlayer.getPoints()+selectedHero.getPoints());
        table.getHeroesOnTableMap().remove(numberOfHero);
    }
}
