package pl.vanta.red7.game.rules;

import java.util.Set;

import pl.vanta.red7.game.Card;
import pl.vanta.red7.game.Rule;

public class HighestCardRule implements Rule {
    @Override
    public String getName() {
        return "Highest Card";
    }

    @Override
    public Set<Card> getCardsForRule(Set<Card> cards) {
        return cards.stream()
                .max(Card::compareTo)
                .map(Set::of)
                .orElse(Set.of());
    }
}
