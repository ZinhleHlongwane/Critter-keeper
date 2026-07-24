package com.zinhle.critterkeeper.creatures;

public class PuppyCreature extends Creature {

    public PuppyCreature(String name) {
        super(name);
    }

    @Override
    public String getSpecies() {
        return "Puppy";
    }

    @Override
    public String getSpecialAbility() {
        return "Boundless energy - covers two grid squares for the same tiredness as one";
    }

    @Override
    protected int feedAmount() {
        return 25;
    }

    @Override
    protected int playAmount() {
        return 30;
    }

    @Override
    protected int playEnergyCost() {
        return 15;
    }

    @Override
    protected int restAmount() {
        return 20;
    }

    @Override
    public int moveEnergyCost() {
        return 2;
    }
}
