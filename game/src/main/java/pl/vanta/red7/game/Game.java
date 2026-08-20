package pl.vanta.red7.game;

import java.util.List;

import static pl.vanta.red7.game.Rule.HIGHEST_CARD;

public class Game {
    private final List<Player> players;
    private final Deck deck;

    private Rule currentRule = HIGHEST_CARD;

    public Game(List<String> playerNames) {
        this.deck = new Deck();
        this.players = playerNames.stream()
                .map(name -> new Player(name, deck.take(7), deck.take(1).iterator().next()))
                .toList();
    }

    public boolean isFinished() {
        return false;
    }

    public Rule getCurrentRule() {
        return currentRule;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }
}
