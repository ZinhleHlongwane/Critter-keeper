package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.protocol.StateFactory;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;

import java.util.List;

/** HELP - lists every command and what it does. */
public class HelpCommand extends Command {

    private final List<Command> allCommands;

    public HelpCommand(List<Command> allCommands) {
        this.allCommands = allCommands;
    }

    @Override
    public Response execute(Shelter shelter, Keeper keeper, Request request) {
        StringBuilder help = new StringBuilder();
        for (Command command : allCommands) {
            help.append(command.getHelp()).append("\n");
        }
        return Response.ok(help.toString(), StateFactory.from(keeper));
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "HELP - list all available commands";
    }
}
