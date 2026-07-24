package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.protocol.StateFactory;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

/** STATE - reports the keeper's current position, facing and creature stats. */
public class StateCommand extends Command {

    @Override
    public Response execute(Shelter shelter, Keeper keeper, Request request) {
        return Response.ok("current state", StateFactory.from(keeper));
    }

    @Override
    public String getName() {
        return "state";
    }

    @Override
    public String getHelp() {
        return "STATE - show your keeper's position, facing and creature's stats";
    }
}
