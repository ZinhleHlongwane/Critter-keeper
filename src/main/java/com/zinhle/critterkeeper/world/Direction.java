package com.zinhle.critterkeeper.world;

/**
 * The four directions a Keeper can face inside the shelter grid.
 */
public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    /** Turning right (clockwise) from the current direction. */
    public Direction right() {
        return values()[(this.ordinal() + 1) % values().length];
    }

    /** Turning left (counter-clockwise) from the current direction. */
    public Direction left() {
        return values()[(this.ordinal() + values().length - 1) % values().length];
    }

    /** How much the x-coordinate changes when taking one step in this direction. */
    public int deltaX() {
        return switch (this) {
            case EAST -> 1;
            case WEST -> -1;
            default -> 0;
        };
    }

    /** How much the y-coordinate changes when taking one step in this direction. */
    public int deltaY() {
        return switch (this) {
            case NORTH -> 1;
            case SOUTH -> -1;
            default -> 0;
        };
    }
}
