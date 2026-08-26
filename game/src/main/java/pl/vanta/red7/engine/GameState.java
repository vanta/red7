package pl.vanta.red7.engine;


import java.util.List;

import pl.vanta.red7.game.Card;
import pl.vanta.red7.game.Rule;

public interface GameState {
    Rule getCurrentRule();

    List<PlayerView> getPlayers();

    void changeRule(Player player, Card cardRule);

    void changeRuleAndPutCardOnTable(Player player, Card cardRule, Card cardOnTable);

    void putCardOnTable(Player player, Card cardOnTable);

    void pass(Player player);
}
