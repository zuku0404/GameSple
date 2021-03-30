package coin;

import game.Movement;
import lombok.Getter;

@Getter
public class ConditionsCoins {
    private int minimalNumberCoinsOnStackOnTable;
    private int amountCoinsToChangeFromSingleStack;
    private int maxAmountCoinsToTakeInSingleMove;
    private int maxNumberCoinsPosesByPlayer = 10;

    private ConditionsCoins(int minimalNumberCoinsOnStackOnTable, int amountCoinsToChangeFromSingleStack, int maxAmountCoinsToTakeInSingleMove) {
        this.minimalNumberCoinsOnStackOnTable = minimalNumberCoinsOnStackOnTable;
        this.amountCoinsToChangeFromSingleStack = amountCoinsToChangeFromSingleStack;
        this.maxAmountCoinsToTakeInSingleMove = maxAmountCoinsToTakeInSingleMove;
    }

    public static ConditionsCoins getConditionCoins(Movement move) {
        switch (move) {
            case TAKE_TREE_DIFFERENT_COINS:
                return new ConditionsCoins(1, 1, 3);
            case TAKE_TWO_SAME_COINS:
                return new ConditionsCoins(4, 2, 2);
            case RESERVE_CARD:
                return null;
            default:
                throw new IllegalArgumentException();
        }
    }
}