import entities.Player;
import entities.Warrior;
import entities.Wizard;
import engine.BattleEngine;
import levels.Level;
import ui.GameCLI;
import items.Item;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameCLI gameCLI = new GameCLI();

        while (true) {
            int choice = gameCLI.displayMainMenu();

            if (choice == 2) {
                System.out.println("Goodbye!");
                gameCLI.close();
                break;
            }

            String pClass = gameCLI.selectPlayerClass();
            int difficulty = gameCLI.selectDifficulty();
            Item[] chosenItems = gameCLI.selectItems();

            Player player = createPlayer(pClass);
            List<Item> inv = new ArrayList<>();
            for (Item it : chosenItems) {
                inv.add(it);
            }
            player.setInventory(inv);

            Level level = new Level(difficulty);
            BattleEngine engine = new BattleEngine(player, level, gameCLI);
            engine.startBattle();

            int endChoice = gameCLI.displayEndGameOptions();
            while (endChoice == 1) {
                player = createFreshPlayer(pClass, chosenItems);
                level = new Level(difficulty);
                BattleEngine replay = new BattleEngine(player, level, gameCLI);
                replay.startBattle();
                endChoice = gameCLI.displayEndGameOptions();
            }
            if (endChoice == 3) {
                System.out.println("Goodbye!");
                gameCLI.close();
                break;
            }
        }
    }

    private static Player createPlayer(String playerClass) {
        if (playerClass.equals("Warrior")) {
            return new Warrior();
        }
        return new Wizard();
    }

    private static Player createFreshPlayer(String playerClass, Item[] originalItems) {
        Player player = createPlayer(playerClass);
        List<Item> bag = new ArrayList<>();
        for (Item it : originalItems) {
            bag.add(it);
        }
        player.setInventory(bag);
        return player;
    }
}
