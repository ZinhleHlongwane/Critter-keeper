package com.zinhle.critterkeeper.config;

import com.zinhle.critterkeeper.hazards.MudPuddle;
import com.zinhle.critterkeeper.hazards.Pond;
import com.zinhle.critterkeeper.hazards.ThornBush;
import com.zinhle.critterkeeper.world.Position;
import com.zinhle.critterkeeper.world.Shelter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ConfigLoaderTest {

    @Test
    void fallsBackToDefaultsWhenFileIsMissing() {
        Shelter shelter = ConfigLoader.loadShelter("does-not-exist.json");
        assertEquals(10, shelter.getWidth());
        assertEquals(10, shelter.getHeight());
        assertTrue(shelter.getHazards().isEmpty());
    }

    @Test
    void fallsBackToDefaultsWhenPathIsNull() {
        Shelter shelter = ConfigLoader.loadShelter(null);
        assertEquals(10, shelter.getWidth());
    }

    @Test
    void loadsWidthAndHeightFromFile(@TempDir Path tempDir) throws IOException {
        Path configFile = tempDir.resolve("shelter.json");
        Files.writeString(configFile, "{\"width\": 6, \"height\": 8, \"hazards\": []}");

        Shelter shelter = ConfigLoader.loadShelter(configFile.toString());

        assertEquals(6, shelter.getWidth());
        assertEquals(8, shelter.getHeight());
    }

    @Test
    void loadsEachHazardTypeIntoTheRightClass(@TempDir Path tempDir) throws IOException {
        Path configFile = tempDir.resolve("shelter.json");
        String json = "{\"width\": 10, \"height\": 10, \"hazards\": ["
                + "{\"type\":\"pond\",\"x\":1,\"y\":1},"
                + "{\"type\":\"thornbush\",\"x\":2,\"y\":2},"
                + "{\"type\":\"mudpuddle\",\"x\":3,\"y\":3}"
                + "]}";
        Files.writeString(configFile, json);

        Shelter shelter = ConfigLoader.loadShelter(configFile.toString());

        assertInstanceOf(Pond.class, shelter.hazardAt(new Position(1, 1)));
        assertInstanceOf(ThornBush.class, shelter.hazardAt(new Position(2, 2)));
        assertInstanceOf(MudPuddle.class, shelter.hazardAt(new Position(3, 3)));
    }

    @Test
    void skipsUnknownHazardTypesWithoutCrashing(@TempDir Path tempDir) throws IOException {
        Path configFile = tempDir.resolve("shelter.json");
        String json = "{\"width\": 10, \"height\": 10, \"hazards\": ["
                + "{\"type\":\"volcano\",\"x\":5,\"y\":5}"
                + "]}";
        Files.writeString(configFile, json);

        Shelter shelter = ConfigLoader.loadShelter(configFile.toString());

        assertNull(shelter.hazardAt(new Position(5, 5)));
    }
}
