package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import entities.Combatant;

public class TurnOrderStrategy {
    public List<Combatant> determineTurnOrder(List<Combatant> combatants) {
        List<Combatant> turnOrder = new ArrayList<>(combatants);
        Collections.sort(turnOrder, new Comparator<Combatant>() {
            @Override
            public int compare(Combatant c1, Combatant c2) {
                return Integer.compare(c2.getSpeed(), c1.getSpeed());
            }
        });
        return turnOrder;
    }
}
