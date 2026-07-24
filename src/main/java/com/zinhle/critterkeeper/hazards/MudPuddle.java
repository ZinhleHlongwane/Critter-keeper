package com.zinhle.critterkeeper.hazards;

import com.zinhle.critterkeeper.world.Position;

/** A mud puddle - slows a creature's mood a little, unless it's a nimble Rabbit. */
public class MudPuddle extends Hazard {

    public MudPuddle(Position position) {
        super(position);
    }

    @Override
    public String getName() {
        return "MudPuddle";
    }

    @Override
    public boolean blocksMovement() {
        return false;
    }

    @Override
    public int happinessPenalty() {
        return 5;
    }
}
