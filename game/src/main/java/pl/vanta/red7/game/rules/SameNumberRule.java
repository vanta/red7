package pl.vanta.red7.game.rules;


import java.util.Set;
import java.util.function.Function;

import pl.vanta.red7.game.Card;
import pl.vanta.red7.game.Rule;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class SameNumberRule implements Rule {
    @Override
    public String getName() {
        return "Same Number";
    }

    @Override
    public Set<Card> getCardsForRule(Set<Card> cards) {
        return cards.stream()
                .collect(groupingBy(Card::value, toSet()))
                .values().stream()
                .max(comparingInt(Set<Card>::size).thenComparing(highestCardComparator()))
                .orElse(Set.of());
    }
}
