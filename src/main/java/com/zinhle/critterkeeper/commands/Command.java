package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

/**
 * Base class for every command a keeper can send to the shelter server.
 * Mirrors the command pattern used for robot commands in the original
 * project this one is modelled on.
 */
public abstract class Command {

    public abstract Response execute(Shelter shelter, Keeper keeper, Request request);

    public abstract String getName();

    public abstract String getHelp();
}
