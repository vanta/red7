package pl.vanta.red7.game;

import static pl.vanta.red7.game.Color.BLUE;
import static pl.vanta.red7.game.Color.GREEN;
import static pl.vanta.red7.game.Color.INDIGO;
import static pl.vanta.red7.game.Color.ORANGE;
import static pl.vanta.red7.game.Color.RED;
import static pl.vanta.red7.game.Color.VIOLET;
import static pl.vanta.red7.game.Color.YELLOW;

public enum Card {
    RED_7(7, RED),
    RED_6(6, RED),
    RED_5(5, RED),
    RED_4(4, RED),
    RED_3(3, RED),
    RED_2(2, RED),
    RED_1(1, RED),

    ORANGE_7(7, ORANGE),
    ORANGE_6(6, ORANGE),
    ORANGE_5(5, ORANGE),
    ORANGE_4(4, ORANGE),
    ORANGE_3(3, ORANGE),
    ORANGE_2(2, ORANGE),
    ORANGE_1(1, ORANGE),

    YELLOW_7(7, YELLOW),
    YELLOW_6(6, YELLOW),
    YELLOW_5(5, YELLOW),
    YELLOW_4(4, YELLOW),
    YELLOW_3(3, YELLOW),
    YELLOW_2(2, YELLOW),
    YELLOW_1(1, YELLOW),

    GREEN_7(7, GREEN),
    GREEN_6(6, GREEN),
    GREEN_5(5, GREEN),
    GREEN_4(4, GREEN),
    GREEN_3(3, GREEN),
    GREEN_2(2, GREEN),
    GREEN_1(1, GREEN),

    BLUE_7(7, BLUE),
    BLUE_6(6, BLUE),
    BLUE_5(5, BLUE),
    BLUE_4(4, BLUE),
    BLUE_3(3, BLUE),
    BLUE_2(2, BLUE),
    BLUE_1(1, BLUE),

    INDIGO_7(7, INDIGO),
    INDIGO_6(6, INDIGO),
    INDIGO_5(5, INDIGO),
    INDIGO_4(4, INDIGO),
    INDIGO_3(3, INDIGO),
    INDIGO_2(2, INDIGO),
    INDIGO_1(1, INDIGO),

    VIOLET_7(7, VIOLET),
    VIOLET_6(6, VIOLET),
    VIOLET_5(5, VIOLET),
    VIOLET_4(4, VIOLET),
    VIOLET_3(3, VIOLET),
    VIOLET_2(2, VIOLET),
    VIOLET_1(1, VIOLET);

    private final int value;
    private final Color color;

    Card(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    boolean isBetterThan(Card other) {
        if (this.value == other.value) {
            return this.color.getOrder() > other.color.getOrder();
        }
        return this.value > other.value;
    }
}
