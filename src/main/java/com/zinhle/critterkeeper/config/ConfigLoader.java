package com.zinhle.critterkeeper.config;

import com.google.gson.Gson;
import com.zinhle.critterkeeper.hazards.MudPuddle;
import com.zinhle.critterkeeper.hazards.Pond;
import com.zinhle.critterkeeper.hazards.ThornBush;
import com.zinhle.critterkeeper.world.Position;
import com.zinhle.critterkeeper.world.Shelter;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Reads a shelter.json config file and turns it into a ready-to-use Shelter.
 * Falls back to sensible defaults if no file is found.
 */
public final class ConfigLoader {

    private static final Gson GSON = new Gson();

    private ConfigLoader() {
        // no instances
    }

    public static Shelter loadShelter(String path) {
        ShelterConfig config = readConfig(path);
        Shelter shelter = new Shelter(config.getWidth(), config.getHeight());
        for (ShelterConfig.HazardConfig hazardConfig : config.getHazards()) {
            Position position = new Position(hazardConfig.getX(), hazardConfig.getY());
            switch (hazardConfig.getType().toLowerCase()) {
                case "pond":
                    shelter.addHazard(new Pond(position));
                    break;
                case "thornbush":
                    shelter.addHazard(new ThornBush(position));
                    break;
                case "mudpuddle":
                    shelter.addHazard(new MudPuddle(position));
                    break;
                default:
                    // unknown hazard type in config - skip it
                    break;
            }
        }
        return shelter;
    }

    private static ShelterConfig readConfig(String path) {
        if (path != null && Files.exists(Path.of(path))) {
            try (Reader reader = new FileReader(path)) {
                ShelterConfig config = GSON.fromJson(reader, ShelterConfig.class);
                if (config != null) {
                    return config;
                }
            } catch (IOException e) {
                // fall through to default config below
            }
        }
        return new ShelterConfig();
    }
}
