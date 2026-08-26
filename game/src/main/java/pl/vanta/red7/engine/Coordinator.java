package pl.vanta.red7.engine;


import java.util.Arrays;
import java.util.List;

import pl.vanta.red7.game.Deck;

public class Coordinator {

    public void startGame(String... playerNames) {
        var players = Arrays.stream(playerNames)
                .map(Player::new)
                .toList();

        var deck = new Deck();

        while (thereAreEnoughCards(deck, players)) {
            var game = new Game(players, deck);

            var winner = game.start();
            IO.println("The winner is: " + winner.getName());

            //take th winning cards
            winner.takeCards(game.getWinningCards());
            deck.putBack(game.getRemainingCards());
        }

    }

    private static boolean thereAreEnoughCards(Deck deck, List<Player> players) {
        return deck.remainingCards() >= players.size() * Game.CARDS_PER_PLAYER;
    }

}
