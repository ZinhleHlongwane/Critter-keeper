package com.zinhle.critterkeeper.hazards;

import org.junit.jupiter.api.Test;
import com.zinhle.critterkeeper.world.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HazardTest {

    @Test
    void pondBlocksMovementAndHasNoPenalty() {
        Pond pond = new Pond(new Position(1, 1));
        assertTrue(pond.blocksMovement());
        assertEquals(0, pond.happinessPenalty());
        assertEquals(new Position(1, 1), pond.getPosition());
    }

    @Test
    void thornBushIsPassableButCostsHappiness() {
        ThornBush thornBush = new ThornBush(new Position(2, 2));
        assertFalse(thornBush.blocksMovement());
        assertTrue(thornBush.happinessPenalty() > 0);
    }

    @Test
    void mudPuddleIsPassableWithSmallerPenalty() {
        MudPuddle mudPuddle = new MudPuddle(new Position(3, 3));
        assertFalse(mudPuddle.blocksMovement());
        assertTrue(mudPuddle.happinessPenalty() > 0);
        assertTrue(mudPuddle.happinessPenalty() < new ThornBush(new Position(0, 0)).happinessPenalty());
    }
}
