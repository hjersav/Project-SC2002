package items;
import entities.Player;
public class Potion extends Item {
  public Potion() {
    this.name = "Potion";
  }
  @Override
  public void use(Object user){
    if (user instanceof Player){
      Player p=(Player) user;
      int newHp=Math.min(p.getHP() + 100, p.getMaxHP());
      p.setHP(newHp);
      used=true;
      System.out.print(p.getName() + " used Potion! HP restored to " + newHp);
    }
  }
}
