package entities;

import turn_based_game.ArcaneBlast;

public class Wizard extends Player {
    private int attackBonus;

    public Wizard() {
        super(200, 50, 10, 20);
        this.specialSkill = new ArcaneBlast();
        this.attackBonus = 0;
    }

    @Override
    public int getAttack() {
        return atk + attackBonus;
    }

    public void addAttackBonus(int bonus) {
        attackBonus += bonus;
    }

    public void resetAttackBonus() {
        attackBonus = 0;
    }

    @Override
    public String getSpecialSkillName() {
        return "Arcane Blast";
    }
}
