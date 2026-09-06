package pl.vanta.red7.engine;


import java.util.Arrays;
import java.util.List;

import pl.vanta.red7.game.Deck;

import static java.util.Comparator.comparingInt;
import static pl.vanta.red7.engine.Game.CARDS_PER_PLAYER;

public class Coordinator {

    public void startGame(String... playerNames) {
        var players = Arrays.stream(playerNames)
                .map(Player::new)
                .toList();

        var deck = new Deck();

        while (thereAreEnoughCardsToPlay(deck, players)) {
            players.forEach(
                    player -> player.init(deck.take(CARDS_PER_PLAYER - 1), deck.take(1).iterator().next())
            );

            var game = new Game(players);
            var winner = game.start();
            IO.println("The winner is: " + winner.getName());

            //take the winning cards
            winner.takeCards(game.getCardsForRule(winner));
            deck.putBack(game.getRemainingCards(winner));
        }

        players.stream()
                .sorted(comparingInt(Player::getPoints))
                .forEach(IO::println);
    }

    private static boolean thereAreEnoughCardsToPlay(Deck deck, List<Player> players) {
        return deck.remainingCards() >= players.size() * CARDS_PER_PLAYER;
    }

}
