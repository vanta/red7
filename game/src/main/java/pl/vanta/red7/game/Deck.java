package pl.vanta.red7.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.generate;
import static java.util.stream.IntStream.range;

public class Deck {
    public static final int MAX_CARDS = 49;
    private final Random random = new Random();
    private final List<Card> cards = new ArrayList<>(allCards());

    public Set<Card> take(int count) {
        if (count > cards.size()) {
            throw new IllegalArgumentException("Not enough cards in the deck");
        }

        return generate(() -> cards.remove(random.nextInt(cards.size())))
                .limit(count)
                .collect(toSet());
    }

    public void putBack(Set<Card> returnedCards) {
        cards.addAll(returnedCards);
    }

    public int remainingCards() {
        return cards.size();
    }

    public static Set<Card> allCards() {
        return stream(Color.values())
                .map(color -> range(1, 8)
                        .mapToObj(value -> new Card(value, color))
                        .collect(toSet()))
                .flatMap(Set::stream)
                .collect(toSet());
    }
}
