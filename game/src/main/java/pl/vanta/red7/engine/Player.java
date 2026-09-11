package pl.vanta.red7.engine;

import java.util.HashSet;
import java.util.Set;

import pl.vanta.red7.game.Card;

public final class Player implements PlayerView {
    private final String name;
    private final Set<Card> hand = new HashSet<>();
    private final Set<Card> table = new HashSet<>();
    private final Set<Card> wonCards = new HashSet<>();

    Player(String name) {
        this.name = name;
    }

    void init(Set<Card> hand, Card initialCard) {
        assert hand.size() == Game.CARDS_PER_PLAYER - 1 : "Player " + name + " should be dealt " + (Game.CARDS_PER_PLAYER - 1) + " cards, but got " + hand.size();
        assert !hand.contains(initialCard) : "Player " + name + " should not have initial card " + initialCard + " in hand";
        
        this.hand.clear();
        this.table.clear();

        this.hand.addAll(hand);
        this.table.add(initialCard);

        IO.println(name + ": I have been dealt " + hand + " and my initial card is " + initialCard);
    }

    void takeCards(Set<Card> cards) {
        this.wonCards.addAll(cards);
    }

    void putOnTable(Card card) {
        if (!hand.contains(card)) {
            throw new IllegalArgumentException("Player " + name + " does not have card " + card);
        }
        hand.remove(card);
        table.add(card);
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
        return Set.copyOf(table);
    }

    void play(GameState gameState) {
        gameState.pass(this);
    }

    @Override
    public String toString() {
        return name + " (" + getPoints() + " points)";
    }

    int getPoints() {
        return wonCards.stream().mapToInt(Card::value).sum();
    }
}
