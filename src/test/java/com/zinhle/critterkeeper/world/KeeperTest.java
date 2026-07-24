package com.zinhle.critterkeeper.world;

import com.zinhle.critterkeeper.creatures.PuppyCreature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeeperTest {

    @Test
    void newKeeperHasNoCreature() {
        Keeper keeper = new Keeper("Zinhle", new Position(0, 0), Direction.NORTH);
        assertFalse(keeper.hasCreature());
        assertNull(keeper.getCreature());
    }

    @Test
    void adoptingGivesTheKeeperACreature() {
        Keeper keeper = new Keeper("Zinhle", new Position(0, 0), Direction.NORTH);
        keeper.adopt(new PuppyCreature("Rex"));
        assertTrue(keeper.hasCreature());
        assertEquals("Rex", keeper.getCreature().getName());
    }

    @Test
    void releaseCreatureClearsIt() {
        Keeper keeper = new Keeper("Zinhle", new Position(0, 0), Direction.NORTH);
        keeper.adopt(new PuppyCreature("Rex"));
        keeper.releaseCreature();
        assertFalse(keeper.hasCreature());
    }

    @Test
    void positionAndFacingCanBeUpdated() {
        Keeper keeper = new Keeper("Zinhle", new Position(0, 0), Direction.NORTH);
        keeper.setPosition(new Position(3, 4));
        keeper.setFacing(Direction.SOUTH);
        assertEquals(new Position(3, 4), keeper.getPosition());
        assertEquals(Direction.SOUTH, keeper.getFacing());
    }

    @Test
    void nameIsImmutable() {
        Keeper keeper = new Keeper("Zinhle", new Position(0, 0), Direction.EAST);
        assertEquals("Zinhle", keeper.getName());
    }
}
