package pl.vanta.red7.game;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static pl.vanta.red7.game.Rule.HIGHEST_CARD;

class GameTest {
    @Test
    public void shouldInitialStateBeCorrect() {
        //given

        //when
        var game = new Game(List.of("Alice", "Bob", "Charlie"));

        //then
        assertFalse(game.isFinished());
        assertEquals(HIGHEST_CARD, game.getCurrentRule());
        assertEquals(3, game.getPlayers().size());
        assertEquals(Deck.MAX_CARDS - 3 * 7 - 3, game.getDeck().remainingCards());

        for (Player p : game.getPlayers()) {
            assertEquals(7, p.getHand().size());
            assertEquals(1, p.getTable().size());
        }
    }

}