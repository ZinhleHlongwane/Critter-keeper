package com.zinhle.critterkeeper.commands;

import org.junit.jupiter.api.Test;
import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdoptCommandTest {

    @Test
    void adoptingKnownSpeciesSucceeds() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        AdoptCommand command = new AdoptCommand();
        Request request = new Request("Zinhle", "adopt", List.of("puppy", "Rex"));

        Response response = command.execute(shelter, keeper, request);

        assertTrue(response.isOk());
        assertTrue(keeper.hasCreature());
    }

    @Test
    void adoptingUnknownSpeciesFails() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        AdoptCommand command = new AdoptCommand();
        Request request = new Request("Zinhle", "adopt", List.of("unicorn", "Sparkle"));

        Response response = command.execute(shelter, keeper, request);

        assertFalse(response.isOk());
        assertFalse(keeper.hasCreature());
    }

    @Test
    void cannotAdoptWhileAlreadyHavingACreature() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        AdoptCommand command = new AdoptCommand();
        command.execute(shelter, keeper, new Request("Zinhle", "adopt", List.of("puppy", "Rex")));

        Response second = command.execute(shelter, keeper, new Request("Zinhle", "adopt", List.of("kitten", "Whiskers")));

        assertFalse(second.isOk());
    }
}
