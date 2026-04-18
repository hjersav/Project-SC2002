package turn_based_game;

public class BasicAttack extends Action {
    
    public BasicAttack() {
        super("Basic Attack");
    }

    @Override
    public void execute(Combatant actor, Combatant target) {
        // Need to implement getAttack() and getDefense()
        
        // Damage-max(0, Attacker Attack-Target Defense)
        int damage = Math.max(0, actor.getAttack() - target.getDefense());
        
        // Minimum HP post-damage is 0
        // Need to implement a takeDamage(int damage) method that automatically clamps the HP so it doesn't go below 0.
        target.takeDamage(damage); 
        
        System.out.println(actor.getName() + " uses Basic Attack on " + target.getName() + " for " + damage + " damage!");
    }
}