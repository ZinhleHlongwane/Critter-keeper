package com.zinhle.critterkeeper.commands;

import org.junit.jupiter.api.Test;
import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.world.Direction;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TurnCommandTest {

    @Test
    void leftCommandTurnsCounterClockwise() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.setFacing(Direction.NORTH);

        new LeftCommand().execute(shelter, keeper, new Request("Zinhle", "left", List.of()));

        assertEquals(Direction.WEST, keeper.getFacing());
    }

    @Test
    void rightCommandTurnsClockwise() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.setFacing(Direction.NORTH);

        new RightCommand().execute(shelter, keeper, new Request("Zinhle", "right", List.of()));

        assertEquals(Direction.EAST, keeper.getFacing());
    }
}
