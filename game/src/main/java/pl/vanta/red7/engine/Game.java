package pl.vanta.red7.engine;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import pl.vanta.red7.game.Card;
import pl.vanta.red7.game.Deck;
import pl.vanta.red7.game.Rule;
import pl.vanta.red7.game.rules.HighestCardRule;

import static java.util.function.Predicate.not;
import static java.util.stream.Stream.generate;

public class Game implements GameState {
    public static final int MIN_NUMBER_OF_PLAYERS = 2;
    public static final int CARDS_PER_PLAYER = 8;

    private final Set<Player> passedPlayers = new HashSet<>();
    private final List<Player> players;
    private Player currentPlayer;

    private final Deck deck;
    private List<Card> rules = new LinkedList<>();

    public Game(List<Player> players, Deck deck) {
        assert players.size() >= MIN_NUMBER_OF_PLAYERS : "At least two players are required to start the game";
        assert deck.remainingCards() >= players.size() * CARDS_PER_PLAYER : "Not enough cards in the deck to start the game";

        this.deck = deck;
        this.players = players;

        players.forEach(
                player -> player.init(this, deck.take(CARDS_PER_PLAYER - 1), deck.take(1).iterator().next())
        );
    }

    @Override
    public Rule getCurrentRule() {
        return rules.isEmpty() ? new HighestCardRule() : rules.getLast().color().getRule();
    }

    @Override
    public List<PlayerView> getPlayers() {
        return List.copyOf(players);
    }

    @Override
    public void changeRule(Player player, Card cardRule) {
        checkTurn(player);
    }

    @Override
    public void changeRuleAndPutCardOnTable(Player player, Card cardRule, Card cardOnTable) {
        checkTurn(player);

    }

    @Override
    public void putCardOnTable(Player player, Card cardOnTable) {
        checkTurn(player);

    }

    @Override
    public void pass(Player player) {
        passedPlayers.add(player);
    }

    private void checkTurn(Player player) {
        if (player != currentPlayer) {
            throw new IllegalStateException("It's not " + player.getName() + "'s turn");
        }
    }

    public Player start() {
        var playersIterator = generate(() -> players)
                .flatMap(List::stream)
                .iterator();

        while (passedPlayers.size() < players.size() - 1) {
            //set current player            
            currentPlayer = playersIterator.next();

            //skip players who have already passed
            if (passedPlayers.contains(currentPlayer)) {
                continue;
            }

            currentPlayer.play();

            //if the current player passed, stop
            if (passedPlayers.contains(currentPlayer)) {
                continue;
            }

            //check if the current player is winning
            if (!isWinning(currentPlayer)) {
                passedPlayers.add(currentPlayer);
            }
        }

        return players.stream()
                .filter(not(passedPlayers::contains))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No player left to win the game"));
    }

    private boolean isWinning(Player player) {
        return false;
    }

    public Set<Card> getWinningCards() {
        return Set.of();
    }

    public Set<Card> getRemainingCards() {
        return Set.of();
    }
}
