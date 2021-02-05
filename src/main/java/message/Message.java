package message;

public enum Message {
    TAKE_TWO_COIN_QUESTION("Which two coins would you like to take"),
    TAKE_THREE_COIN_QUESTION("Which three different coins would you like to take"),
    TAKE_HERO_QUESTION("Which hero would you like to buy: "),
    BUY_CARD_QUESTION("Which card would you like to buy (Enter number): "),
    RESERVE_CARD_QUESTION("Which card do you want reserve: "),
    BUY_RESERVED_CARD_QUESTION("Which card would you like to buy (Enter number): "),
    DECISION_WINDOW ("\nWhat would you like to do (write number): " +
            "\n1:buy card, 2:take three coins different color, 3:take two coins in the same color, " +
            "4:buy a hero, 5:reserve card, 6 buy reserved card " +
            "\nEnter number: "),
    FINAL_SCORE_TAG("Final Score"),
    FINAL_SCORE_PLAYER ("Player number: %d scored: %d"),
    FINAL_ROUND_TAG ("IT IS FINAL ROUND !!"),
    ENTER_TAG ("Enter number of coin: "),
    NUMBER_OF_COIN_TO_TAKE("In this move you can take only %s coins "),
    PLAYER_STATUS("Player number: %s " +
            "\nActual status of player: " +
            "\nyou have %s points " +
            "\nyour cards: %s " +
            "\nyour coins: %s" +
            "\nnumber of hero %s :" +
            "\nyour reserved cards: "),
    DASHED_LINE ("-------------------------------------------------------------------------------------------------"),
    CARD_HEADER_TABLE( " %10s %10s %10s %10s %30s"),
    CARD_STATISTIC("%7s %13s %7s %11s %50s "),
    HERO_HEADER_TABLE( " %10s %13s %30s"),
    HERO_STATISTIC("%7s %13s %50s "),
    TABLE_STATISTIC_FIRST_PART("\nTABLE: " +
            "\nCoins on the table: %s " +
            "\nCards on the table:\n "),
    TABLE_STATISTIC_SECOND_PART("Heroes on the table");


    public String getText() {
        return text;
    }

    private String text;

     Message(String text){
        this.text = text;
    }
}
