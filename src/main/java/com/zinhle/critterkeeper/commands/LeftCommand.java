package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.protocol.StateFactory;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

/** LEFT - turns the keeper 90 degrees counter-clockwise. */
public class LeftCommand extends Command {

    @Override
    public Response execute(Shelter shelter, Keeper keeper, Request request) {
        keeper.setFacing(keeper.getFacing().left());
        return Response.ok("turned left, now facing " + keeper.getFacing(), StateFactory.from(keeper));
    }

    @Override
    public String getName() {
        return "left";
    }

    @Override
    public String getHelp() {
        return "LEFT - turn 90 degrees left";
    }
}
