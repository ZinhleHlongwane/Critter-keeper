package com.zinhle.critterkeeper.commands;

import org.junit.jupiter.api.Test;
import com.zinhle.critterkeeper.creatures.PuppyCreature;
import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CareCommandsTest {

    @Test
    void feedRequiresACreature() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");

        Response response = new FeedCommand().execute(shelter, keeper, new Request("Zinhle", "feed", List.of()));

        assertFalse(response.isOk());
    }

    @Test
    void feedReducesHunger() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new PuppyCreature("Rex"));
        int hungerBefore = keeper.getCreature().getHunger();

        Response response = new FeedCommand().execute(shelter, keeper, new Request("Zinhle", "feed", List.of()));

        assertTrue(response.isOk());
        assertTrue(keeper.getCreature().getHunger() <= hungerBefore);
    }

    @Test
    void playIncreasesHappiness() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new PuppyCreature("Rex"));
        int happinessBefore = keeper.getCreature().getHappiness();

        Response response = new PlayCommand().execute(shelter, keeper, new Request("Zinhle", "play", List.of()));

        assertTrue(response.isOk());
        assertTrue(keeper.getCreature().getHappiness() >= happinessBefore);
    }

    @Test
    void restRestoresEnergy() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new PuppyCreature("Rex"));
        keeper.getCreature().spendEnergyForMove();
        int energyBefore = keeper.getCreature().getEnergy();

        Response response = new RestCommand().execute(shelter, keeper, new Request("Zinhle", "rest", List.of()));

        assertTrue(response.isOk());
        assertTrue(keeper.getCreature().getEnergy() >= energyBefore);
    }
}
