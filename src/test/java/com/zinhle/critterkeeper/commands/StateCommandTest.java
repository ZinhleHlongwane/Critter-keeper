package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.creatures.PuppyCreature;
import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.world.Direction;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Position;
import com.zinhle.critterkeeper.world.Shelter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StateCommandTest {

    @Test
    void reportsPositionAndFacing() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.setPosition(new Position(2, 3));
        keeper.setFacing(Direction.EAST);

        Response response = new StateCommand().execute(shelter, keeper, new Request("Zinhle", "state", List.of()));

        assertTrue(response.isOk());
        assertEquals(2, response.getState().getX());
        assertEquals(3, response.getState().getY());
        assertEquals("EAST", response.getState().getFacing());
    }

    @Test
    void reportsCreatureStatsWhenAdopted() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new PuppyCreature("Rex"));

        Response response = new StateCommand().execute(shelter, keeper, new Request("Zinhle", "state", List.of()));

        assertEquals("Puppy", response.getState().getSpecies());
    }
}
