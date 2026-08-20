package pl.vanta.red7.game;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

public class Deck {
    private final Random random = new Random();
    private final List<Card> cards = new ArrayList<>(EnumSet.allOf(Card.class));

    public Set<Card> take(int count) {
        if (count > cards.size()) {
            throw new IllegalArgumentException("Not enough cards in the deck");
        }

        return range(0, count)
                .map(i -> random.nextInt(cards.size()))
                .mapToObj(cards::remove)
                .collect(toSet());
    }

    public int remainingCards() {
        return cards.size();
    }
}
