package pl.vanta.red7.engine;

import java.util.HashSet;
import java.util.Set;

import pl.vanta.red7.game.Card;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notBlank;

public final class Player implements PlayerView {
    private final String name;
    private final Set<Card> hand = new HashSet<>();
    private final Set<Card> table = new HashSet<>();
    private final Set<Card> wonCards = new HashSet<>();

    Player(String name) {
        this.name = notBlank(name, "name cannot be empty");
    }

    void init(Set<Card> hand, Card initialCard) {
        isTrue(hand.size() == Game.CARDS_PER_PLAYER - 1, "Player %s should be dealt %d cards, but got %d", name, Game.CARDS_PER_PLAYER - 1, hand.size());
        isTrue(!hand.contains(initialCard), "Player %s should not have initial card %s in hand", name, initialCard);

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

    public Set<Card> getHand() {
        return Set.copyOf(hand);
    }

    Move play(GameState gameState) {
        return hand.stream()
                .max(Card::compareTo)
                .map(card -> (Move) new PutOnTableMove(card))
                .orElseGet(PassMove::new);
    }

    @Override
    public String toString() {
        return name + " (" + getPoints() + " points)";
    }

    int getPoints() {
        return wonCards.stream().mapToInt(Card::value).sum();
    }
}
