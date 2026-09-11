package pl.vanta.red7.engine;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import pl.vanta.red7.game.Card;
import pl.vanta.red7.game.Rule;
import pl.vanta.red7.game.rules.HighestCardRule;

import static java.util.Comparator.comparingInt;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.generate;

class Game implements GameState {
    static final int CARDS_PER_PLAYER = 8;
    private static final int MIN_NUMBER_OF_PLAYERS = 2;
    private static final HighestCardRule HIGHEST_CARD_RULE = new HighestCardRule();

    private final Set<Player> passedPlayers = new HashSet<>();
    private final List<Player> players;
    private final List<Card> rules = new LinkedList<>();

    private Player currentPlayer;

    Game(List<Player> players) {
        assert players.size() >= MIN_NUMBER_OF_PLAYERS : "At least two players are required to start the game";

        this.players = players;
    }

    @Override
    public Rule getCurrentRule() {
        return rules.isEmpty() ? HIGHEST_CARD_RULE : rules.getLast().color().getRule();
    }

    @Override
    public List<PlayerView> getPlayers() {
        return List.copyOf(players);
    }

    Player start() {
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

            switch (currentPlayer.play(this)) {
                case PassMove _ -> pass();
                case ChangeRuleMove move -> changeRule(move.card());
                case PutOnTableMove move -> putOnTable(move.card());
                case ChangeRuleAndPutOnTableMove move -> both(move);
                case null, default -> throw new IllegalStateException();
            }

            // check if the current player is winning, i.e. if the move was legitimate,
            // otherwise they have to pass
            if (getWinner() != currentPlayer && !passedPlayers.contains(currentPlayer)) {
                IO.println(currentPlayer.getName() + " is not winning the game, they have to pass");
                pass();
            }
        }

        return players.stream()
                .filter(not(passedPlayers::contains))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No player left to win the game"));
    }

    private void both(ChangeRuleAndPutOnTableMove move) {
        IO.println(currentPlayer.getName() + " has changed the rule to " + move.changeRuleCard().color().getRule().getName() + " and put " + move.tableCard() + " on the table");
        rules.add(move.changeRuleCard());
        currentPlayer.putOnTable(move.tableCard());
    }

    private void putOnTable(Card move) {
        IO.println(currentPlayer.getName() + " has put " + move + " on the table");
        currentPlayer.putOnTable(move);
    }

    private void changeRule(Card move) {
        IO.println(currentPlayer.getName() + " has changed the rule to " + move.color().getRule().getName());
        rules.add(move);
    }

    private void pass() {
        IO.println(currentPlayer.getName() + " has passed");
        passedPlayers.add(currentPlayer);
    }

    Player getWinner() {
        return players.stream()
                .filter(not(passedPlayers::contains))
                .max(playerComparator())
                .orElseThrow(() -> new IllegalStateException("No player left to win the game"));
    }

    private Comparator<Player> playerComparator() {
        return (p1, p2) -> cardsComparator().compare(getCurrentRule().getCardsForRule(p1.getTable()), getCurrentRule().getCardsForRule(p2.getTable()));
    }

    private Comparator<Set<Card>> cardsComparator() {
        return comparingInt(Set<Card>::size)
                .thenComparing(getCurrentRule().highestCardComparator());
    }

    Set<Card> getCardsForRule(Player player) {
        return getCurrentRule().getCardsForRule(player.getTable());
    }

    Set<Card> getRemainingCards(Set<Card> wonCards) {
        var tableCards = players.stream()
                .flatMap(p -> p.getTable().stream())
                .collect(toSet());

        var remainTableCards = tableCards.stream()
                .filter(not(wonCards::contains))
                .collect(toSet());

        var allHandsCards = players.stream()
                .flatMap(p -> p.getHand().stream())
                .collect(toSet());

        return Stream.of(remainTableCards, allHandsCards)
                .flatMap(Set::stream)
                .collect(toSet());
    }
}
