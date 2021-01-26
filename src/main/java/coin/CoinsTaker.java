package coin;

import game.ColorsWithoutGold;
import game.GameSetter;
import game.Table;
import lombok.extern.slf4j.Slf4j;
import player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class CoinsTaker {
    private int minimalAmountCoinsOnStackOnTable;
    private int amountCoinsToChangeFromSingleStack;
    private int maxAmountCoinsToTakeInSingleMove;
    private Player currentPlayer;
    private Table table;
    private Scanner scanner = new Scanner(System.in);

    public CoinsTaker(int minimalAmountCoinsOnStackOnTable, int amountCoinsToChangeFromSingleStack,
                      int maxAmountCoinsToTakeInSingleMove, Table table, Player currentPlayer) {

        this.minimalAmountCoinsOnStackOnTable = minimalAmountCoinsOnStackOnTable;
        this.amountCoinsToChangeFromSingleStack = amountCoinsToChangeFromSingleStack;
        this.maxAmountCoinsToTakeInSingleMove = maxAmountCoinsToTakeInSingleMove;
        this.table = table;
        this.currentPlayer = currentPlayer;
    }

    public void chooseAndTakeCoins() {
        List<Color> possibleCoinsToTakeList = checkPossibleCoinsToTake(minimalAmountCoinsOnStackOnTable);
        int numberOfStackChanged = checkNumberOfStackChanged(possibleCoinsToTakeList, maxAmountCoinsToTakeInSingleMove, amountCoinsToChangeFromSingleStack);
        List<Color> selectedCoinsList = takeCoin(numberOfStackChanged, possibleCoinsToTakeList);
        confirmTakeCoins(selectedCoinsList, amountCoinsToChangeFromSingleStack);
    }

    private List<Color> checkPossibleCoinsToTake(int minimalAmountCoinsOnStackOnTable) {
        List<Color> colorsListWithoutGold = ColorsWithoutGold.listOfColorsWithoutGold();
        List<Color> possibleCoinsToTakeList = new ArrayList<>();
        for (Color color : colorsListWithoutGold) {
            if (table.getCoinsOnTableMap().get(color) >= minimalAmountCoinsOnStackOnTable) {
                possibleCoinsToTakeList.add(color);
            }
        }
        return possibleCoinsToTakeList;
    }

    private int checkNumberOfStackChanged(List<Color> possibleCoinsToTakeList, int maxAmountCoinsToTakeInSingleMove, int amountCoinsToChangeFromSingleStack) {
        CoinsCounter coinsCounter = new CoinsCounter();
        int maxNumberCoinsPosesByPlayerInGame = 10;
        int differentBetweenMaxAndActualNumberOfCoin = maxNumberCoinsPosesByPlayerInGame - coinsCounter.checkActualTotalNumberOfCoinsPosesByPlayer(currentPlayer);

        if (differentBetweenMaxAndActualNumberOfCoin < amountCoinsToChangeFromSingleStack || possibleCoinsToTakeList.isEmpty()) {
            throw new IllegalArgumentException("You can not select this option you have too much coins or on the table " +
                    "is not enough coins");
        } else if (amountCoinsToChangeFromSingleStack == 2) {
            return 1;
        } else if (possibleCoinsToTakeList.size() < maxAmountCoinsToTakeInSingleMove || differentBetweenMaxAndActualNumberOfCoin < maxAmountCoinsToTakeInSingleMove) {
            int maxNumberOfStackChanged = Math.min(possibleCoinsToTakeList.size(), differentBetweenMaxAndActualNumberOfCoin);
            System.out.println(String.format("In this move you can take only %d coins ", maxNumberOfStackChanged));
            return maxNumberOfStackChanged;
        } else
            return 3;
    }

    private List<Color> takeCoin(int numberOfStackChanged, List<Color> possibleCoinsToTakeList) {
        List<Color> selectedCoinsList = new ArrayList<>();
        while (selectedCoinsList.size() < numberOfStackChanged) {
            try {
                System.out.println("Enter number of coin: ");
                int numberOfCoin = scanner.nextInt();
                Color colorSelectedCoin;
                if ((colorSelectedCoin = GameSetter.setCoinsWithIdMap().get(numberOfCoin)) != null) {
                    selectedCoinsList.add(selectCoin(possibleCoinsToTakeList, colorSelectedCoin));
                } else throw new NullPointerException("That coin not exist");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return selectedCoinsList;
    }

    private Color selectCoin(List<Color> possibleCoinsToTakeList, Color colorSelectedCoin) {
        if (possibleCoinsToTakeList.contains(colorSelectedCoin)) {
            possibleCoinsToTakeList.remove(colorSelectedCoin);
            return colorSelectedCoin;
        } else {
            throw new IllegalArgumentException("You can not take this coin");
        }
    }

    private void confirmTakeCoins(List<Color> selectedCoinsList, int amountCoinsToChangeFromSingleStack) {
        for (Color color : selectedCoinsList) {
            table.getCoinsOnTableMap().replace(color, table.getCoinsOnTableMap().get(color) - amountCoinsToChangeFromSingleStack);
            currentPlayer.getCoinsUser().replace(color, currentPlayer.getCoinsUser().get(color) + amountCoinsToChangeFromSingleStack);
        }
    }


}
