package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.protocol.StateFactory;
import com.zinhle.critterkeeper.world.Common;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

/** REST - restores the keeper's creature's energy. */
public class RestCommand extends Command {

    @Override
    public Response execute(Shelter shelter, Keeper keeper, Request request) {
        if (!keeper.hasCreature()) {
            return Response.error(Common.REASON_NO_CREATURE);
        }
        keeper.getCreature().rest();
        return Response.ok(keeper.getCreature().getName() + " rested a while", StateFactory.from(keeper));
    }

    @Override
    public String getName() {
        return "rest";
    }

    @Override
    public String getHelp() {
        return "REST - let your creature rest to restore energy";
    }
}
