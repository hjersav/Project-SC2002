package statuseffects;
import entities.Player;
public class DefendEffect extends StatusEffect{
  public DefendEffect(int duration){
    this.name="Defend";
    this.duration=duration;
  }

 @Override
  public void apply(Object target){
    if (target instanceof Player){
      Player p= (Player) target;
      p.addDefenseBonus(DEFENSE_BONUS);
      System.out.println(p.getName() + " is defending! +" + DEFENSE_BONUS + " defense for " + duration + " turn(s).");
    }
  }

  @Override
  public String getDescription(){
    return "Defending: +" + DEFENSE_BONUS + " defense for " + duration + " turn(s).";
  }
}
    
