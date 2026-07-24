package com.zinhle.critterkeeper.hazards;

import com.zinhle.critterkeeper.world.Position;

/** A thorn bush - a creature can push through it, but it costs happiness (except Dragonlings). */
public class ThornBush extends Hazard {

    public ThornBush(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "ThornBush";
    }

    @Override
    public boolean blocksMovement() {
        return false;
    }

    @Override
    public int happinessPenalty() {
        return 10;
    }
}
