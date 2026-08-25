package pl.vanta.red7.engine;


import java.util.Set;

import pl.vanta.red7.game.Card;

public interface PlayerView {
    
    String getName();

    int getRemainingCards();

    Set<Card> getTable();
}
