package pl.vanta.red7.game.rules;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.vanta.red7.game.Card;
import pl.vanta.red7.game.Rule;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;

public class ConsecutiveNumbersRule implements Rule {
    @Override
    public String getName() {
        return "Consecutive Numbers";
    }

    @Override
    public Set<Card> getCardsForRule(Set<Card> cards) {
        return cards.stream()
                .collect(groupingBy(Card::color))
                .values().stream()
                .flatMap(group -> consecutiveRuns(group).stream())
                .max(comparingInt(List<Card>::size).thenComparing(List::getLast))
                .map(HashSet::new)
                .orElseGet(HashSet::new);
    }

    private List<List<Card>> consecutiveRuns(List<Card> cards) {
        List<Card> sorted = cards.stream().sorted().toList();
        List<List<Card>> runs = new ArrayList<>();
        List<Card> current = new ArrayList<>();
        for (Card card : sorted) {
            if (current.isEmpty() || card.value() == current.getLast().value() + 1) {
                current.add(card);
            } else {
                runs.add(current);
                current = new ArrayList<>();
                current.add(card);
            }
        }
        if (!current.isEmpty()) {
            runs.add(current);
        }
        return runs;
    }
}
