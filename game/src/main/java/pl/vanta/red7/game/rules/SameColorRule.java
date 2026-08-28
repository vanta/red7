package pl.vanta.red7.game.rules;


import java.util.Set;

import pl.vanta.red7.game.Card;
import pl.vanta.red7.game.Rule;

public class SameColorRule implements Rule {
    @Override
    public String getName() {
        return "Same Color";
    }

    @Override
    public Set<Card> getCardsForRule(Set<Card> cards) {
        return Set.of();
    }
}
