package levels;

import java.util.ArrayList;
import java.util.List;
import entities.Enemy;
import entities.Goblin;
import entities.Wolf;


public class Level {
    private int levelNumber;
    private List<String> initialEnemyTypes;
    private List<String> backupEnemyTypes;
    private boolean backupSpawned;
    

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        this.initialEnemyTypes = new ArrayList<>();
        this.backupEnemyTypes = new ArrayList<>();
        this.backupSpawned = false;
        // enemies just do basic attack by default
        initializeLevel();
    }

    private void initializeLevel() {
        switch (levelNumber) {
            case 1:
                initialEnemyTypes.add("Goblin");
                initialEnemyTypes.add("Goblin");
                initialEnemyTypes.add("Goblin");
                break;
            case 2:
                initialEnemyTypes.add("Goblin");
                initialEnemyTypes.add("Wolf");
                backupEnemyTypes.add("Wolf");
                backupEnemyTypes.add("Wolf");
                break;
            case 3:
                initialEnemyTypes.add("Goblin");
                initialEnemyTypes.add("Goblin");
                backupEnemyTypes.add("Goblin");
                backupEnemyTypes.add("Wolf");
                backupEnemyTypes.add("Wolf");
                break;
            default:
                break;
        }
    }

    private String generateName(String type, int index) {
        char letter = (char) ('A' + index);
        return type + " " + letter;
    }

    private int getTypeCount(List<String> list, String type, int upToIndex) {
        int count = 0;
        for (int i = 0; i < upToIndex; i++) {
            if (list.get(i).equals(type)) count++;
        }
        return count;
    }

    public List<Enemy> getInitialEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < initialEnemyTypes.size(); i++) {
            String type = initialEnemyTypes.get(i);
            String name = generateName(type, getTypeCount(initialEnemyTypes, type, i));
            if (type.equals("Goblin")) {
                enemies.add(new Goblin(name));
            } else if (type.equals("Wolf")) {
                enemies.add(new Wolf(name));
            }
        }
        return enemies;
    }

    public List<Enemy> getBackupEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < backupEnemyTypes.size(); i++) {
            String type = backupEnemyTypes.get(i);
            int initialCount = getTypeCount(initialEnemyTypes, type, initialEnemyTypes.size());
            int backupCount = getTypeCount(backupEnemyTypes, type, i);
            String name = generateName(type, initialCount + backupCount);
            if (type.equals("Goblin")) {
                enemies.add(new Goblin(name));
            } else if (type.equals("Wolf")) {
                enemies.add(new Wolf(name));
            }
        }
        return enemies;
    }

    public boolean isBackupSpawned() {
        return backupSpawned;
    }

    public void spawnBackup() {
        this.backupSpawned = true;
    }

    

    public int getLevelNumber() {
        return levelNumber;
    }
}
