package statuseffects;
public class Stun extends StatusEffect{
  public Stun(int duration){
    this.name ="Stun";
    this.duration=duration;
  }

  @Override
  public void apply(Object target){
    System.out.println(target + " is stunned and canot act!");
  }

  @Override
  public String getDescription(){
    return "Stunned: cannot take actions for " + duration + " turn(s).";
  }
}
