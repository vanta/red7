package pl.vanta.red7.game;

import java.util.HashSet;
import java.util.Set;

public final class Player {
    private final String name;
    private final Set<Card> hand;
    private final Set<Card> table;

    Player(String name, Set<Card> hand, Card initialCard) {
        this.name = name;
        this.hand = hand;
        this.table = new HashSet<>();
        this.table.add(initialCard);
    }

    public Set<Card> getHand() {
        return hand;
    }

    public Set<Card> getTable() {
        return table;
    }
}
