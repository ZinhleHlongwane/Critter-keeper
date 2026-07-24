package com.zinhle.critterkeeper.creatures;

/**
 * Base class for every kind of creature that can be adopted from the shelter.
 * Each subspecies tunes how quickly hunger/happiness/energy change and adds
 * its own special ability, mirroring how different robot kinds behaved
 * differently in the original project this one is modelled on.
 */
public abstract class Creature {

    public static final int MAX_STAT = 100;

    private final String name;
    private int hunger = 20;
    private int happiness = 80;
    private int energy = 100;

    protected Creature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getSpecies();

    /** A short description of what makes this species unique. Shown by the STATE command. */
    public abstract String getSpecialAbility();

    /** How many hunger points feeding removes. */
    protected abstract int feedAmount();

    /** How many happiness points a play session adds. */
    protected abstract int playAmount();

    /** How much energy a play session costs. */
    protected abstract int playEnergyCost();

    /** How many energy points resting restores per turn. */
    protected abstract int restAmount();

    /** How much energy moving one step costs this species. */
    public abstract int moveEnergyCost();

    public int getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isAsleep() {
        return energy <= 0;
    }

    public void feed() {
        hunger = clamp(hunger - feedAmount());
    }

    public void play() {
        happiness = clamp(happiness + playAmount());
        energy = clamp(energy - playEnergyCost());
    }

    public void rest() {
        energy = clamp(energy + restAmount());
    }

    /** Called whenever the keeper carrying this creature takes a step. */
    public void spendEnergyForMove() {
        energy = clamp(energy - moveEnergyCost());
        hunger = clamp(hunger + 1);
    }

    protected static int clamp(int value) {
        return Math.max(0, Math.min(MAX_STAT, value));
    }

    @Override
    public String toString() {
        return String.format("%s the %s [hunger=%d, happiness=%d, energy=%d]",
                name, getSpecies(), hunger, happiness, energy);
    }
}
