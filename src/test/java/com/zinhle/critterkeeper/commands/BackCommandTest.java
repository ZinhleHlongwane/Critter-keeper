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

class BackCommandTest {

    @Test
    void movesOppositeToFacingWithoutTurning() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new PuppyCreature("Rex"));
        keeper.setPosition(new Position(0, 0));
        keeper.setFacing(Direction.NORTH);

        Response response = new BackCommand().execute(shelter, keeper, new Request("Zinhle", "back", List.of()));

        assertTrue(response.isOk());
        assertEquals(new Position(0, -1), keeper.getPosition());
        assertEquals(Direction.NORTH, keeper.getFacing());
    }

    @Test
    void movesMultipleStepsBackward() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new PuppyCreature("Rex"));
        keeper.setPosition(new Position(0, 0));
        keeper.setFacing(Direction.EAST);

        new BackCommand().execute(shelter, keeper, new Request("Zinhle", "back", List.of("2")));

        assertEquals(new Position(-2, 0), keeper.getPosition());
    }

    @Test
    void restoresFacingEvenWhenMoveFails() {
        Shelter shelter = new Shelter(2, 2);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new PuppyCreature("Rex"));
        // Facing NORTH means "back" tries to step SOUTH, straight off the edge.
        keeper.setPosition(new Position(-1, -1));
        keeper.setFacing(Direction.NORTH);

        Response response = new BackCommand().execute(shelter, keeper, new Request("Zinhle", "back", List.of()));

        assertTrue(!response.isOk());
        assertEquals(Direction.NORTH, keeper.getFacing());
    }
}
