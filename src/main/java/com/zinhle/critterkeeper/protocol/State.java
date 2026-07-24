package com.zinhle.critterkeeper.protocol;

/**
 * A snapshot of a keeper and its creature, serialised back to the client
 * after most commands.
 */
public class State {

    private final int x;
    private final int y;
    private final String facing;
    private final String species;
    private final int hunger;
    private final int happiness;
    private final int energy;

    public State(int x, int y, String facing, String species, int hunger, int happiness, int energy) {
        this.x = x;
        this.y = y;
        this.facing = facing;
        this.species = species;
        this.hunger = hunger;
        this.happiness = happiness;
        this.energy = energy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getFacing() {
        return facing;
    }

    public String getSpecies() {
        return species;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getEnergy() {
        return energy;
    }
}
