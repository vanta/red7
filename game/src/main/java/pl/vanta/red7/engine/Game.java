package pl.vanta.red7.engine;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import pl.vanta.red7.game.Card;
import pl.vanta.red7.game.Rule;
import pl.vanta.red7.game.rules.HighestCardRule;

import static java.util.Comparator.comparingInt;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.concat;
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

//    @Override
//    public void changeRule(Player player, Card cardRule) {
//        checkTurn(player);
//
//        rules.add(cardRule);
//    }
//
//    @Override
//    public void changeRuleAndPutCardOnTable(Player player, Card cardRule, Card cardOnTable) {
//        checkTurn(player);
//
//        rules.add(cardRule);
//        player.putOnTable(cardOnTable);
//    }
//
//    @Override
//    public void putCardOnTable(Player player, Card cardOnTable) {
//        checkTurn(player);
//
//        player.putOnTable(cardOnTable);
//    }

    private void checkTurn(Player player) {
        if (player != currentPlayer) {
            throw new IllegalStateException("It's not " + player.getName() + "'s turn");
        }
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
                case PassMove _ -> passedPlayers.add(currentPlayer);
                case ChangeRuleMove changeRuleMove -> rules.add(changeRuleMove.changeRuleCard());
                case PutOnTableMove putOnTableMove -> currentPlayer.putOnTable(putOnTableMove.tableCard());
                case ChangeRuleAndPutOnTableMove changeRuleAndPutCardOnTableMove -> {
                    rules.add(changeRuleAndPutCardOnTableMove.changeRuleCard());
                    currentPlayer.putOnTable(changeRuleAndPutCardOnTableMove.tableCard());
                }
                case null, default -> {
                    throw new IllegalStateException();
                }
            }

            //check if the current player is winning
            if (getWinner() != currentPlayer) {
                passedPlayers.add(currentPlayer);
            }
        }

        return players.stream()
                .filter(not(passedPlayers::contains))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No player left to win the game"));
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

    Set<Card> getRemainingCards(Player winner) {
        var losersCards = players.stream()
                .filter(not(p -> p.equals(winner)))
                .flatMap(p -> p.getTable().stream())
                .collect(toSet());

        var winnerCards = getCardsForRule(winner);
        var winnerRemainingCards = winner.getTable().stream()
                .filter(not(winnerCards::contains))
                .collect(toSet());

        return concat(losersCards.stream(), winnerRemainingCards.stream())
                .collect(toSet());
    }
}
