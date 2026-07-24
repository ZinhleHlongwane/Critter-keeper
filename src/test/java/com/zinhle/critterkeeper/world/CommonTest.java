package com.zinhle.critterkeeper.world;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommonTest {

    @Test
    void defaultShelterSizeIsPositive() {
        assertEquals(10, Common.DEFAULT_SHELTER_SIZE);
    }

    @Test
    void statusStringsAreDefined() {
        assertEquals("OK", Common.STATUS_OK);
        assertEquals("ERROR", Common.STATUS_ERROR);
    }

    @Test
    void reasonMessagesAreNonEmpty() {
        assertNotNull(Common.REASON_AT_EDGE);
        assertNotNull(Common.REASON_BLOCKED);
        assertNotNull(Common.REASON_NO_CREATURE);
        assertNotNull(Common.REASON_ALREADY_ADOPTED);
        assertNotNull(Common.REASON_UNKNOWN_SPECIES);
        assertNotNull(Common.REASON_TOO_TIRED);
    }
}
