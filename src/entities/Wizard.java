// Combatant Class - Base class for all characters (players & enemies)
public abstract class Combatant {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private int speed;

    // Constructor
    public Combatant(String name, int health, int attack, int defense, int speed) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    // Common combat methods
    public abstract void attack(Combatant target); // Abstract method for attack
    public void takeDamage(int damage) { this.health -= damage; }
    public boolean isAlive() { return this.health > 0; }

    // Getters and setters (omitted for brevity)
}

// Wizard Class - A specific type of Combatant
public class Wizard extends Combatant {
    private int arcaneAttackBonus; // Tracks bonus attack from special skill (Arcane Blast)

    public Wizard() {
        super("Wizard", 200, 50, 10, 20); // Wizard-specific stats
        this.arcaneAttackBonus = 0; // Initially no bonus
    }

    @Override
    public void attack(Combatant target) {
        int damage = Math.max(0, this.attack - target.defense);
        target.takeDamage(damage);
    }

    // Arcane Blast - Special Skill for Wizard
    public void arcaneBlast(Combatant[] targets) {
        // Deals damage to all enemies and increases Wizard's attack for each enemy defeated
        for (Combatant target : targets) {
            if (target.isAlive()) {
                int damage = Math.max(0, this.attack - target.defense);
                target.takeDamage(damage);
                if (!target.isAlive()) {
                    this.arcaneAttackBonus += 10; // Increase attack bonus for each defeated target
                    this.attack += arcaneAttackBonus; // Apply the attack bonus
                }
            }
        }
        System.out.println(this.getName() + " used Arcane Blast!");
    }

    @Override
    public void executeSpecialSkill(Combatant target) {
        // Wizard's special skill (Arcane Blast) affects all enemies
        // We can pass an array of enemies or multiple combatants to attack
        System.out.println("Wizard executes Arcane Blast!");
        arcaneBlast(new Combatant[]{target}); // Example with one target, can be extended
    }

    // Getter for the Wizard's attack
    public int getArcaneAttackBonus() {
        return arcaneAttackBonus;
    }
}
