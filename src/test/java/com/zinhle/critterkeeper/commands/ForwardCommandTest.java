package com.zinhle.critterkeeper.commands;

import org.junit.jupiter.api.Test;
import com.zinhle.critterkeeper.creatures.PuppyCreature;
import com.zinhle.critterkeeper.hazards.Pond;
import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.world.Direction;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Position;
import com.zinhle.critterkeeper.world.Shelter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ForwardCommandTest {

    @Test
    void movesForwardOneStepByDefault() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new PuppyCreature("Rex"));
        keeper.setPosition(new Position(0, 0));
        keeper.setFacing(Direction.NORTH);

        Response response = new ForwardCommand().execute(shelter, keeper, new Request("Zinhle", "forward", List.of()));

        assertTrue(response.isOk());
        assertEquals(new Position(0, 1), keeper.getPosition());
    }

    @Test
    void movesGivenNumberOfSteps() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new PuppyCreature("Rex"));
        keeper.setPosition(new Position(0, 0));
        keeper.setFacing(Direction.EAST);

        Response response = new ForwardCommand().execute(shelter, keeper, new Request("Zinhle", "forward", List.of("3")));

        assertTrue(response.isOk());
        assertEquals(new Position(3, 0), keeper.getPosition());
    }

    @Test
    void failsWithoutACreature() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");

        Response response = new ForwardCommand().execute(shelter, keeper, new Request("Zinhle", "forward", List.of()));

        assertFalse(response.isOk());
    }

    @Test
    void failsWhenBlockedByAPond() {
        Shelter shelter = new Shelter(10, 10);
        shelter.addHazard(new Pond(new Position(0, 1)));
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new PuppyCreature("Rex"));
        keeper.setPosition(new Position(0, 0));
        keeper.setFacing(Direction.NORTH);

        Response response = new ForwardCommand().execute(shelter, keeper, new Request("Zinhle", "forward", List.of()));

        assertFalse(response.isOk());
    }
}
