package turn_based_game;

public class SpecialSkill extends Action {

    public SpecialSkill() {
        super("Special Skill");
    }

    @Override
    public void execute(Combatant actor, Combatant target) {
        
        if (actor instanceof Player) {
            Player player = (Player) actor;
            
            // Cooldown 3 turns, including current round 
            // Track state for each combatant, decreasing cooldown only if a turn by the combatant took place
            if (player.getSkillCooldown() == 0) {
                
                // Warrior and Wizard will override with their specific logic.
                player.performSpecialSkill(target);
                
                // Set cooldown to 3
                player.setSkillCooldown(3);
            } else {
                System.out.println("Special skill is on cooldown for " + player.getSkillCooldown() + " more turns.");
            }
        } else {
            System.out.println("Enemies cannot use special skills.");
        }
    }
}
