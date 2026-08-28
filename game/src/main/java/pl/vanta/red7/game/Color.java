package pl.vanta.red7.game;

import java.util.Comparator;

import pl.vanta.red7.game.rules.ConsecutiveNumbersRule;
import pl.vanta.red7.game.rules.DifferentColorsRule;
import pl.vanta.red7.game.rules.EvenCardsRule;
import pl.vanta.red7.game.rules.HighestCardRule;
import pl.vanta.red7.game.rules.NumbersUnder4Rule;
import pl.vanta.red7.game.rules.SameColorRule;
import pl.vanta.red7.game.rules.SameNumberRule;

public enum Color {
    RED(7, new HighestCardRule()),
    ORANGE(6, new SameNumberRule()),
    YELLOW(5, new SameColorRule()),
    GREEN(4, new EvenCardsRule()),
    BLUE(3, new DifferentColorsRule()),
    INDIGO(2, new ConsecutiveNumbersRule()),
    VIOLET(1, new NumbersUnder4Rule());

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

    public static Comparator<Color> getComparator() {
        return Comparator.comparingInt(Color::getOrder);
    }
}
