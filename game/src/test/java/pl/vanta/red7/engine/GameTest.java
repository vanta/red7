package pl.vanta.red7.engine;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import pl.vanta.red7.game.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest extends BaseTest {
    private final Player p1 = new Player("Player 1");
    private final Player p2 = new Player("Player 2");
    private final Player p3 = new Player("Player 3");

    @Test
    void shouldNotAllowToCreateGameWithLessThanTwoPlayers() {
        //when
        var exception = assertThrows(AssertionError.class, () -> new Game(List.of(p1)));

        //then
        assertEquals("At least two players are required to start the game", exception.getMessage());
    }

    @Test
    void shouldFindTheWinnerAtTheBeginningOfTheGame() {
        //given
        p2.init(Set.of(), O7);
        p3.init(Set.of(), Y7);
        p1.init(Set.of(), R6);

        var game = new Game(List.of(p1, p2, p3));

        //when
        var winner = game.getWinner();

        //then
        assertEquals(p2, winner);
    }

    @Test
    void shouldFindTheWinner() {
        //given
        p1.init(Set.of(O7, O6, O5), O1);
        p2.init(Set.of(R5, R6, R7), R1);
        p3.init(Set.of(Y5, Y6, Y7), Y1);

        //when
        var game = new Game(List.of(p1, p2, p3));
        game.start();

        //then
        assertEquals(p3, game.getWinner());
    }

}