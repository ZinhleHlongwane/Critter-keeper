package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.protocol.StateFactory;
import com.zinhle.critterkeeper.world.Common;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

/** RELEASE - returns the keeper's creature to the shelter, freeing them up to adopt another. */
public class ReleaseCommand extends Command {

    @Override
    public Response execute(Shelter shelter, Keeper keeper, Request request) {
        if (!keeper.hasCreature()) {
            return Response.error(Common.REASON_NO_CREATURE);
        }
        String name = keeper.getCreature().getName();
        keeper.releaseCreature();
        return Response.ok(name + " has been returned to the shelter", StateFactory.from(keeper));
    }

    @Override
    public String getName() {
        return "release";
    }

    @Override
    public String getHelp() {
        return "RELEASE - return your current creature to the shelter";
    }
}
