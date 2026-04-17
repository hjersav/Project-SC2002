public class Player extends Combatant {
    private int experiencePoints;
    private int level;
    private Inventory inventory;

    public Player(String name, int health, int attack, int defense, int speed) {
        super(name, health, attack, defense, speed);
        this.experiencePoints = 0;
        this.level = 1;
        this.inventory = new Inventory();
    }

    // Player can use items during the game
    public void useItem(Item item) {
        item.applyEffect(this);
        inventory.removeItem(item);
    }

    // Level up logic
    public void levelUp() {
        this.level++;
        this.experiencePoints = 0; // Reset experience after leveling up
        // Increase player's stats (e.g., attack, health) on level-up
        this.attack += 10;
        this.health += 50;
        System.out.println(this.getName() + " leveled up to level " + this.level + "!");
    }

    // Attack method that lets players choose actions
    @Override
    public void attack(Combatant target) {
        basicAttack(target);
    }

    @Override
    public void executeSpecialSkill(Combatant target) {
        // This is where player's special skills are executed
        // For example, use Shield Bash or Arcane Blast depending on player type
    }

    // Add experience points
    public void gainExperience(int points) {
        this.experiencePoints += points;
        System.out.println(this.getName() + " gained " + points + " experience points!");
        if (this.experiencePoints >= 100) {
            levelUp();
        }
    }

    // Inventory class to handle items
    private class Inventory {
        private List<Item> items;

        public Inventory() {
            this.items = new ArrayList<>();
        }

        public void addItem(Item item) {
            items.add(item);
        }

        public void removeItem(Item item) {
            items.remove(item);
        }

        public List<Item> getItems() {
            return items;
        }
    }
}
