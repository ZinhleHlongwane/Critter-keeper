package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.creatures.PuppyCreature;
import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReleaseCommandTest {

    @Test
    void releasingWithNoCreatureFails() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");

        Response response = new ReleaseCommand().execute(shelter, keeper, new Request("Zinhle", "release", List.of()));

        assertFalse(response.isOk());
    }

    @Test
    void releasingFreesTheKeeperToAdoptAgain() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new PuppyCreature("Rex"));

        Response response = new ReleaseCommand().execute(shelter, keeper, new Request("Zinhle", "release", List.of()));

        assertTrue(response.isOk());
        assertFalse(keeper.hasCreature());
    }

    @Test
    void releaseMessageMentionsTheCreatureName() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new PuppyCreature("Rex"));

        Response response = new ReleaseCommand().execute(shelter, keeper, new Request("Zinhle", "release", List.of()));

        assertTrue(response.getMessage().contains("Rex"));
    }
}
