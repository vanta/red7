package pl.vanta.red7.game;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.vanta.red7.game.Deck.*;

class DeckTest {

    @Test
    void shouldTakeCards() {
        Deck deck = new Deck();
        int initialSize = deck.remainingCards();
        int count = 5;

        var takenCards = deck.take(count);

        assertEquals(count, takenCards.size());
        assertEquals(initialSize - count, deck.remainingCards());
    }

    @Test
    void shouldNotTakeMoreCardsThanAvailable() {
        Deck deck = new Deck();
        int count = deck.remainingCards() + 1;

        assertThrows(IllegalArgumentException.class, () -> deck.take(count));
    }

    @Test
    void shouldTakeAllDifferentCards() {
        //given
        Deck deck = new Deck();

        //when
        var takenCards1 = deck.take(25);
        var takenCards2 = deck.take(24);

        //then
        assertTrue(takenCards1.stream().noneMatch(takenCards2::contains));
    }

    @Test
    void shouldTakeEveryCardOnce() {
        //given
        Deck deck = new Deck();
        Set<Card> takenCards = new HashSet<>();

        //when 1 card 49 times to get all cards
        for (int i = 0; i < MAX_CARDS; i++) {
            takenCards.addAll(deck.take(1));
        }

        //then
        assertEquals(MAX_CARDS, takenCards.size());
        assertEquals(0, deck.remainingCards());
    }

    @Test
    void shouldPutBackCards() {
        //given
        Deck deck = new Deck();
        Set<Card> takenCards = deck.take(5);

        //when
        deck.putBack(takenCards);

        //then
        assertEquals(MAX_CARDS, deck.remainingCards());
    }
}