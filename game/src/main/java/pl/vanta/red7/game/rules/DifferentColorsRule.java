package pl.vanta.red7.game.rules;

import java.util.Set;

import pl.vanta.red7.game.Card;
import pl.vanta.red7.game.Rule;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class DifferentColorsRule implements Rule {
    @Override
    public String getName() {
        return "Different Colors";
    }

    @Override
    public Set<Card> getCardsForRule(Set<Card> cards) {
        return cards.stream()
                .collect(groupingBy(Card::color, toSet()))
                .values().stream()
                .map(group -> group.stream().max(Card::compareTo).orElseThrow())
                .collect(toSet());
    }
}
