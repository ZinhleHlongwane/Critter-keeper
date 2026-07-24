package com.zinhle.critterkeeper.protocol;

/**
 * A response sent back to a client after handling a Request.
 */
public class Response {

    private final String result;
    private final String message;
    private final State state;

    private Response(String result, String message, State state) {
        this.result = result;
        this.message = message;
        this.state = state;
    }

    public static Response ok(String message, State state) {
        return new Response("OK", message, state);
    }

    public static Response error(String message) {
        return new Response("ERROR", message, null);
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public State getState() {
        return state;
    }

    public boolean isOk() {
        return "OK".equals(result);
    }
}
