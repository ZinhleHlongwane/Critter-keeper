package com.zinhle.critterkeeper.creatures;

public class KittenCreature extends Creature {

    public KittenCreature(String name) {
        super(name);
    }

    @Override
    public String getSpecies() {
        return "Kitten";
    }

    @Override
    public String getSpecialAbility() {
        return "Low maintenance - barely gets hungry and naps itself back to full energy";
    }

    @Override
    protected int feedAmount() {
        return 20;
    }

    @Override
    protected int playAmount() {
        return 15;
    }

    @Override
    protected int playEnergyCost() {
        return 8;
    }

    @Override
    protected int restAmount() {
        return 35;
    }

    @Override
    public int moveEnergyCost() {
        return 1;
    }
}
