package pl.vanta.red7.game;

import java.util.Set;
import java.util.function.Function;

public interface Rule {
    String getName();

    Set<Card> getCardsForRule(Set<Card> cards);

    default Function<Set<Card>, Card> highestCardComparator() {
        return group -> group.stream().max(Card::compareTo).orElseThrow();
    }

}
