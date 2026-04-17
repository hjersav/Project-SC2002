package statuseffects;
import entities.Wizard;
public class ArcaneBlastEffect extends StatusEffect{
  private static final int ATTACK_BONUS_PER_KILL=10;
  public ArcaneBlastEffect(){
    this.name = "Arcane Blast Buff";
    this.duration = Integer.MAX_VALUE;
  }

  @Override
  public void apply(Object target){
    if (target instanceof Wizard){
      Wizard w = (Wizard) target;
      w.addAttackBonus(ATTACK_BONUS_PER_KILL);
      System.out.println(w.getName() + " gains +" + ATTACK_BONUS_PER_KILL + " ATK from Arcane Blast kill! ATK is now " + w.getAttack());
    }
  }

  @Override
  public String getDescription(){
    return "Arcane Blast: +" + ATTACK_BONUS_PER_KILL + "ATK per kill, lasts until end of level.";
  }
}
    
