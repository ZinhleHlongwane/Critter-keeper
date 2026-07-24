package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.creatures.Creature;
import com.zinhle.critterkeeper.creatures.CreatureFactory;
import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.protocol.StateFactory;
import com.zinhle.critterkeeper.world.Common;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

import java.util.List;

/** ADOPT <species> <name> - claims a new creature for this keeper. */
public class AdoptCommand extends Command {

    @Override
    public Response execute(Shelter shelter, Keeper keeper, Request request) {
        if (keeper.hasCreature()) {
            return Response.error(Common.REASON_ALREADY_ADOPTED);
        }
        List<String> args = request.getArguments();
        if (args == null || args.size() < 2) {
            return Response.error("usage: ADOPT <species> <name>");
        }
        String species = args.get(0);
        String name = args.get(1);
        if (!CreatureFactory.isKnownSpecies(species)) {
            return Response.error(Common.REASON_UNKNOWN_SPECIES);
        }
        Creature creature = CreatureFactory.create(species, name);
        keeper.adopt(creature);
        return Response.ok(name + " the " + creature.getSpecies() + " has been adopted!",
                StateFactory.from(keeper));
    }

    @Override
    public String getName() {
        return "adopt";
    }

    @Override
    public String getHelp() {
        return "ADOPT <species> <name> - adopt a creature (puppy, kitten, dragonling, rabbit, turtle)";
    }
}
