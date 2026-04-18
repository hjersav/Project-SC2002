package turn_based_game;

import entities.Combatant;
import entities.Player;
import entities.Wizard;
import statuseffects.DefendEffect;
import statuseffects.SmokeBombEffect;
import statuseffects.StatusEffect;
import java.util.List;

public class ArcaneBlast extends SpecialSkill {

    public ArcaneBlast() {
        super();
        this.name = "Arcane Blast";
    }

    // AOE: overrides list version to hit all enemies
    @Override
    public void execute(Combatant actor, List<Combatant> targets) {
        if (actor instanceof Player) {
            Player player = (Player) actor;
            if (currentCooldown == 0) {
                performAOE(player, targets);
                startCooldown();
            } else {
                System.out.println("Special skill is on cooldown for " + currentCooldown + " more turns.");
            }
        }
    }

    private void performAOE(Player player, List<Combatant> targets) {
        System.out.println(player.getName() + " uses Arcane Blast on all enemies!");

        int k = 0;
        for (Combatant target : targets) {
            if (!target.isDefeated()) {
                int defB = 0;
                boolean smoke = false;
                for (StatusEffect e : target.getStatusEffects()) {
                    if (e instanceof DefendEffect) {
                        defB += ((DefendEffect) e).getDefenseBonus();
                    }
                    if (e instanceof SmokeBombEffect) {
                        smoke = ((SmokeBombEffect) e).isActive();
                    }
                }

                int baseDmg = Math.max(0, player.getAttack() - (target.getDefense() + defB));
                int damage = smoke ? 0 : baseDmg;
                target.takeDamage(damage);
                System.out.println(target.getName() + " takes " + damage + " damage!");

                if (target.isDefeated() && !smoke) {
                    k++;
                    System.out.println(target.getName() + " is eliminated!");
                }
            }
        }

        if (player instanceof Wizard && k > 0) {
            Wizard wiz = (Wizard) player;
            int bonus = k * 10;
            wiz.addAttackBonus(bonus);
            System.out.println("Wizard gains +" + bonus + " Attack (total: " + wiz.getAttack() + ")");
        }
    }
}
