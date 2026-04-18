package turn_based_game;

import entities.Combatant;
import java.util.List;

public abstract class Action {
    protected String name;

    public Action(String name) {
        this.name = name;
    }

    // The BattleEngine will call this method.
    // It passes the combatant performing the action, and the target.
    public abstract void execute(Combatant actor, Combatant target);

    // Overloaded for AOE actions (e.g. Arcane Blast)
    public void execute(Combatant actor, List<Combatant> targets) {
        if (!targets.isEmpty()) {
            execute(actor, targets.get(0));
        }
    }

    public String getName() {
        return name;
    }
}
