package pl.vanta.red7.game;

import java.util.Set;

public interface Rule {
    String getName();

    Set<Card> getCardsForRule(Set<Card> cards);
}
