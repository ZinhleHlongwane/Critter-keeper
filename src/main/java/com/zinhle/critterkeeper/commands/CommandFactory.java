package com.zinhle.critterkeeper.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Builds the full set of available commands and looks them up by name.
 */
public class CommandFactory {

    private final Map<String, Command> commandsByName = new HashMap<>();

    public CommandFactory() {
        List<Command> baseCommands = new ArrayList<>();
        baseCommands.add(new AdoptCommand());
        baseCommands.add(new ForwardCommand());
        baseCommands.add(new BackCommand());
        baseCommands.add(new LeftCommand());
        baseCommands.add(new RightCommand());
        baseCommands.add(new FeedCommand());
        baseCommands.add(new PlayCommand());
        baseCommands.add(new RestCommand());
        baseCommands.add(new StateCommand());
        baseCommands.add(new LookCommand());
        baseCommands.add(new ReleaseCommand());
        baseCommands.add(new QuitCommand());

        for (Command command : baseCommands) {
            commandsByName.put(command.getName(), command);
        }
        // HELP needs to know about every other command, including itself.
        HelpCommand helpCommand = new HelpCommand(new ArrayList<>(baseCommands));
        commandsByName.put(helpCommand.getName(), helpCommand);
    }

    public Command get(String name) {
        if (name == null) {
            return null;
        }
        return commandsByName.get(name.trim().toLowerCase());
    }
}
