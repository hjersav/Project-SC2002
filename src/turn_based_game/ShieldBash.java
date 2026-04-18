package turn_based_game;

import entities.Combatant;
import entities.Player;
import statuseffects.Stun;
import statuseffects.DefendEffect;
import statuseffects.SmokeBombEffect;
import statuseffects.StatusEffect;

public class ShieldBash extends SpecialSkill {
    private Combatant target;

    public ShieldBash() {
        super();
        this.name = "Shield Bash";
    }

    public void setTarget(Combatant target) {
        this.target = target;
    }

    @Override
    protected void performSkill(Player player, Combatant target) {
        Combatant actualTarget = (this.target != null) ? this.target : target;
        int defBonus = 0;
        for (StatusEffect e : actualTarget.getStatusEffects()) {
            if (e instanceof DefendEffect) {
                defBonus += ((DefendEffect) e).getDefenseBonus();
            }
        }

        int baseDmg = Math.max(0, player.getAttack() - (actualTarget.getDefense() + defBonus));

        boolean smoke = false;
        for (StatusEffect e : actualTarget.getStatusEffects()) {
            if (e instanceof SmokeBombEffect) {
                smoke = ((SmokeBombEffect) e).isActive();
                break;
            }
        }

        int damage = smoke ? 0 : baseDmg;
        actualTarget.takeDamage(damage);

        if (!smoke) {
            actualTarget.addStatusEffect(new Stun());
            System.out.println(player.getName() + " uses Shield Bash on " + actualTarget.getName() + " for " + damage + " damage!");
            System.out.println(actualTarget.getName() + " is stunned for 2 turns!");
        } else {
            System.out.println(player.getName() + " uses Shield Bash on " + actualTarget.getName() + " but Smoke Bomb blocked it!");
        }
    }
}
