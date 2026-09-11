package pl.vanta.red7.engine;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
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

    private static final Player PLAYER_ALICE = new Player("Alice");

    static Stream<Arguments> handsOfWrongSize() {
        return Stream.of(0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 49)
                .map(size -> of(size + " cards", Deck.allCards().stream().limit(size).collect(toSet())));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("handsOfWrongSize")
    void shouldNotAllowToInitPlayerWithNot7Cards(String description, Set<Card> hand) {
        //given

        //when
        var exception = assertThrows(IllegalArgumentException.class, () -> PLAYER_ALICE.init(hand, R7));

        //then
        assertEquals("Player Alice should be dealt 7 cards, but got " + hand.size(), exception.getMessage());
    }

    @Test
    void shouldNotAllowToInitPlayerWithTableTheSameAsInHand() {
        //given
        var hand = Set.of(R7, R6, R5, R4, R3, R2, R1);
        var tableCard = R7;

        //when
        var exception = assertThrows(IllegalArgumentException.class, () -> PLAYER_ALICE.init(hand, tableCard));

        //then
        assertEquals("Player Alice should not have initial card " + tableCard + " in hand", exception.getMessage());
    }

    static Stream<Arguments> invalidNames() {
        return Stream.of(
                of("null", null, NullPointerException.class),
                of("empty", "", IllegalArgumentException.class),
                of("blank", "   ", IllegalArgumentException.class)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidNames")
    void shouldCannotCreatePlayerWithInvalidName(String description, String name, Class<? extends RuntimeException> expectedException) {
        var exception = assertThrows(expectedException, () -> new Player(name));

        assertEquals("name cannot be empty", exception.getMessage());
    }

}
