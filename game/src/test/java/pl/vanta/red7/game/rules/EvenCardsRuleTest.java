package pl.vanta.red7.game.rules;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.vanta.red7.game.BaseTest;
import pl.vanta.red7.game.Card;
import pl.vanta.red7.game.Rule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

class EvenCardsRuleTest extends BaseTest {

    private final Rule underTest = new EvenCardsRule();

    static Stream<Arguments> testCases() {
        return Stream.of(
                of("all red cards", Set.of(R7, R6, R5, R4, R3, R2, R1), Set.of(R6, R4, R2)),
                of("all 7 cards", Set.of(R7, O7, Y7, G7, B7, I7, V7), Set.of()),
                of("different colors and numbers", Set.of(V7, O7, R7, R6), Set.of(R6)),
                of("single card", Set.of(R4), Set.of(R4)),
                of("low cards only", Set.of(R1, R2, R3), Set.of(R2))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("testCases")
    void shouldReturnEvenCards(String description, Set<Card> input, Set<Card> expected) {
        assertEquals(expected, underTest.getCardsForRule(input));
    }

}