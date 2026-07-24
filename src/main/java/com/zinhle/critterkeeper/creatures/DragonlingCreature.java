package com.zinhle.critterkeeper.creatures;

public class DragonlingCreature extends Creature {

    public DragonlingCreature(String name) {
        super(name);
    }

    @Override
    public String getSpecies() {
        return "Dragonling";
    }

    @Override
    public String getSpecialAbility() {
        return "Fire-breather - shrugs off thorn bushes without losing happiness";
    }

    @Override
    protected int feedAmount() {
        return 15;
    }

    @Override
    protected int playAmount() {
        return 10;
    }

    @Override
    protected int playEnergyCost() {
        return 20;
    }

    @Override
    protected int restAmount() {
        return 15;
    }

    @Override
    public int moveEnergyCost() {
        return 4;
    }
}
