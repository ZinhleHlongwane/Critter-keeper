package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.hazards.Pond;
import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Position;
import com.zinhle.critterkeeper.world.Shelter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LookCommandTest {

    @Test
    void reportsNothingWhenYardIsEmpty() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.setPosition(new Position(0, 0));

        Response response = new LookCommand().execute(shelter, keeper, new Request("Zinhle", "look", List.of()));

        assertTrue(response.isOk());
        assertTrue(response.getMessage().contains("nothing nearby"));
    }

    @Test
    void reportsANearbyHazard() {
        Shelter shelter = new Shelter(10, 10);
        shelter.addHazard(new Pond(new Position(1, 1)));
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.setPosition(new Position(0, 0));

        Response response = new LookCommand().execute(shelter, keeper, new Request("Zinhle", "look", List.of()));

        assertTrue(response.getMessage().contains("Pond"));
    }

    @Test
    void reportsANearbyKeeperButNotItself() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.setPosition(new Position(0, 0));
        Keeper other = shelter.addKeeper("Sam");
        other.setPosition(new Position(1, 0));

        Response response = new LookCommand().execute(shelter, keeper, new Request("Zinhle", "look", List.of()));

        assertTrue(response.getMessage().contains("Sam"));
        assertTrue(!response.getMessage().contains("Zinhle"));
    }

    @Test
    void ignoresHazardsOutsideViewRadius() {
        Shelter shelter = new Shelter(20, 20);
        shelter.addHazard(new Pond(new Position(9, 9)));
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.setPosition(new Position(0, 0));

        Response response = new LookCommand().execute(shelter, keeper, new Request("Zinhle", "look", List.of()));

        assertTrue(response.getMessage().contains("nothing nearby"));
    }
}
