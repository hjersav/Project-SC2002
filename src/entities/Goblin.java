// Goblin Class - Extending the Enemy class
public class Goblin extends Enemy {

    // Constructor for Goblin
    public Goblin() {
        super("Goblin", 55, 35, 15, 25);  // Goblin-specific stats (HP: 55, Attack: 35, Defense: 15, Speed: 25)
    }

    // Goblin's basic attack (inherited from Enemy)
    @Override
    public void attack(Combatant target) {
        int damage = Math.max(0, this.getAttack() - target.getDefense());  // Calculate damage considering defense
        target.takeDamage(damage);  // Deal damage to the target
        System.out.println(this.getName() + " attacked " + target.getName() + " for " + damage + " damage.");
    }

    // Goblin's special attack: Poison Attack
    @Override
    public void specialAttack(Combatant target) {
        int poisonDamage = 10;  // Poison damage dealt over time
        target.takeDamage(poisonDamage);  // Apply poison damage immediately
        System.out.println(this.getName() + " used Poison Attack on " + target.getName() + " for " + poisonDamage + " damage.");

        // Optionally, you could add more complex behavior, like multiple turns of poison damage
    }

    // Overriding the special skill method for goblin
    @Override
    public void executeSpecialSkill(Combatant target) {
        specialAttack(target);  // Goblin uses Poison Attack as its special skill
    }
}
