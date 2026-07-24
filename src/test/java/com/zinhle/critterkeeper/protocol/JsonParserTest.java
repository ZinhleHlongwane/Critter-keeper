package com.zinhle.critterkeeper.protocol;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonParserTest {

    @Test
    void parsesARequestFromJson() {
        String json = "{\"keeper\":\"Zinhle\",\"command\":\"forward\",\"arguments\":[\"2\"]}";

        Request request = JsonParser.parseRequest(json);

        assertEquals("Zinhle", request.getKeeper());
        assertEquals("forward", request.getCommand());
        assertEquals(List.of("2"), request.getArguments());
    }

    @Test
    void serialisesAnOkResponseToJson() {
        State state = new State(0, 0, "NORTH", "Puppy", 20, 80, 100);
        Response response = Response.ok("adopted!", state);

        String json = JsonParser.toJson(response);

        assertTrue(json.contains("\"result\":\"OK\""));
        assertTrue(json.contains("adopted!"));
        assertTrue(json.contains("Puppy"));
    }

    @Test
    void serialisesAnErrorResponseWithoutState() {
        Response response = Response.error("blocked by a hazard");

        String json = JsonParser.toJson(response);

        assertTrue(json.contains("\"result\":\"ERROR\""));
        assertTrue(json.contains("blocked by a hazard"));
    }

    @Test
    void roundTripsARequestThroughJson() {
        Request original = new Request("Zinhle", "adopt", List.of("puppy", "Rex"));
        String json = JsonParser.toJson(original);
        Request parsed = JsonParser.parseRequest(json);

        assertEquals(original.getKeeper(), parsed.getKeeper());
        assertEquals(original.getCommand(), parsed.getCommand());
        assertEquals(original.getArguments(), parsed.getArguments());
    }

    @Test
    void doesNotEscapeAnglesBracketsOrApostrophesInMessages() {
        Response response = Response.ok("ADOPT <species> <name> - keeper's usage", null);

        String json = JsonParser.toJson(response);

        assertTrue(json.contains("<species>"));
        assertTrue(json.contains("<name>"));
        assertTrue(json.contains("keeper's"));
        assertTrue(!json.contains("\\u003c"));
        assertTrue(!json.contains("\\u0027"));
    }

    @Test
    void parsesAResponseBackFromJson() {
        State state = new State(1, 2, "EAST", "Kitten", 10, 90, 60);
        Response original = Response.ok("all good", state);
        String json = JsonParser.toJson(original);

        Response parsed = JsonParser.parseResponse(json);

        assertTrue(parsed.isOk());
        assertEquals("all good", parsed.getMessage());
        assertEquals("Kitten", parsed.getState().getSpecies());
    }

    @Test
    void parsedErrorResponseHasNoState() {
        String json = JsonParser.toJson(Response.error("nope"));

        Response parsed = JsonParser.parseResponse(json);

        assertTrue(!parsed.isOk());
        assertEquals(null, parsed.getState());
    }
}
