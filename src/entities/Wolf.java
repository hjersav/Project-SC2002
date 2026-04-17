// Wolf Class - Extending the Enemy class
public class Wolf extends Enemy {

    // Constructor for Wolf
    public Wolf() {
        super("Wolf", 40, 45, 5, 35);  // Wolf-specific stats (HP: 40, Attack: 45, Defense: 5, Speed: 35)
    }

    // Wolf's basic attack (inherited from Combatant)
    @Override
    public void attack(Combatant target) {
        int damage = Math.max(0, this.getAttack() - target.getDefense());  // Calculate damage considering defense
        target.takeDamage(damage);  // Deal damage to the target
        System.out.println(this.getName() + " attacked " + target.getName() + " for " + damage + " damage.");
    }

    // Wolf's special ability - Can be extended, but here we'll make it a simple attack
    @Override
    public void specialAttack(Combatant target) {
        int biteDamage = 15;  // A Wolf's bite attack, for example
        target.takeDamage(biteDamage);  // Apply bite damage
        System.out.println(this.getName() + " used Bite Attack on " + target.getName() + " for " + biteDamage + " damage.");
    }

    // Overriding the special skill method for Wolf
    @Override
    public void executeSpecialSkill(Combatant target) {
        specialAttack(target);  // Wolf uses Bite Attack as its special skill
    }
}
