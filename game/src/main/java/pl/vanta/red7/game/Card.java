package pl.vanta.red7.game;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BACK_COLOR;
import static com.diogonunes.jcolor.Attribute.BLUE_BACK;
import static com.diogonunes.jcolor.Attribute.BRIGHT_WHITE_TEXT;
import static com.diogonunes.jcolor.Attribute.BRIGHT_YELLOW_BACK;
import static com.diogonunes.jcolor.Attribute.GREEN_BACK;
import static java.util.Comparator.comparingInt;

public record Card(int value, Color color) implements Comparable<Card> {

    public boolean isBetterThan(Card other) {
        return compareTo(other) > 0;
    }

    @Override
    public int compareTo(Card o) {
        return comparingInt(Card::value)
                .thenComparing(Card::color, Color.getComparator())
                .compare(this, o);
    }

    @Override
    public String toString() {
        var backColor = switch (color) {
            case RED -> BACK_COLOR(120, 25, 37);
            case ORANGE -> BACK_COLOR(220, 88, 42);
            case YELLOW -> BRIGHT_YELLOW_BACK();
            case GREEN -> GREEN_BACK();
            case BLUE -> BLUE_BACK();
            case INDIGO -> BACK_COLOR(50, 20, 82);
            case VIOLET -> BACK_COLOR(150, 0, 255);
        };
        
        return colorize(" " + value + " ", BRIGHT_WHITE_TEXT(), backColor);
    }
}
