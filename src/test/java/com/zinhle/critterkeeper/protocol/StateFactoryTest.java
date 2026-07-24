package com.zinhle.critterkeeper.protocol;

import com.zinhle.critterkeeper.creatures.PuppyCreature;
import com.zinhle.critterkeeper.world.Direction;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StateFactoryTest {

    @Test
    void reflectsKeeperWithNoCreatureAsSpeciesNone() {
        Keeper keeper = new Keeper("Zinhle", new Position(0, 0), Direction.NORTH);
        State state = StateFactory.from(keeper);
        assertEquals("none", state.getSpecies());
        assertEquals(0, state.getHunger());
    }

    @Test
    void reflectsKeeperWithACreature() {
        Keeper keeper = new Keeper("Zinhle", new Position(2, 3), Direction.WEST);
        keeper.adopt(new PuppyCreature("Rex"));

        State state = StateFactory.from(keeper);

        assertEquals(2, state.getX());
        assertEquals(3, state.getY());
        assertEquals("WEST", state.getFacing());
        assertEquals("Puppy", state.getSpecies());
        assertEquals(keeper.getCreature().getHunger(), state.getHunger());
        assertEquals(keeper.getCreature().getHappiness(), state.getHappiness());
        assertEquals(keeper.getCreature().getEnergy(), state.getEnergy());
    }
}
