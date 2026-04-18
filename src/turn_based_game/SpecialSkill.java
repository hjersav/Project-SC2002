package turn_based_game;

import entities.Combatant;
import entities.Player;

public class SpecialSkill extends Action {

    protected int cooldown;
    protected int currentCooldown;

    public SpecialSkill() {
        super("Special Skill");
        this.cooldown = 3;
        this.currentCooldown = 0;
    }

    @Override
    public void execute(Combatant actor, Combatant target) {

        if (actor instanceof Player) {
            Player player = (Player) actor;

            // Cooldown 3 turns, including current round
            // Track state for each combatant, decreasing cooldown only if a turn by the combatant took place
            if (currentCooldown == 0) {

                // Warrior and Wizard will override with their specific logic.
                performSkill(player, target);

                // Set cooldown to 3
                startCooldown();
            } else {
                System.out.println("Special skill is on cooldown for " + currentCooldown + " more turns.");
            }
        } else {
            System.out.println("Enemies cannot use special skills.");
        }
    }

    protected void performSkill(Player player, Combatant target) {
        // Subclasses (ShieldBash, ArcaneBlast) override this
    }

    public void startCooldown() {
        currentCooldown = cooldown;
    }

    public void decrementCooldown() {
        if (currentCooldown > 0) {
            currentCooldown--;
        }
    }

    public boolean isOnCooldown() {
        return currentCooldown > 0;
    }

    public int getCurrentCooldown() {
        return currentCooldown;
    }

    public void setCurrentCooldown(int cooldown) {
        this.currentCooldown = cooldown;
    }
}
