package com.zinhle.critterkeeper.server;

import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.world.Shelter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShelterManagerTest {

    @Test
    void createsAKeeperTheFirstTimeItSeesTheirName() {
        ShelterManager manager = new ShelterManager(new Shelter(10, 10));

        Response response = manager.handle(new Request("Zinhle", "state", List.of()));

        assertTrue(response.isOk());
        assertNotNull(manager.getShelter().getKeeper("Zinhle"));
    }

    @Test
    void reusesTheSameKeeperAcrossRequests() {
        ShelterManager manager = new ShelterManager(new Shelter(10, 10));
        manager.handle(new Request("Zinhle", "adopt", List.of("puppy", "Rex")));

        Response response = manager.handle(new Request("Zinhle", "state", List.of()));

        assertEquals("Puppy", response.getState().getSpecies());
    }

    @Test
    void rejectsAnUnknownCommand() {
        ShelterManager manager = new ShelterManager(new Shelter(10, 10));

        Response response = manager.handle(new Request("Zinhle", "teleport", List.of()));

        assertFalse(response.isOk());
    }

    @Test
    void rejectsAMalformedRequestWithNoKeeperOrCommand() {
        ShelterManager manager = new ShelterManager(new Shelter(10, 10));

        assertFalse(manager.handle(null).isOk());
        assertFalse(manager.handle(new Request(null, "state", List.of())).isOk());
        assertFalse(manager.handle(new Request("Zinhle", null, List.of())).isOk());
    }

    @Test
    void fullPlaySessionAdoptMoveFeedRelease() {
        ShelterManager manager = new ShelterManager(new Shelter(10, 10));

        assertTrue(manager.handle(new Request("Zinhle", "adopt", List.of("turtle", "Shelly"))).isOk());
        assertTrue(manager.handle(new Request("Zinhle", "forward", List.of("1"))).isOk());
        assertTrue(manager.handle(new Request("Zinhle", "feed", List.of())).isOk());
        assertTrue(manager.handle(new Request("Zinhle", "play", List.of())).isOk());
        assertTrue(manager.handle(new Request("Zinhle", "rest", List.of())).isOk());
        assertTrue(manager.handle(new Request("Zinhle", "release", List.of())).isOk());
        assertTrue(manager.handle(new Request("Zinhle", "quit", List.of())).isOk());
    }
}
