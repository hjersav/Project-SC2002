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
    }
  }
}
