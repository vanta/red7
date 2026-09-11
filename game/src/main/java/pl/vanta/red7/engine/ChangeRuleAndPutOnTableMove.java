package pl.vanta.red7.engine;

import pl.vanta.red7.game.Card;

public record ChangeRuleAndPutOnTableMove(Card changeRuleCard, Card tableCard) implements Move {
}
