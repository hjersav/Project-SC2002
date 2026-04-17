// Warrior Class (Specialized subclass)
public class Warrior extends Combatant {

    // Constructor for Warrior
    public Warrior() {
        super("Warrior", 260, 40, 20, 30); // Initialize Warrior's stats
    }

    @Override
    public void attack(Combatant target) {
        basicAttack(target);  // Perform a basic attack
    }

    @Override
    public void executeSpecialSkill(Combatant target) {
        // Warrior's Shield Bash: Deals damage and stuns the target
        shieldBash(target);
    }

    // Shield Bash - Special Skill for Warrior
    public void shieldBash(Combatant target) {
        int damage = Math.max(0, this.getAttack() - target.getAttack());
        target.takeDamage(damage);  // Apply damage

        // Stun the target for one turn by resetting their defense
        target.defend();
        System.out.println(this.getName() + " used Shield Bash on " + target.getName());
    }
}
