package engine;

import java.util.ArrayList;
import java.util.List;
import entities.Combatant;
import entities.Player;
import entities.Enemy;
import turn_based_game.Action;
import turn_based_game.SpecialSkill;
import turn_based_game.BasicAttack;
import ui.GameCLI;
import levels.Level;
import statuseffects.Stun;
import statuseffects.StatusEffect;

public class BattleEngine {
    private Player player;
    private List<Enemy> enemiesList;
    private List<Enemy> initialEnemies;
    private Level level;
    private TurnOrderStrategy orderStrat;
    private GameCLI cli;
    private int rnd;
    private boolean ended;

    public BattleEngine(Player player, Level level, GameCLI gameCLI) {
        this.player = player;
        this.level = level;
        this.cli = gameCLI;
        this.enemiesList = new ArrayList<>(level.getInitialEnemies());
        this.initialEnemies = new ArrayList<>(this.enemiesList);
        this.orderStrat = new TurnOrderStrategy();
        this.rnd = 0;
        this.ended = false;
    }

    public void startBattle() {
        while (!ended) {
            rnd++;
            executeRound();
        }
    }

    private void executeRound() {
        List<Combatant> enemyList = new ArrayList<>(enemiesList);
        cli.displayBattleState(player, enemyList, rnd);

        List<Combatant> allCombatants = new ArrayList<>();
        allCombatants.add(player);
        allCombatants.addAll(enemiesList);

        List<Combatant> turnOrder = orderStrat.determineTurnOrder(allCombatants);
        cli.displayTurnOrder(turnOrder);

        for (Combatant combatant : turnOrder) {
            if (ended) {
                break;
            }

            if (combatant.isDefeated()) {
                continue;
            }

            boolean isStunned = false;
            for (StatusEffect effect : combatant.getStatusEffects()) {
                if (effect instanceof Stun) {
                    isStunned = true;
                    break;
                }
            }

            if (isStunned) {
                System.out.println(combatant.getName() + " is stunned and skips turn!");
                for (int i = combatant.getStatusEffects().size() - 1; i >= 0; i--) {
                    StatusEffect effect = combatant.getStatusEffects().get(i);
                    if (effect instanceof Stun) {
                        effect.tick();
                        if (effect.isExpired()) {
                            combatant.getStatusEffects().remove(i);
                        }
                        break;
                    }
                }
                continue;
            }

            combatant.applyStatusEffects();

            if (combatant instanceof Player) {
                // Inline player turn logic (was executePlayerTurn)
                Player pl = (Player) combatant;
                List<Combatant> alive = new ArrayList<>();
                for (Enemy e : enemiesList) {
                    if (!e.isDefeated()) {
                        alive.add(e);
                    }
                }
                if (!alive.isEmpty()) {
                    Action action = cli.selectPlayerAction(pl, alive);
                    action.execute(pl, alive);
                }

                SpecialSkill skill = ((Player) combatant).getSpecialSkill();
                if (skill != null) {
                    skill.decrementCooldown();
                }

            } else if (combatant instanceof Enemy) {
                // Inline enemy turn logic (direct basic attack)
                Enemy en = (Enemy) combatant;
                List<Combatant> targets = new ArrayList<>();
                targets.add(player);
                if (!en.isDefeated() && !targets.isEmpty()) {
                    BasicAttack attack = new BasicAttack();
                    attack.execute(en, targets.get(0));
                }
            }

            checkBattleEnd();
        }

        checkBackupSpawn();
    }

    

    private void checkBattleEnd() {
        if (player.isDefeated()) {
            ended = true;
            List<Combatant> enemyList = new ArrayList<>(enemiesList);
            cli.displayDefeatScreen(enemyList, rnd);
            return;
        }

        boolean allEnemiesDefeated = true;
        for (Enemy enemy : enemiesList) {
            if (!enemy.isDefeated()) {
                allEnemiesDefeated = false;
                break;
            }
        }

        boolean hasBackup = !level.getBackupEnemies().isEmpty();
        if (allEnemiesDefeated && (!hasBackup || level.isBackupSpawned())) {
            ended = true;
            cli.displayVictoryScreen(player, rnd);
        }
    }

    private void checkBackupSpawn() {
        if (!level.isBackupSpawned()) {
            if (!level.getBackupEnemies().isEmpty()) {
                boolean allInitDead = true;
                for (Enemy en : initialEnemies) {
                    if (!en.isDefeated()) {
                        allInitDead = false;
                        break;
                    }
                }

                if (allInitDead) {
                    level.spawnBackup();
                    cli.displayBackupSpawn();
                    enemiesList.addAll(level.getBackupEnemies());
                }
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemiesList;
    }

    public int getRoundNumber() {
        return rnd;
    }

    public boolean isBattleEnded() {
        return ended;
    }
}
