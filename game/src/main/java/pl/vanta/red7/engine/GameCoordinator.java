package pl.vanta.red7.engine;

import java.util.List;

import pl.vanta.red7.game.Deck;
import pl.vanta.red7.game.Rule;

import static pl.vanta.red7.game.Rule.HIGHEST_CARD;

public class GameCoordinator implements GameState {
    private static final int MIN_NUMBER_OF_PLAYERS = 2;
    private static final int CARDS_PER_PLAYER = 8;
    
    private final List<Player> players;
    private final Deck deck;

    private Rule currentRule = HIGHEST_CARD;

    public GameCoordinator(List<String> playerNames, Deck deck) {
        assert playerNames.size() >= MIN_NUMBER_OF_PLAYERS : "At least two players are required to start the game";
        assert deck.remainingCards() >= playerNames.size() * CARDS_PER_PLAYER : "Not enough cards in the deck to start the game";

        this.deck = deck;
        this.players = playerNames.stream()
                .map(name -> new Player(this, name, deck.take(CARDS_PER_PLAYER - 1), deck.take(1).iterator().next()))
                .toList();
    }

    @Override
    public Rule getCurrentRule() {
        return currentRule;
    }

    @Override
    public List<PlayerView> getPlayers() {
        return List.copyOf(players);
    }

    @Override
    public void changeRule(Rule newRule) {
        this.currentRule = newRule;
    }

    Player whoIsWinning() {
        return null;
    }
}
