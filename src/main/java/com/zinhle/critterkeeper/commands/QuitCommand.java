package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.protocol.StateFactory;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

/** QUIT - the keeper leaves the shelter, releasing any creature they had. */
public class QuitCommand extends Command {

    @Override
    public Response execute(Shelter shelter, Keeper keeper, Request request) {
        shelter.removeKeeper(keeper.getName());
        return Response.ok(keeper.getName() + " has left the shelter", StateFactory.from(keeper));
    }

    @Override
    public String getName() {
        return "quit";
    }

    @Override
    public String getHelp() {
        return "QUIT - leave the shelter";
    }
}
