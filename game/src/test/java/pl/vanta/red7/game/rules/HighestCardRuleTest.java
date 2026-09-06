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

class HighestCardRuleTest extends BaseTest {

    private final Rule underTest = new HighestCardRule();

    static Stream<Arguments> testCases() {
        return Stream.of(
                of("all red cards", Set.of(R7, R6, R5, R4, R3, R2, R1), R7),
                of("all 7 cards", Set.of(R7, O7, Y7, G7, B7, I7, V7), R7),
                of("different colors and numbers", Set.of(V7, O7, R5, R6), O7),
                of("single card", Set.of(R4), R4),
                of("low cards only", Set.of(R1, R2, R3), R3)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("testCases")
    void shouldReturnHighestCard(String description, Set<Card> input, Card expected) {
        assertEquals(Set.of(expected), underTest.getCardsForRule(input));
    }
}
