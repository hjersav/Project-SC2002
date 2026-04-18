package items;

import entities.Player;
import entities.Combatant;
import java.util.List;

public class Potion extends Item {
  public Potion() {
    this.name = "Potion";
  }
  public void use(Player player, List<Combatant> aliveEnemies){
    int healAmt = 100;
    int newHp = Math.min(player.getMaxHp(), player.getHp() + healAmt);
    int actual = newHp - player.getHp();
    player.takeDamage(-actual);
    used = true;
    System.out.println("Healed for " + actual + " HP!");
  }
}
