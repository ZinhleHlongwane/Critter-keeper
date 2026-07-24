package com.zinhle.critterkeeper.creatures;

public class TurtleCreature extends Creature {

    public TurtleCreature(String name) {
        super(name);
    }

    @Override
    public String getSpecies() {
        return "Turtle";
    }

    @Override
    public String getSpecialAbility() {
        return "Thick shell - never gets hurt by hazards, hardly ever needs feeding";
    }

    @Override
    protected int feedAmount() {
        return 10;
    }

    @Override
    protected int playAmount() {
        return 8;
    }

    @Override
    protected int playEnergyCost() {
        return 5;
    }

    @Override
    protected int restAmount() {
        return 10;
    }

    @Override
    public int moveEnergyCost() {
        return 1;
    }
}
