package pl.vanta.red7.engine;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.vanta.red7.game.BaseTest;
import pl.vanta.red7.game.Card;
import pl.vanta.red7.game.Deck;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.of;

class PlayerTest extends BaseTest {

    static Stream<Arguments> handsOfWrongSize() {
        return Stream.of(0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 49)
                .map(size -> of(size + " cards", Deck.allCards().stream().limit(size).collect(toSet())));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("handsOfWrongSize")
    void shouldNotAllowToInitPlayerWithNot7Cards(String description, Set<Card> hand) {
        //given
        var player = new Player("Alice");

        //when
        var exception = assertThrows(AssertionError.class, () -> player.init(hand, R7));

        //then
        assertEquals("Player Alice should be dealt 7 cards, but got " + hand.size(), exception.getMessage());
    }

}
