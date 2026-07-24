package com.zinhle.critterkeeper.world;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @Test
    void stepMovesInCorrectDirection() {
        Position start = new Position(0, 0);
        assertEquals(new Position(0, 3), start.step(Direction.NORTH, 3));
        assertEquals(new Position(3, 0), start.step(Direction.EAST, 3));
    }

    @Test
    void distanceToIsManhattanDistance() {
        Position a = new Position(0, 0);
        Position b = new Position(3, 4);
        assertEquals(7, a.distanceTo(b));
    }

    @Test
    void equalsComparesCoordinates() {
        assertEquals(new Position(2, 2), new Position(2, 2));
    }
}
