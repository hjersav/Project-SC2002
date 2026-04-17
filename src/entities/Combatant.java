public abstract class Combatant {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private int speed;
    private int specialSkillCooldown;

    public Combatant(String name, int health, int attack, int defense, int speed) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.specialSkillCooldown = 0;
    }

    // Abstract method for attack
    public abstract void attack(Combatant target);

    // Defend increases defense for one round
    public void defend() {
        this.defense += 10; // Increase defense temporarily
    }

    // Special skills cooldown management
    public void updateCooldown() {
        if (specialSkillCooldown > 0) specialSkillCooldown--;
    }

    // Basic Attack formula
    public void basicAttack(Combatant target) {
        int damage = Math.max(0, this.attack - target.defense);
        target.takeDamage(damage);
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public void resetDefense() {
        this.defense = 10;  // Reset defense to normal after defend action
    }

    public abstract void executeSpecialSkill(Combatant target);

    // Getters and setters omitted for brevity
}


}
