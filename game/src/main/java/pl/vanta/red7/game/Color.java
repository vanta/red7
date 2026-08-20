package pl.vanta.red7.game;

import static pl.vanta.red7.game.Rule.HIGHEST_CARD;
import static pl.vanta.red7.game.Rule.MOST_CARDS_OF_DIFFERENT_COLORS;
import static pl.vanta.red7.game.Rule.MOST_CARDS_OF_ONE_COLOR;
import static pl.vanta.red7.game.Rule.MOST_CARDS_OF_ONE_NUMBER;
import static pl.vanta.red7.game.Rule.MOST_CARDS_UNDER_4;
import static pl.vanta.red7.game.Rule.MOST_CONSECUTIVE_CARDS;
import static pl.vanta.red7.game.Rule.MOST_EVEN_CARDS;

public enum Color {
    RED(7, HIGHEST_CARD),
    ORANGE(6, MOST_CARDS_OF_ONE_NUMBER),
    YELLOW(5, MOST_CARDS_OF_ONE_COLOR),
    GREEN(4, MOST_EVEN_CARDS),
    BLUE(3, MOST_CARDS_OF_DIFFERENT_COLORS),
    INDIGO(2, MOST_CONSECUTIVE_CARDS),
    VIOLET(1, MOST_CARDS_UNDER_4);

    private final int order;
    private final Rule rule;

    Color(int order, Rule rule) {
        this.order = order;
        this.rule = rule;
    }

    public int getOrder() {
        return order;
    }

    public Rule getRule() {
        return rule;
    }
}
