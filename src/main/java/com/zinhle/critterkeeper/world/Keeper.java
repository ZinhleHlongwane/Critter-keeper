package com.zinhle.critterkeeper.world;

import com.zinhle.critterkeeper.creatures.Creature;

/**
 * A Keeper is the player's avatar in the shelter grid. A keeper can adopt a
 * single Creature at a time and walks it around the exercise yard.
 */
public class Keeper {

    private final String name;
    private Position position;
    private Direction facing;
    private Creature creature;

    public Keeper(String name, Position position, Direction facing) {
        this.name = name;
        this.position = position;
        this.facing = facing;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getFacing() {
        return facing;
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }

    public boolean hasCreature() {
        return creature != null;
    }

    public Creature getCreature() {
        return creature;
    }

    public void adopt(Creature creature) {
        this.creature = creature;
    }

    public void releaseCreature() {
        this.creature = null;
    }
}
