package com.zinhle.critterkeeper.world;

/**
 * Constants shared across the world/, commands/ and server/ packages.
 */
public final class Common {

    private Common() {
        // no instances
    }

    public static final int DEFAULT_SHELTER_SIZE = 10;

    public static final String STATUS_OK = "OK";
    public static final String STATUS_ERROR = "ERROR";

    public static final String REASON_AT_EDGE = "at the edge of the shelter";
    public static final String REASON_BLOCKED = "blocked by a hazard";
    public static final String REASON_NO_CREATURE = "you have not adopted a creature yet";
    public static final String REASON_ALREADY_ADOPTED = "you already have a creature - visit the front desk to adopt another";
    public static final String REASON_UNKNOWN_SPECIES = "unknown creature species";
    public static final String REASON_TOO_TIRED = "your creature is too tired to do that right now";
}
