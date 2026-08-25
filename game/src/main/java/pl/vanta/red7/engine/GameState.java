package pl.vanta.red7.engine;


import java.util.List;

import pl.vanta.red7.game.Rule;

public interface GameState {
    Rule getCurrentRule();

    List<PlayerView> getPlayers();
    
    void changeRule(Rule newRule);
}
