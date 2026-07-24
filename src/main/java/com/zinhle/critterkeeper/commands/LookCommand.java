package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.hazards.Hazard;
import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.protocol.StateFactory;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

/** LOOK - describes hazards and other keepers within 2 squares. */
public class LookCommand extends Command {

    private static final int VIEW_RADIUS = 2;

    @Override
    public Response execute(Shelter shelter, Keeper keeper, Request request) {
        StringBuilder description = new StringBuilder();
        for (Hazard hazard : shelter.getHazards()) {
            if (keeper.getPosition().distanceTo(hazard.getPosition()) <= VIEW_RADIUS) {
                description.append(hazard.getName()).append(" at ").append(hazard.getPosition()).append("; ");
            }
        }
        for (Keeper other : shelter.allKeepers()) {
            if (!other.getName().equals(keeper.getName())
                    && keeper.getPosition().distanceTo(other.getPosition()) <= VIEW_RADIUS) {
                description.append(other.getName()).append(" at ").append(other.getPosition()).append("; ");
            }
        }
        if (description.length() == 0) {
            description.append("nothing nearby");
        }
        return Response.ok(description.toString(), StateFactory.from(keeper));
    }

    @Override
    public String getName() {
        return "look";
    }

    @Override
    public String getHelp() {
        return "LOOK - see hazards and other keepers nearby";
    }
}
