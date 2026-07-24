package com.zinhle.critterkeeper.protocol;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StateTest {

    @Test
    void storesAllFieldsPassedIn() {
        State state = new State(3, -4, "EAST", "Rabbit", 15, 70, 55);

        assertEquals(3, state.getX());
        assertEquals(-4, state.getY());
        assertEquals("EAST", state.getFacing());
        assertEquals("Rabbit", state.getSpecies());
        assertEquals(15, state.getHunger());
        assertEquals(70, state.getHappiness());
        assertEquals(55, state.getEnergy());
    }
}
