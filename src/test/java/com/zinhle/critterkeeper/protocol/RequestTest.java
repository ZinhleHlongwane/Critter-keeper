package com.zinhle.critterkeeper.protocol;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestTest {

    @Test
    void storesKeeperCommandAndArguments() {
        Request request = new Request("Zinhle", "forward", List.of("2"));
        assertEquals("Zinhle", request.getKeeper());
        assertEquals("forward", request.getCommand());
        assertEquals(List.of("2"), request.getArguments());
    }

    @Test
    void supportsEmptyArgumentList() {
        Request request = new Request("Zinhle", "state", List.of());
        assertEquals(0, request.getArguments().size());
    }
}
