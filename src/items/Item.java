package items;

import entities.Player;
import entities.Combatant;
import java.util.List;

public abstract class Item{
  protected String name;
  protected boolean used = false;
  public String getName() {return this.getClass().getSimpleName();}
  public boolean isUsed() {return used;}
  public abstract void use(Player player, List<Combatant> aliveEnemies);
}
