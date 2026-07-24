package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.protocol.StateFactory;
import com.zinhle.critterkeeper.world.Common;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

/** FEED - reduces the keeper's creature's hunger. */
public class FeedCommand extends Command {

    @Override
    public Response execute(Shelter shelter, Keeper keeper, Request request) {
        if (!keeper.hasCreature()) {
            return Response.error(Common.REASON_NO_CREATURE);
        }
        keeper.getCreature().feed();
        return Response.ok(keeper.getCreature().getName() + " has been fed", StateFactory.from(keeper));
    }

    @Override
    public String getName() {
        return "feed";
    }

    @Override
    public String getHelp() {
        return "FEED - feed your creature to reduce its hunger";
    }
}
