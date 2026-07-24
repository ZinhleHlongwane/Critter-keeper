package com.zinhle.critterkeeper.hazards;

import com.zinhle.critterkeeper.world.Position;

/**
 * Base class for anything placed in the shelter's exercise yard that a
 * keeper's creature has to deal with while moving around.
 */
public abstract class Hazard {

    private final Position position;

    protected Hazard(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public abstract String getName();

    /** Whether a creature can occupy the same square as this hazard at all. */
    public abstract boolean blocksMovement();

    /** How much happiness a creature loses for encountering this hazard, if not blocked. */
    public abstract int happinessPenalty();
}
