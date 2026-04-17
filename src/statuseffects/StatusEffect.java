package statuseffects;
public abstract class StatusEffect{
  protected String name;
  protected int duration;

  public String getName() {return name;}
  public int getDuration() {return duration;}

  public void tick(){
    if (duration>0) duration--;
  }
  public boolean isExpired(){
    return duration <=0;
  }

  public abstract void apply(Object target);
  public abstract String getDescription();
}
