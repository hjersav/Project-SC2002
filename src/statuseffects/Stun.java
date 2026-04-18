package statuseffects;

import entities.Combatant;

public class Stun extends StatusEffect{
  public Stun(){
    super(2);
    this.name = "Stun";
  }

  @Override
  public void apply(Combatant combatant){
  }

  @Override
  public String getDescription(){
    return "Stunned: cannot take actions for " + duration + " turn(s).";
  }
}
