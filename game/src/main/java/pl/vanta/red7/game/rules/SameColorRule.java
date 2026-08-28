package pl.vanta.red7.game.rules;


import java.util.Set;

import pl.vanta.red7.game.Card;
import pl.vanta.red7.game.Rule;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class SameColorRule implements Rule {
    @Override
    public String getName() {
        return "Same Color";
    }

    @Override
    public Set<Card> getCardsForRule(Set<Card> cards) {
        return cards.stream()
                .collect(groupingBy(Card::color, toSet()))
                .values().stream()
                .max(comparingInt(Set<Card>::size).thenComparing(highestCardComparator()))
                .orElse(Set.of());
    }
}
