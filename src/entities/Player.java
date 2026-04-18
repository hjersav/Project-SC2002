package entities;

import java.util.ArrayList;
import java.util.List;
import statuseffects.StatusEffect;
import items.Item;
import turn_based_game.SpecialSkill;

public abstract class Player implements Combatant {
    protected int hp;
    protected int maxHpVal;
    protected int atk;
    protected int defn;
    protected int spd;
    protected List<StatusEffect> effects;
    protected List<Item> bag;
    protected SpecialSkill specialSkill;

    public Player(int maxHp, int attack, int defense, int speed) {
        this.maxHpVal = maxHp;
        this.hp = maxHp;
        this.atk = attack;
        this.defn = defense;
        this.spd = speed;
        this.effects = new ArrayList<>();
        this.bag = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getMaxHp() {
        return maxHpVal;
    }

    @Override
    public int getAttack() {
        return atk;
    }

    @Override
    public int getDefense() {
        return defn;
    }

    @Override
    public int getSpeed() {
        return spd;
    }

    @Override
    public void takeDamage(int damage) {
        hp = Math.max(0, hp - damage);
    }

    @Override
    public boolean isDefeated() {
        return hp <= 0;
    }

    @Override
    public void addStatusEffect(StatusEffect effect) {
        effects.add(effect);
    }

    @Override
    public void removeStatusEffect(StatusEffect effect) {
        effects.remove(effect);
    }

    @Override
    public List<StatusEffect> getStatusEffects() {
        return effects;
    }

    @Override
    public void applyStatusEffects() {
        for (int i = effects.size() - 1; i >= 0; i--) {
            StatusEffect effect = effects.get(i);
            effect.apply(this);
            if (effect.isExpired()) {
                effects.remove(i);
            }
        }
    }

    public List<Item> getInventory() {
        return bag;
    }

    public void setInventory(List<Item> inventory) {
        this.bag = inventory;
    }

    public SpecialSkill getSpecialSkill() {
        return specialSkill;
    }

    public void setSpecialSkill(SpecialSkill specialSkill) {
        this.specialSkill = specialSkill;
    }
    public abstract String getSpecialSkillName();
}
