package entities;

import java.util.List;
import statuseffects.StatusEffect;

public interface Combatant {
    String getName();
    int getHp();
    int getMaxHp();
    int getAttack();
    int getDefense();
    int getSpeed();
    void takeDamage(int damage);
    boolean isDefeated();
    void addStatusEffect(StatusEffect effect);
    void removeStatusEffect(StatusEffect effect);
    List<StatusEffect> getStatusEffects();
    void applyStatusEffects();
}
