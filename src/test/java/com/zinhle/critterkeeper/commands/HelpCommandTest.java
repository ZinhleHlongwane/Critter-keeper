package com.zinhle.critterkeeper.commands;

import com.zinhle.critterkeeper.protocol.Request;
import com.zinhle.critterkeeper.protocol.Response;
import com.zinhle.critterkeeper.world.Keeper;
import com.zinhle.critterkeeper.world.Shelter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HelpCommandTest {

    @Test
    void listsEveryUnderlyingCommandsHelpText() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        List<Command> commands = List.of(new ForwardCommand(), new AdoptCommand());
        HelpCommand help = new HelpCommand(commands);

        Response response = help.execute(shelter, keeper, new Request("Zinhle", "help", List.of()));

        assertTrue(response.isOk());
        assertTrue(response.getMessage().contains("FORWARD"));
        assertTrue(response.getMessage().contains("ADOPT"));
    }

    @Test
    void nameAndHelpTextAreSet() {
        HelpCommand help = new HelpCommand(List.of());
        assertTrue(help.getName().equals("help"));
        assertTrue(help.getHelp().toLowerCase().contains("help"));
    }
}
