package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.protocol.StateFactory;
import com.zinhle.critterkeeper.world.Common;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

/** PLAY - boosts happiness at the cost of energy. */
public class PlayCommand extends Command {

    @Override
    public Response execute(Shelter shelter, Keeper keeper, Request request) {
        if (!keeper.hasCreature()) {
            return Response.error(Common.REASON_NO_CREATURE);
        }
        if (keeper.getCreature().isAsleep()) {
            return Response.error(Common.REASON_TOO_TIRED);
        }
        keeper.getCreature().play();
        return Response.ok(keeper.getCreature().getName() + " had fun playing!", StateFactory.from(keeper));
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getHelp() {
        return "PLAY - play with your creature to boost happiness (costs energy)";
    }
}
