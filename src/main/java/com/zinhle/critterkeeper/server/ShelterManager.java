package com.zinhle.critterkeeper.server;

import com.zinhle.critterkeeper.commands.Command;
import com.zinhle.critterkeeper.commands.CommandFactory;
import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

/**
 * Owns the single shared Shelter and routes each incoming Request to the
 * right Command, creating a Keeper the first time a name is seen.
 */
public class ShelterManager {

    private final Shelter shelter;
    private final CommandFactory commandFactory = new CommandFactory();

    public ShelterManager(Shelter shelter) {
        this.shelter = shelter;
    }

    public synchronized Response handle(Request request) {
        if (request == null || request.getKeeper() == null || request.getCommand() == null) {
            return Response.error("malformed request");
        }
        Keeper keeper = shelter.getKeeper(request.getKeeper());
        if (keeper == null) {
            keeper = shelter.addKeeper(request.getKeeper());
        }
        Command command = commandFactory.get(request.getCommand());
        if (command == null) {
            return Response.error("unknown command: " + request.getCommand());
        }
        return command.execute(shelter, keeper, request);
    }

    public Shelter getShelter() {
        return shelter;
    }
}
