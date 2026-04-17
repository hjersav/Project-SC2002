package items;
import entities.Player;
public class SmokeBomb extends Item{
  public SmokeBomb(){
    this.name = "SmokeBomb";
  }

  @Override
  public void use(Object user){
    if (user instanceof Player){
      Player p = (Player) user;
      p.applyStatusEffect(new statuseffects.SmokeBombEffect(2));
      used = true;
      System.out.println(p.getName() + " used Smoke Bomb! Enemy attacks deal 0 damage for 2 turns.");
    }
  }
}
