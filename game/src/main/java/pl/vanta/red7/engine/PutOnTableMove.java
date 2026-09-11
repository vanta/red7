package pl.vanta.red7.engine;

import pl.vanta.red7.game.Card;

public record PutOnTableMove(Card tableCard) implements Move {
}
