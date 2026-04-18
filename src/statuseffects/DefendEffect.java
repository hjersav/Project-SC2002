package statuseffects;

import entities.Combatant;

public class DefendEffect extends StatusEffect{
  private static final int DEFENSE_BONUS = 10;
  public DefendEffect(){
    super(2);
    this.name="Defend";
  }

  @Override
  public void apply(Combatant combatant){
    tick();
  }

  public int getDefenseBonus() {
    return DEFENSE_BONUS;
  }

  @Override
  public String getDescription(){
    return "Defending: +" + DEFENSE_BONUS + " defense for " + duration + " turn(s).";
  }
}
