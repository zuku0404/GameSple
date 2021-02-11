package coin;

import exceptions.InvalidActionException;
import exceptions.InvalidValueException;
import game.Movements;
import game.Table;
import lombok.extern.slf4j.Slf4j;
import message.Message;
import message.Messenger;
import player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class CoinsTaker {
    private Player currentPlayer;
    private Table table;
    private Scanner scanner = new Scanner(System.in);
    private ConditionsCoins conditionsCoins;
    private int maxNumberCoinsPosesByPlayer;

    public CoinsTaker(Table table, Player currentPlayer, Movements move) {
        this.table = table;
        this.currentPlayer = currentPlayer;
        conditionsCoins = new ConditionsCoins(move);
        this.maxNumberCoinsPosesByPlayer = conditionsCoins.getMaxNumberCoinsPosesByPlayer();
    }

    public void takeGoldCoin() {
        int actualNumberOfPlayerCoins = currentPlayer.calculateActualNumberOfPlayerCoins();
        int numberOfPlayerGoldCoins = currentPlayer.getNumberOfSelectedColorCoins(Color.GOLD);
        int numberOfGoldCoinsOnTable = table.getNumberOfSelectedColorCoins(Color.GOLD);

        if (actualNumberOfPlayerCoins < maxNumberCoinsPosesByPlayer && numberOfGoldCoinsOnTable != 0) {
            currentPlayer.setNumberOfSelectedColorCoins(Color.GOLD, numberOfPlayerGoldCoins + 1);
            table.setNumberOfSelectedColorCoins(Color.GOLD, numberOfGoldCoinsOnTable - 1);
        }
    }

    public void selectAndTakeCoins() throws InvalidValueException {
        List<Color> possibleCoinsToTake = createListCoinsToTake(conditionsCoins.getMinimalNumberCoinsOnStackOnTable());
        int numberOfStackChanged = checkNumberOfStackChanged(possibleCoinsToTake, conditionsCoins.getMaxAmountCoinsToTakeInSingleMove(),
                conditionsCoins.getAmountCoinsToChangeFromSingleStack());
        List<Color> selectedCoinsList = takeCoinsFromStack(numberOfStackChanged, possibleCoinsToTake);
        relocateCoinsBetweenPlayerAndTable(selectedCoinsList, conditionsCoins.getAmountCoinsToChangeFromSingleStack());
    }

    private List<Color> createListCoinsToTake(int minimalAmountCoinsOnStackOnTable) {
        List<Color> colorsWithoutGold = Color.getColorsWithoutGold();
        List<Color> possibleCoinsToTake = new ArrayList<>();
        for (Color color : colorsWithoutGold) {
            if (table.getNumberOfSelectedColorCoins(color) >= minimalAmountCoinsOnStackOnTable) {
                possibleCoinsToTake.add(color);
            }
        }
        return possibleCoinsToTake;
    }

    private int checkNumberOfStackChanged(List<Color> possibleCoinsToTakeList, int maxAmountCoinsToTakeInSingleMove,
                                          int amountCoinsToChangeFromSingleStack) throws InvalidValueException {
        int differentBetweenMaxAndActualNumberOfCoin = maxNumberCoinsPosesByPlayer - currentPlayer.calculateActualNumberOfPlayerCoins();

        if (differentBetweenMaxAndActualNumberOfCoin < amountCoinsToChangeFromSingleStack || possibleCoinsToTakeList.isEmpty()) {
            throw new InvalidValueException("You can not select this option you have too much coins or on the table " +
                    "is not enough coins");
        } else if (amountCoinsToChangeFromSingleStack == 2) {
            return 1;
        } else if (possibleCoinsToTakeList.size() < maxAmountCoinsToTakeInSingleMove ||
                differentBetweenMaxAndActualNumberOfCoin < maxAmountCoinsToTakeInSingleMove) {
            int maxNumberOfStackChanged = Math.min(possibleCoinsToTakeList.size(), differentBetweenMaxAndActualNumberOfCoin);
            Messenger.display(Message.NUMBER_OF_COIN_TO_TAKE, maxNumberOfStackChanged);
            return maxNumberOfStackChanged;
        } else
            return 3;
    }

    private List<Color> takeCoinsFromStack(int numberOfStackChanged, List<Color> possibleCoinsToTakeList) throws InvalidValueException {
        List<Color> selectedCoinsList = new ArrayList<>();
        while (selectedCoinsList.size() < numberOfStackChanged) {
            Messenger.display(Message.ENTER_TAG);
            try {
                int numberOfCoin = scanner.nextInt();
                Color colorSelectedCoin;
                if ((colorSelectedCoin = Color.createMapIdOfColors().get(numberOfCoin)) != null) {
                    selectedCoinsList.add(takeSelectCoin(possibleCoinsToTakeList, colorSelectedCoin));
                } else throw new InvalidValueException("That coin not exist");
            } catch (InvalidActionException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return selectedCoinsList;
    }

    private Color takeSelectCoin(List<Color> possibleCoinsToTakeList, Color colorSelectedCoin) throws InvalidActionException {
        if (possibleCoinsToTakeList.contains(colorSelectedCoin)) {
            possibleCoinsToTakeList.remove(colorSelectedCoin);
            return colorSelectedCoin;
        } else {
            throw new InvalidActionException("You can not take this coin");
        }
    }

    private void relocateCoinsBetweenPlayerAndTable(List<Color> selectedCoinsList, int amountCoinsToChangeFromSingleStack) {
        for (Color color : selectedCoinsList) {
            int actualNumberCoinsInSelectedColorOnTable = table.getNumberOfSelectedColorCoins(color);
            int actualNumberCoinsInSelectedColorPlayer = currentPlayer.getNumberOfSelectedColorCoins(color);
            table.setNumberOfSelectedColorCoins(color, actualNumberCoinsInSelectedColorOnTable - amountCoinsToChangeFromSingleStack);
            currentPlayer.setNumberOfSelectedColorCoins(color, actualNumberCoinsInSelectedColorPlayer + amountCoinsToChangeFromSingleStack);
        }
    }
}
