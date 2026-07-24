package com.zinhle.critterkeeper.creatures;

public class RabbitCreature extends Creature {

    public RabbitCreature(String name) {
        super(name);
    }

    @Override
    public String getSpecies() {
        return "Rabbit";
    }

    @Override
    public String getSpecialAbility() {
        return "Quick hopper - can slip past a puddle without slowing down";
    }

    @Override
    protected int feedAmount() {
        return 30;
    }

    @Override
    protected int playAmount() {
        return 25;
    }

    @Override
    protected int playEnergyCost() {
        return 18;
    }

    @Override
    protected int restAmount() {
        return 18;
    }

    @Override
    public int moveEnergyCost() {
        return 3;
    }
}
