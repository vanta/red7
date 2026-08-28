package pl.vanta.red7.game.rules;

import pl.vanta.red7.game.Card;

import static pl.vanta.red7.game.Color.BLUE;
import static pl.vanta.red7.game.Color.GREEN;
import static pl.vanta.red7.game.Color.INDIGO;
import static pl.vanta.red7.game.Color.ORANGE;
import static pl.vanta.red7.game.Color.RED;
import static pl.vanta.red7.game.Color.VIOLET;
import static pl.vanta.red7.game.Color.YELLOW;

abstract class RuleBaseTest {
    static Card R7 = new Card(7, RED);
    static Card R6 = new Card(6, RED);
    static Card R5 = new Card(5, RED);
    static Card R4 = new Card(4, RED);
    static Card R3 = new Card(3, RED);
    static Card R2 = new Card(2, RED);
    static Card R1 = new Card(1, RED);

    static Card O7 = new Card(7, ORANGE);
    static Card Y7 = new Card(7, YELLOW);
    static Card G7 = new Card(7, GREEN);
    static Card B7 = new Card(7, BLUE);
    static Card I7 = new Card(7, INDIGO);
    static Card V7 = new Card(7, VIOLET);

    static Card O1 = new Card(1, ORANGE);
    static Card O2 = new Card(2, ORANGE);

    static Card Y1 = new Card(1, YELLOW);
    static Card Y2 = new Card(2, YELLOW);
    static Card Y3 = new Card(3, YELLOW);

}
