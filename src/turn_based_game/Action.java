package turn_based_game;

public abstract class Action {
    protected String name;

    public Action(String name) {
        this.name = name;
    }

    // The BattleEngine will call this method. 
    // It passes the combatant performing the action, and the target.
    public abstract void execute(Combatant actor, Combatant target);

    public String getName() {
        return name;
    }
}
