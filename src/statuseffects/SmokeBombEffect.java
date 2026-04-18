package statuseffects;

import entities.Combatant;

public class SmokeBombEffect extends StatusEffect{
  public SmokeBombEffect(){
    super(2);
    this.name="Smoke Bomb";
  }

  @Override
  public void apply(Combatant combatant){
    tick();
  }

  public boolean isActive() {
    return duration > 0;
  }

  @Override
  public String getDescription(){
    return "Smoke Bomb: enemy attacks deal 0 damage for " + duration + " turn(s).";
  }
}
