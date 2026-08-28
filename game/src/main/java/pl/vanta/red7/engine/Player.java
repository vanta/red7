package pl.vanta.red7.engine;

import java.util.HashSet;
import java.util.Set;

import pl.vanta.red7.game.Card;

public final class Player implements PlayerView {
    private final String name;
    private final Set<Card> hand = new HashSet<>();
    private final Set<Card> table = new HashSet<>();
    private final Set<Card> wonCards = new HashSet<>();

    private GameState gameState;

    public Player(String name) {
        this.name = name;
    }

    public void init(GameState gameState, Set<Card> hand, Card initialCard) {
        this.gameState = gameState;
        this.hand.clear();
        this.table.clear();

        this.hand.addAll(hand);
        this.table.add(initialCard);

        IO.println(name + ": I have been dealt " + hand + " and my initial card is " + initialCard);
    }

    public void takeCards(Set<Card> cards) {
        this.wonCards.addAll(cards);
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

    public void play() {
        gameState.pass(this);
    }

    @Override
    public String toString() {
        return name + " (" + getPoints() + " points)";
    }

    public int getPoints() {
        return wonCards.stream().mapToInt(Card::value).sum();
    }
}
