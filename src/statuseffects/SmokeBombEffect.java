package statuseffects;
import entities.Player;
public class SmokeBombEffect extends StatusEffect{
  public SmokeBombEffect(int duration){
    this.name="Smoke Bomb";
    this.duration=duration;
  }

  @Override
  public void apply(Object target){
    System.out.println("Smoke Bomb active! Enemy attacks deal 0 damage.");
  }

  @Override
  public String getDescriptioin(){
    return "Smoke Bomb: enemy attacks deal 0 damage for " + duration + " turn(s).";
  }
}
    
