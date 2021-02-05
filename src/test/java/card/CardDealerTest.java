package card;

import coin.Color;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;
import org.testng.annotations.Test;
import player.Player;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CardDealerTest {

    @Test
    public void hasPlayerEnoughColorPointsToBuyCard_dataFromMock_returnTrue() {
        //given
        Player currentPlayer = createPlayerMock();
        CardDealer cardDealer = new CardDealer(null, currentPlayer);
        //when
        Map<Color, Integer> cardCost = createCardMock().getCardCost();
        Map<Color, Integer> cardCost2 = createCardMock().getCardCost();
        boolean result = cardDealer.hasPlayerEnoughColorPointsToBuyCard(cardCost);

        boolean result2 = cardDealer.hasPlayerEnoughColorPointsToBuyCard(cardCost2);
        //then
        Assert.assertTrue(result);
        Assert.assertTrue(result2);
    }
    Card mock = Mockito.mock(Card.class);
    private Player createPlayerMock() {
        Player mock = Mockito.mock(Player.class);
        Mockito.when(mock.getTotalNumberOfEachColors()).thenReturn(Map.of(Color.RED, 2, Color.GREEN, 2, Color.BLACK, 3, Color.BLUE, 4,
                Color.WHITE, 3, Color.GOLD, 3));
        return mock;
    }

    private Card createCardMock() {
        List<Map<Color, Integer>> cardCostList = createListCardCostExample();
        Mockito.when(mock.getCardCost()).thenReturn(cardCostList.get(1)).thenReturn(cardCostList.get(0));
        return mock;
    }

    private List<Map<Color, Integer>> createListCardCostExample() {
        List<Map<Color, Integer>> cardCostList = new ArrayList<>();
        cardCostList.add(Map.of(Color.RED, 2, Color.GREEN, 2, Color.BLACK, 2,
                Color.BLUE, 2, Color.WHITE, 2));
        cardCostList.add(Map.of(Color.RED, 1, Color.GREEN, 4, Color.BLACK, 3,
                Color.BLUE, 4, Color.WHITE, 1));
        cardCostList.add(Map.of(Color.RED, 3, Color.GREEN, 4, Color.BLACK, 1,
                Color.BLUE, 1, Color.WHITE, 1));
        return cardCostList;
    }
}