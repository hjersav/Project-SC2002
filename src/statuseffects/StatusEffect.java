package statuseffects;

import entities.Combatant;

public abstract class StatusEffect{
  protected String name;
  protected int duration;

  public StatusEffect(int duration) {
    this.duration = duration;
  }

  public String getName() {return name;}
  public int getDuration() {return duration;}

  public void tick(){
    if (duration>0) duration--;
  }
  public boolean isExpired(){
    return duration <=0;
  }

  public abstract void apply(Combatant combatant);
  public abstract String getDescription();
}
