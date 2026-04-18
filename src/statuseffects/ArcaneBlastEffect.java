package statuseffects;

import entities.Combatant;

public class ArcaneBlastEffect extends StatusEffect{
  private static final int ATTACK_BONUS_PER_KILL=10;
  public ArcaneBlastEffect(){
    super(Integer.MAX_VALUE);
    this.name = "Arcane Blast Buff";
  }

  @Override
  public void apply(Combatant combatant){
  }

  public int getAttackBonus() {
    return ATTACK_BONUS_PER_KILL;
  }

  @Override
  public boolean isExpired(){
    return false;
  }

  @Override
  public String getDescription(){
    return "Arcane Blast: +" + ATTACK_BONUS_PER_KILL + "ATK per kill, lasts until end of level.";
  }
}
