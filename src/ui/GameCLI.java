package ui;

import java.util.List;
import java.util.Scanner;
import entities.Combatant;
import entities.Player;
import turn_based_game.Action;
import turn_based_game.BasicAttack;
import turn_based_game.Defend;
import turn_based_game.UseItem;
import turn_based_game.SpecialSkill;
import turn_based_game.ShieldBash;
import items.Item;

public class GameCLI {
    private Scanner scanner;

    public GameCLI() {
        this.scanner = new Scanner(System.in);
    }

    public int displayMainMenu() {
        System.out.println("=== Turn-Based Combat Arena ===");
        System.out.println("1. Start New Game");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public String selectPlayerClass() {
        System.out.println("\nSelect your character class:");
        System.out.println("1. Warrior");
        System.out.println("   HP: 260, Attack: 40, Defense: 20, Speed: 30");
        System.out.println("   Special Skill: Shield Bash - Deal damage and stun enemy for 2 turns");
        System.out.println("2. Wizard");
        System.out.println("   HP: 200, Attack: 50, Defense: 10, Speed: 20");
        System.out.println("   Special Skill: Arcane Blast - Deal AOE damage, gain +10 ATK per kill");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = scanner.nextInt();
        if (choice == 1) return "Warrior";
        return "Wizard";
    }

    public int selectDifficulty() {
        System.out.println("\nSelect difficulty level:");
        System.out.println("1. Easy - 3 Goblins");
        System.out.println("2. Medium - 1 Goblin + 1 Wolf (Backup: 2 Wolves)");
        System.out.println("3. Hard - 2 Goblins (Backup: 1 Goblin + 2 Wolves)");
        System.out.print("Enter your choice (1-3): ");
        return scanner.nextInt();
    }

    public Item[] selectItems() {
        System.out.println("\nSelect 2 items (duplicates allowed):");
        System.out.println("1. Potion - Heal 100 HP");
        System.out.println("2. Power Stone - Free use of special skill");
        System.out.println("3. Smoke Bomb - Enemy attacks deal 0 damage for 2 turns");
        Item[] items = new Item[2];
        for (int i = 0; i < 2; i++) {
            System.out.print("Select item " + (i + 1) + " (1-3): ");
            int choice = scanner.nextInt();
            items[i] = createItem(choice);
        }
        return items;
    }

    private Item createItem(int choice) {
        switch (choice) {
            case 1: return new items.Potion();
            case 2: return new items.PowerStone();
            case 3: return new items.SmokeBomb();
            default: return new items.Potion();
        }
    }

    public void displayBattleState(Player player, List<Combatant> enemies, int roundNumber) {
        System.out.println("\n=== Round " + roundNumber + " ===");
        System.out.println("Player: " + player.getName());
        System.out.println("HP: " + player.getHp() + "/" + player.getMaxHp());
        System.out.println("ATK: " + player.getAttack() + " DEF: " + player.getDefense());
        System.out.println("SPD: " + player.getSpeed());
        SpecialSkill skill = player.getSpecialSkill();
        if (skill != null && skill.isOnCooldown()) {
            System.out.println("Skill CD: " + skill.getCurrentCooldown());
        }
        System.out.println("\nEnemies:");
        for (int i = 0; i < enemies.size(); i++) {
            Combatant enemy = enemies.get(i);
            System.out.println((i + 1) + ". " + enemy.getName() + " - HP: " + enemy.getHp() + "/" + enemy.getMaxHp());
            if (enemy.isDefeated()) {
                System.out.println("   [ELIM]");
            }
        }
    }

    public Action selectPlayerAction(Player player, List<Combatant> aliveEnemies) {
        while (true) {
            System.out.println("\nSelect an action:");
            System.out.println("1. Basic Attack");
            System.out.println("2. Defend");
            System.out.println("3. Use Item");
            SpecialSkill skill = player.getSpecialSkill();
            if (skill != null && !skill.isOnCooldown()) {
                System.out.println("4. Special Skill (" + player.getSpecialSkillName() + ")");
            } else {
                System.out.println("4. Special Skill (" + player.getSpecialSkillName() + ") - Cooldown: " + (skill != null ? skill.getCurrentCooldown() : 0));
            }
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    return handleBasicAttack(aliveEnemies);
                case 2:
                    return new Defend();
                case 3:
                    return handleItemAction(player, aliveEnemies);
                case 4:
                    if (skill != null && !skill.isOnCooldown()) {
                        return handleSpecialSkill(player, aliveEnemies);
                    } else {
                        System.out.println("Special skill is on cooldown! Choose another action.");
                        break;
                    }
                default:
                    System.out.println("Invalid choice! Try again.");
                    break;
            }
        }
    }

    private Action handleSpecialSkill(Player player, List<Combatant> aliveEnemies) {
        SpecialSkill skill = player.getSpecialSkill();

        if (skill instanceof ShieldBash) {
            System.out.println("\nSelect target:");
            for (int i = 0; i < aliveEnemies.size(); i++) {
                Combatant enemy = aliveEnemies.get(i);
                if (!enemy.isDefeated()) {
                    System.out.println((i + 1) + ". " + enemy.getName() + " - HP: " + enemy.getHp() + "/" + enemy.getMaxHp());
                }
            }
            System.out.print("Enter target number: ");
            int targetIndex = scanner.nextInt() - 1;
            ((ShieldBash) skill).setTarget(aliveEnemies.get(targetIndex));
        }
        return skill;
    }

    private Action handleBasicAttack(List<Combatant> aliveEnemies) {
        System.out.println("\nSelect target:");
        for (int i = 0; i < aliveEnemies.size(); i++) {
            Combatant enemy = aliveEnemies.get(i);
            if (!enemy.isDefeated()) {
                System.out.println((i + 1) + ". " + enemy.getName() + " - HP: " + enemy.getHp() + "/" + enemy.getMaxHp());
            }
        }
        System.out.print("Enter target number: ");
        int targetIndex = scanner.nextInt() - 1;
        BasicAttack attack = new BasicAttack();
        attack.setTarget(aliveEnemies.get(targetIndex));
        return attack;
    }

    private Action handleItemAction(Player player, List<Combatant> aliveEnemies) {
        List<Item> items = player.getInventory();
        if (items.isEmpty()) {
            System.out.println("No items available!");
            return handleBasicAttack(aliveEnemies);
        }
        System.out.println("\nSelect item to use:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName());
        }
        System.out.print("Enter item number: ");
        int itemIndex = scanner.nextInt() - 1;
        Item selectedItem = items.get(itemIndex);

        if (selectedItem instanceof items.PowerStone) {
            SpecialSkill skill2 = player.getSpecialSkill();
            if (skill2 instanceof ShieldBash) {
                System.out.println("\nSelect target for Shield Bash:");
                for (int i = 0; i < aliveEnemies.size(); i++) {
                    Combatant enemy = aliveEnemies.get(i);
                    if (!enemy.isDefeated()) {
                        System.out.println((i + 1) + ". " + enemy.getName() + " - HP: " + enemy.getHp() + "/" + enemy.getMaxHp());
                    }
                }
                System.out.print("Enter target number: ");
                int targetIndex = scanner.nextInt() - 1;
                ((ShieldBash) skill2).setTarget(aliveEnemies.get(targetIndex));
            }
        }

        UseItem useItem = new UseItem(selectedItem);
        useItem.setAliveEnemies(aliveEnemies);
        return useItem;
    }

    public void displayVictoryScreen(Player player, int totalRounds) {
        System.out.println("\n=== VICTORY ===");
        System.out.println("You beat them! Good job.");
        System.out.println("Remaining HP: " + player.getHp() + "/" + player.getMaxHp());
        System.out.println("Total Rounds: " + totalRounds);
    }

    public void displayDefeatScreen(List<Combatant> enemies, int totalRounds) {
        int remainingEnemies = 0;
        for (Combatant enemy : enemies) {
            if (!enemy.isDefeated()) {
                remainingEnemies++;
            }
        }
        System.out.println("\n=== DEFEATED ===");
        System.out.println("You were defeated. Try again.");
        System.out.println("Enemies remaining: " + remainingEnemies);
        System.out.println("Total Rounds Survived: " + totalRounds);
    }

    public int displayEndGameOptions() {
        System.out.println("\nOptions:");
        System.out.println("1. Replay with same settings");
        System.out.println("2. Start new game");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public void displayBackupSpawn() {
        System.out.println("\n=== BACKUP SPAWN TRIGGERED ===");
        System.out.println("Additional enemies have joined the battle!");
    }

    public void displayTurnOrder(List<Combatant> turnOrder) {
        System.out.println("\nTurn Order:");
        int displayIndex = 1;
        for (int i = 0; i < turnOrder.size(); i++) {
            Combatant combatant = turnOrder.get(i);
            if (!combatant.isDefeated()) {
                System.out.println(displayIndex + ". " + combatant.getName() +
                                 " (Speed: " + combatant.getSpeed() + ")");
                displayIndex++;
            }
        }
    }

    public void close() {
        scanner.close();
    }
}
