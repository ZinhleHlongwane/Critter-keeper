package com.zinhle.critterkeeper.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

class CommandFactoryTest {

    @Test
    void looksUpKnownCommandsCaseInsensitively() {
        CommandFactory factory = new CommandFactory();
        assertInstanceOf(ForwardCommand.class, factory.get("FORWARD"));
        assertInstanceOf(AdoptCommand.class, factory.get("adopt"));
        assertInstanceOf(HelpCommand.class, factory.get("help"));
    }

    @Test
    void returnsNullForUnknownCommand() {
        CommandFactory factory = new CommandFactory();
        assertNull(factory.get("teleport"));
        assertNull(factory.get(null));
    }
}
