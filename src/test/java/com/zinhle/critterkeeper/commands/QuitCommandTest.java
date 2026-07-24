package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuitCommandTest {

    @Test
    void quittingRemovesTheKeeperFromTheShelter() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");

        Response response = new QuitCommand().execute(shelter, keeper, new Request("Zinhle", "quit", List.of()));

        assertTrue(response.isOk());
        assertNull(shelter.getKeeper("Zinhle"));
    }

    @Test
    void quittingDoesNotAffectOtherKeepers() {
        Shelter shelter = new Shelter(10, 10);
        Keeper zinhle = shelter.addKeeper("Zinhle");
        shelter.addKeeper("Sam");

        new QuitCommand().execute(shelter, zinhle, new Request("Zinhle", "quit", List.of()));

        assertTrue(shelter.getKeeper("Sam") != null);
    }
}
