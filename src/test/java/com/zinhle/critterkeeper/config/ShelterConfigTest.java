package com.zinhle.critterkeeper.config;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShelterConfigTest {

    @Test
    void defaultConstructorGivesTenByTenWithNoHazards() {
        ShelterConfig config = new ShelterConfig();
        assertEquals(10, config.getWidth());
        assertEquals(10, config.getHeight());
        assertTrue(config.getHazards().isEmpty());
    }

    @Test
    void gsonPopulatesHazardConfigFields() {
        String json = "{\"width\":5,\"height\":5,\"hazards\":[{\"type\":\"pond\",\"x\":1,\"y\":2}]}";
        ShelterConfig config = new Gson().fromJson(json, ShelterConfig.class);

        assertEquals(5, config.getWidth());
        assertEquals(1, config.getHazards().size());
        assertEquals("pond", config.getHazards().get(0).getType());
        assertEquals(1, config.getHazards().get(0).getX());
        assertEquals(2, config.getHazards().get(0).getY());
    }
}
