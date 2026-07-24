package com.zinhle.critterkeeper.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Plain data class describing the shelter to build - matches the shape of
 * the shelter.json config file. Loaded with Gson.
 */
public class ShelterConfig {

    private int width = 10;
    private int height = 10;
    private List<HazardConfig> hazards = new ArrayList<>();

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<HazardConfig> getHazards() {
        return hazards;
    }

    public static class HazardConfig {
        private String type;
        private int x;
        private int y;

        public String getType() {
            return type;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
