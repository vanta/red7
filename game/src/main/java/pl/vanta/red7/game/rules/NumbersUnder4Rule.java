package pl.vanta.red7.game.rules;

import java.util.Set;

import pl.vanta.red7.game.Card;
import pl.vanta.red7.game.Rule;

import static java.util.stream.Collectors.toSet;

public class NumbersUnder4Rule implements Rule {
    @Override
    public String getName() {
        return "Cards Under 4";
    }

    @Override
    public Set<Card> getCardsForRule(Set<Card> cards) {
        return cards.stream()
                .filter(card -> card.value() < 4)
                .collect(toSet());
    }
}
