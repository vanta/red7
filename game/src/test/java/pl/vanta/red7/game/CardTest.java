package pl.vanta.red7.game;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.vanta.red7.game.Card.*;

class CardTest {

    @Test
    void shouldCompareCardsByValue() {
        assertTrue(RED_7.isBetterThan(RED_6));
        assertTrue(RED_6.isBetterThan(RED_5));
        assertTrue(RED_5.isBetterThan(RED_4));
        assertTrue(RED_4.isBetterThan(RED_3));
        assertTrue(RED_3.isBetterThan(RED_2));
        assertTrue(RED_2.isBetterThan(RED_1));

        assertTrue(VIOLET_2.isBetterThan(VIOLET_1));
    }

    @Test
    public void shouldCompareCardsByColor() {
        assertTrue(RED_7.isBetterThan(ORANGE_7));
        assertTrue(ORANGE_7.isBetterThan(YELLOW_7));
        assertTrue(YELLOW_7.isBetterThan(GREEN_7));
        assertTrue(GREEN_7.isBetterThan(BLUE_7));
        assertTrue(BLUE_7.isBetterThan(INDIGO_7));
        assertTrue(INDIGO_7.isBetterThan(VIOLET_7));

        assertTrue(RED_1.isBetterThan(ORANGE_1));
        assertTrue(ORANGE_1.isBetterThan(YELLOW_1));
        assertTrue(YELLOW_1.isBetterThan(GREEN_1));
        assertTrue(GREEN_1.isBetterThan(BLUE_1));
        assertTrue(BLUE_1.isBetterThan(INDIGO_1));
        assertTrue(INDIGO_1.isBetterThan(VIOLET_1));
    }

    @Test
    void shouldRed7BeBetterThanAnyOtherCard() {
        Stream.of(Card.values()).forEach(card -> {
            if (card != RED_7) {
                assertTrue(RED_7.isBetterThan(card));
            }
        });
    }

    @Test
    void shouldViolet1BeWorseThanAnyOtherCard() {
        Stream.of(Card.values()).forEach(card -> {
            if (card != VIOLET_1) {
                assertTrue(card.isBetterThan(VIOLET_1));
            }
        });
    }
}