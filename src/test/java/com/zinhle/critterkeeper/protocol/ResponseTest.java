package com.zinhle.critterkeeper.protocol;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResponseTest {

    @Test
    void okResponseCarriesStateAndMessage() {
        State state = new State(1, 2, "NORTH", "Puppy", 10, 80, 90);
        Response response = Response.ok("moved forward", state);

        assertTrue(response.isOk());
        assertEquals("OK", response.getResult());
        assertEquals("moved forward", response.getMessage());
        assertEquals(state, response.getState());
    }

    @Test
    void errorResponseHasNoState() {
        Response response = Response.error("blocked by a hazard");

        assertFalse(response.isOk());
        assertEquals("ERROR", response.getResult());
        assertEquals("blocked by a hazard", response.getMessage());
        assertNull(response.getState());
    }
}
