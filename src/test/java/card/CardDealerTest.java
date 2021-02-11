package card;

import coin.Color;
import game.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import player.Player;

import java.util.Map;


public class CardDealerTest {

    @Test
    public void hasPlayerEnoughColorPointsToBuyCard_dataFromMock_returnTrue() {
        //given
        Player currentPlayer = createPlayerMock();
        CardDealer cardDealer = new CardDealer(null, currentPlayer);
        //when

        boolean result = cardDealer.hasPlayerEnoughColorPointsToBuyCard(cardCost);

        //then
        Assertions.assertEquals(expected, result);

    }

    private static Stream<Arguments> provideCardCostExamples() {
        return Stream.of(
                Arguments.of(Map.of(RED, 2, GREEN, 2, BLACK, 2, BLUE, 2, WHITE, 2), true),
                Arguments.of(Map.of(RED, 5, GREEN, 2, BLACK, 3, BLUE, 4, WHITE, 3), true),
                Arguments.of(Map.of(RED, 0, GREEN, 0, BLACK, 0, BLUE, 0, WHITE, 6), true),
                Arguments.of(Map.of(RED, 1, GREEN, 4, BLACK, 3, BLUE, 4, WHITE, 1), true),
                Arguments.of(Map.of(RED, 3, GREEN, 4, BLACK, 1, BLUE, 1, WHITE, 4), false));
    }
@ParameterizedTest
@MethodSource("provideCarPlayerExamples")
    void checkPlayerNeedUseCoins(Map<Color,Integer> selectedCardCost){
        //when
        Player player = mock(Player.class);
        when(player.getCardsUser()).thenReturn(Map.of(RED, 2, GREEN, 2, BLACK, 3, BLUE, 4, WHITE, 3));
        CardDealer cardDealer = new CardDealer(null,player);
        // then
        cardDealer.checkPlayerNeedUseCoins(selectedCardCost);

    }


//    @ParameterizedTest
//    @MethodSource("provideCoinExamples")
//    void useGoldCoinsInsteadOfColor_dataFromParametrizedTest_resultFromData(int goldCoinsUser, int goldCoinsOnTable, int goldCoinUsed,Color color,  int colorCoinsOnTable,
//                                                                            int colorCoinsPosesByPlayer,Map<Color,Integer>CoinsOnTableMap,Map<Color,Integer>CoinsUser) {
//        //given
//        Player player = mock(Player.class);
//        when(player.getNumberOfSelectedColorCoins(GOLD)).thenReturn(goldCoinsUser);
//        when(player.getCoinsUser()).thenReturn(Map.of(color, colorCoinsPosesByPlayer, GOLD, goldCoinsUser));
//        Table table = mock(Table.class);
//        when(table.getNumberOfSelectedColorCoins(GOLD)).thenReturn(goldCoinsOnTable);
//        when(table.getCoinsOnTableMap()).thenReturn(Map.of(color, colorCoinsOnTable, GOLD, goldCoinsOnTable));
//        CardDealer cardDealer = new CardDealer(table, player);
//        //when
//        cardDealer.useColorAndGoldCoins(color, goldCoinUsed, colorCoinsOnTable, colorCoinsPosesByPlayer);
//        //then
//        Assertions.assertEquals(CoinsOnTableMap,table.getCoinsOnTableMap());
//        Assertions.assertEquals(CoinsUser,player.getCoinsUser());
//    }
//
//    private static Stream<Arguments> provideCoinExamples() {
//        return Stream.of(
//                Arguments.of(2,1,1,RED,4, 2, Map.of(RED, 6, GOLD, 2),Map.of(RED, 0,  GOLD, 1)));
//    }
}