package com.zinhle.critterkeeper.world;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {

    @Test
    void rightTurnsClockwise() {
        assertEquals(Direction.EAST, Direction.NORTH.right());
        assertEquals(Direction.SOUTH, Direction.EAST.right());
        assertEquals(Direction.WEST, Direction.SOUTH.right());
        assertEquals(Direction.NORTH, Direction.WEST.right());
    }

    @Test
    void leftTurnsCounterClockwise() {
        assertEquals(Direction.WEST, Direction.NORTH.left());
        assertEquals(Direction.SOUTH, Direction.WEST.left());
        assertEquals(Direction.EAST, Direction.SOUTH.left());
        assertEquals(Direction.NORTH, Direction.EAST.left());
    }

    @Test
    void deltasMatchCompassDirection() {
        assertEquals(1, Direction.NORTH.deltaY());
        assertEquals(0, Direction.NORTH.deltaX());
        assertEquals(1, Direction.EAST.deltaX());
        assertEquals(0, Direction.EAST.deltaY());
    }
}
