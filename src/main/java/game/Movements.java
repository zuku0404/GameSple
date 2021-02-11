package game;

import java.util.Optional;

public enum Movements {
    BUY_CARD(1),
    TAKE_TREE_DIFFERENT_COINS(2),
    TAKE_TWO_SAME_COINS(3),
    BUY_HERO(4),
    RESERVE_CARD(5),
    BUY_RESERVED_CARD(6);

    private int number;

    Movements(int number) {
        this.number = number;
    }

    public static Optional<Movements> choseMove (int decisionNumber) {
        for (Movements value : values()) {
            if(value.number == decisionNumber){
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
