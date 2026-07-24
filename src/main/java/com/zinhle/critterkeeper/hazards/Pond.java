package com.zinhle.critterkeeper.hazards;

import com.zinhle.critterkeeper.world.Position;

/** A pond - creatures cannot walk into it at all (unless they're a Rabbit hopping over it). */
public class Pond extends Hazard {

    public Pond(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "Pond";
    }

    @Override
    public boolean blocksMovement() {
        return true;
    }

    @Override
    public int happinessPenalty() {
        return 0;
    }
}
