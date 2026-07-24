package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.protocol.StateFactory;
import com.zinhle.critterkeeper.world.Direction;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

import java.util.List;

/** BACK [steps] - walks the keeper's creature backward without turning around. */
public class BackCommand extends Command {

    @Override
    public Response execute(Shelter shelter, Keeper keeper, Request request) {
        int steps = parseSteps(request.getArguments());
        Direction original = keeper.getFacing();
        Direction opposite = original.right().right();
        keeper.setFacing(opposite);
        String failureReason = shelter.tryMove(keeper, steps);
        keeper.setFacing(original);
        if (failureReason != null) {
            return Response.error(failureReason);
        }
        return Response.ok("moved back " + steps, StateFactory.from(keeper));
    }

    private int parseSteps(List<String> args) {
        if (args == null || args.isEmpty()) {
            return 1;
        }
        try {
            return Math.max(1, Integer.parseInt(args.get(0)));
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    @Override
    public String getName() {
        return "back";
    }

    @Override
    public String getHelp() {
        return "BACK [steps] - walk backward, default 1 step";
    }
}
