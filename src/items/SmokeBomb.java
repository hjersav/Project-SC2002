package items;

import entities.Player;
import entities.Combatant;
import statuseffects.SmokeBombEffect;
import java.util.List;

public class SmokeBomb extends Item{
  public SmokeBomb(){
    this.name = "SmokeBomb";
  }

  public void use(Player player, List<Combatant> aliveEnemies){
    player.addStatusEffect(new SmokeBombEffect());
    used = true;
    System.out.println("Smoke Bomb used! Enemy attacks will deal 0 damage for 2 turns!");
  }
}
