package turn_based_game;

public class Defend extends Action {

    public Defend() {
        super("Defend");
    }

    @Override
    public void execute(Combatant actor, Combatant target) {
        // Increases defense by 10 for the current round and the next round 
        
        // Pass 'actor' as both the user and target here because they are buffing themselves.
        actor.addStatusEffect(new DefendEffect()); 
        
        System.out.println(actor.getName() + " takes a defensive stance! Defense increased by 10 for 2 turns.");
    }
}