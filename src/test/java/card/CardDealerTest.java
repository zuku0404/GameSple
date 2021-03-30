package card;

import coin.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import player.Player;

import java.util.Map;
import java.util.stream.Stream;

import static coin.Color.*;
import static org.mockito.Mockito.*;


class CardDealerTest {

    @ParameterizedTest
    @MethodSource("provideCardCostExamples")
    void hasPlayerEnoughColorPointsToBuyCard_dataFromParametrizedTest_resultFromData(Map<Color, Integer> cardCost, boolean expected) {
        //given
        Player player = mock(Player.class);
        when(player.getTotalNumberOfEachColors()).thenReturn(Map.of(RED, 2, GREEN, 2, BLACK, 3, BLUE, 4, WHITE, 3, GOLD, 3));

        Card card = mock(Card.class);
        when(card.getCost()).thenReturn(cardCost);

        CardDealer cardDealer = new CardDealer(null, player);
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
}