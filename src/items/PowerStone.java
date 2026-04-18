package items;

import entities.Player;
import entities.Combatant;
import turn_based_game.SpecialSkill;
import java.util.List;

public class PowerStone extends Item{
  public PowerStone(){
    this.name = "PowerStone";
  }

  public void use(Player player, List<Combatant> aliveEnemies) {
    SpecialSkill skill = player.getSpecialSkill();
    if (skill != null) {
      // Save cooldown, execute skill (which would start cooldown), then restore
      int savedCooldown = skill.getCurrentCooldown();
      skill.execute(player, aliveEnemies);
      skill.setCurrentCooldown(savedCooldown);
      used = true;
      System.out.println("Power Stone used! Special skill activated without triggering cooldown!");
    }
  }
}
