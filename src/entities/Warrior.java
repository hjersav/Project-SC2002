package entities;

import turn_based_game.ShieldBash;

public class Warrior extends Player {
    public Warrior() {
        super(260, 40, 20, 30);
        this.specialSkill = new ShieldBash();
    }

    @Override
    public String getSpecialSkillName() {
        return "Shield Bash";
    }
}
