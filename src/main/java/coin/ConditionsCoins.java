package coin;

import game.Movements;
import lombok.Getter;
import message.Message;
import message.Messenger;


@Getter
public class ConditionsCoins {
    private int minimalNumberCoinsOnStackOnTable;
    private int amountCoinsToChangeFromSingleStack;
    private int maxAmountCoinsToTakeInSingleMove;
    private int maxNumberCoinsPosesByPlayer = 10;


    public ConditionsCoins(Movements move) {
        if (move.equals(Movements.TAKE_TREE_DIFFERENT_COINS)) {
            this.minimalNumberCoinsOnStackOnTable = 1;
            this.amountCoinsToChangeFromSingleStack = 1;
            this.maxAmountCoinsToTakeInSingleMove = 3;
            Messenger.display(Message.TAKE_THREE_COIN_QUESTION);
        } else if (move.equals(Movements.TAKE_TWO_SAME_COINS)) {
            this.minimalNumberCoinsOnStackOnTable = 4;
            this.amountCoinsToChangeFromSingleStack = 2;
            this.maxAmountCoinsToTakeInSingleMove = 2;
            Messenger.display(Message.TAKE_TWO_COIN_QUESTION);
        }
        else {
            this.minimalNumberCoinsOnStackOnTable = 0;
            this.amountCoinsToChangeFromSingleStack = 0;
            this.maxAmountCoinsToTakeInSingleMove = 0;
        }
    }
}