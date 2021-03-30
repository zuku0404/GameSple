package player;

import exceptions.InvalidValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


class PlayersCreatorTest {

    @Test
    void createPlayers_tooHighNumberOfPlayer_throwInvalidValueException() {
        //given
        PlayersCreator playersCreator = new PlayersCreator();
        //then
        Assertions.assertThrows(InvalidValueException.class, () -> playersCreator.createPlayers(5));
    }

    @Test
    void createPlayers_tooLowNumberOfPlayer_throwInvalidValueException() {
        //given
        PlayersCreator playersCreator = new PlayersCreator();
        //then
        Assertions.assertThrows(InvalidValueException.class, () -> playersCreator.createPlayers(1));
    }

    @ParameterizedTest
    @MethodSource("data")
     void createPlayers_correctNumberOfPlayer_returnArrayOfPlayer(int numberOfPlayer, int requireResult) throws InvalidValueException {
        //given
        PlayersCreator playersCreator = new PlayersCreator();
        //when
        int length = playersCreator.createPlayers(numberOfPlayer).length;
        // then
        Assertions.assertEquals(requireResult,length);
    }


    private static Stream<Arguments> data() {
        return Stream.of(Arguments.of(2, 2),
                Arguments.of(3, 3),
                Arguments.of(4, 4));
    }
}