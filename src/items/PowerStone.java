package items;
import entities.Player;
public class PowerStone extends Item{
  public PowerStone(){
    this.name = "PowerStone";
  }

  @Override
  public void use(Object user) {
    if (user instanceof Player) {
      Player p =(Player) user;
      p.triggerSpecialSkillOnce();
      used = true;
      System.out.println(p.getName() + " used Power Stone! Special skill triggered without affecting cooldown.");
    }
  }
}
