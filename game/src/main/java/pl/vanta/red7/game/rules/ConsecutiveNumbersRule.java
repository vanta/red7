package pl.vanta.red7.game.rules;


import java.util.Set;

import pl.vanta.red7.game.Card;
import pl.vanta.red7.game.Rule;

public class ConsecutiveNumbersRule implements Rule {
    @Override
    public String getName() {
        return "Consecutive Numbers";
    }

    @Override
    public Set<Card> getCardsForRule(Set<Card> cards) {
        return Set.of();
    }
}
