package pl.vanta.red7.game;

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
}
