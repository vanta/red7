package pl.vanta.red7.engine;

import java.util.HashSet;
import java.util.Set;

import pl.vanta.red7.game.Card;

public final class Player implements PlayerView {
    private final GameState gameState;
    private final String name;
    private final Set<Card> hand;
    private final Set<Card> table;

    public Player(GameState gameState, String name, Set<Card> hand, Card initialCard) {
        this.name = name;
        this.hand = hand;
        this.gameState = gameState;
        this.table = new HashSet<>();
        this.table.add(initialCard);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRemainingCards() {
        return hand.size();
    }

    @Override
    public Set<Card> getTable() {
        return table;
    }
}
