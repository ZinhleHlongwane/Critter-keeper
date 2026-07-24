package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.protocol.StateFactory;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

/** RIGHT - turns the keeper 90 degrees clockwise. */
public class RightCommand extends Command {

    @Override
    public Response execute(Shelter shelter, Keeper keeper, Request request) {
        keeper.setFacing(keeper.getFacing().right());
        return Response.ok("turned right, now facing " + keeper.getFacing(), StateFactory.from(keeper));
    }

    @Override
    public String getName() {
        return "right";
    }

    @Override
    public String getHelp() {
        return "RIGHT - turn 90 degrees right";
    }
}
