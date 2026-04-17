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

// Enemy Class - A specialized subclass for enemies
public class Enemy extends Combatant {

    public Enemy(String name, int health, int attack, int defense, int speed) {
        super(name, health, attack, defense, speed); // Initialize with enemy stats
    }

    // Enemies perform a basic attack
    @Override
    public void attack(Combatant target) {
        int damage = Math.max(0, this.attack - target.defense);
        target.takeDamage(damage);
    }

    // Custom behavior for enemies can be added here, such as special attacks
    public void specialAttack(Combatant target) {
        // For example, the Goblin might have a poison attack
        int poisonDamage = 10; // Damage for poison
        target.takeDamage(poisonDamage);
        System.out.println(this.getName() + " used a poison attack on " + target.getName());
    }

    // Overriding special skill method (no special skill for enemies in this basic case)
    @Override
    public void executeSpecialSkill(Combatant target) {
        specialAttack(target);  // Using special attack as an example of skill
    }
}
