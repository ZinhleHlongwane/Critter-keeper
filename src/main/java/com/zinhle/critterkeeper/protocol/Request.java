package com.zinhle.critterkeeper.protocol;

import java.util.List;

/**
 * A parsed request coming from a client: a command word plus arguments,
 * e.g. {"keeper":"Zinhle","command":"forward","arguments":["2"]}.
 */
public class Request {

    private String keeper;
    private String command;
    private List<String> arguments;

    public Request() {
        // for gson
    }

    public Request(String keeper, String command, List<String> arguments) {
        this.keeper = keeper;
        this.command = command;
        this.arguments = arguments;
    }

    public String getKeeper() {
        return keeper;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
