package pl.vanta.red7.game;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

public class Deck {
    public static final int MAX_CARDS = 49;
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

    public void putBack(Set<Card> returnedCards) {
        cards.addAll(returnedCards);
    }

    public int remainingCards() {
        return cards.size();
    }
}
