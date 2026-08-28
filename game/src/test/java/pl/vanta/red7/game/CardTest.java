package pl.vanta.red7.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.vanta.red7.game.Color.BLUE;
import static pl.vanta.red7.game.Color.GREEN;
import static pl.vanta.red7.game.Color.INDIGO;
import static pl.vanta.red7.game.Color.ORANGE;
import static pl.vanta.red7.game.Color.RED;
import static pl.vanta.red7.game.Color.VIOLET;
import static pl.vanta.red7.game.Color.YELLOW;

class CardTest {

    @Test
    void shouldCompareCardsByValue() {
        assertTrue(new Card(7, RED).isBetterThan(new Card(6, RED)));
        assertTrue(new Card(6, RED).isBetterThan(new Card(5, RED)));
        assertTrue(new Card(5, RED).isBetterThan(new Card(4, RED)));
        assertTrue(new Card(4, RED).isBetterThan(new Card(3, RED)));
        assertTrue(new Card(3, RED).isBetterThan(new Card(2, RED)));
        assertTrue(new Card(2, RED).isBetterThan(new Card(1, RED)));

        assertTrue(new Card(2, VIOLET).isBetterThan(new Card(1, VIOLET)));
    }

    @Test
    public void shouldCompareCardsByColor() {
        assertTrue(new Card(7, RED).isBetterThan(new Card(7, ORANGE)));
        assertTrue(new Card(7, ORANGE).isBetterThan(new Card(7, YELLOW)));
        assertTrue(new Card(7, YELLOW).isBetterThan(new Card(7, GREEN)));
        assertTrue(new Card(7, GREEN).isBetterThan(new Card(7, BLUE)));
        assertTrue(new Card(7, BLUE).isBetterThan(new Card(7, INDIGO)));
        assertTrue(new Card(7, INDIGO).isBetterThan(new Card(7, VIOLET)));

        assertTrue(new Card(1, RED).isBetterThan(new Card(1, ORANGE)));
        assertTrue(new Card(1, ORANGE).isBetterThan(new Card(1, YELLOW)));
        assertTrue(new Card(1, YELLOW).isBetterThan(new Card(1, GREEN)));
        assertTrue(new Card(1, GREEN).isBetterThan(new Card(1, BLUE)));
        assertTrue(new Card(1, BLUE).isBetterThan(new Card(1, INDIGO)));
        assertTrue(new Card(1, INDIGO).isBetterThan(new Card(1, VIOLET)));
    }

    @Test
    void shouldRed7BeBetterThanAnyOtherCard() {
        Deck.allCards().forEach(card -> {
            if (!card.equals(new Card(7, RED))) {
                assertTrue(new Card(7, RED).isBetterThan(card));
            }
        });
    }

    @Test
    void shouldViolet1BeWorseThanAnyOtherCard() {
        Deck.allCards().forEach(card -> {
            if (!card.equals(new Card(1, VIOLET))) {
                assertTrue(card.isBetterThan(new Card(1, VIOLET)));
            }
        });
    }
}