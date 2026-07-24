package com.zinhle.critterkeeper.protocol;

import com.zinhle.critterkeeper.creatures.Creature;
import com.zinhle.critterkeeper.world.Keeper;

/**
 * Builds the State DTO sent back to clients from a live Keeper object.
 */
public final class StateFactory {

    private StateFactory() {
        // no instances
    }

    public static State from(Keeper keeper) {
        Creature creature = keeper.getCreature();
        if (creature == null) {
            return new State(keeper.getPosition().getX(), keeper.getPosition().getY(),
                    keeper.getFacing().name(), "none", 0, 0, 0);
        }
        return new State(keeper.getPosition().getX(), keeper.getPosition().getY(),
                keeper.getFacing().name(), creature.getSpecies(),
                creature.getHunger(), creature.getHappiness(), creature.getEnergy());
    }
}
