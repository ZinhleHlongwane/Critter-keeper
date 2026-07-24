package com.zinhle.critterkeeper.creatures;

/**
 * Creates the right Creature subclass from the species name a keeper types
 * in the LAUNCH command, e.g. "LAUNCH puppy Rex".
 */
public final class CreatureFactory {

    private CreatureFactory() {
        // no instances
    }

    public static Creature create(String species, String name) {
        if (species == null) {
            return null;
        }
        switch (species.trim().toLowerCase()) {
            case "puppy":
                return new PuppyCreature(name);
            case "kitten":
                return new KittenCreature(name);
            case "dragonling":
                return new DragonlingCreature(name);
            case "rabbit":
                return new RabbitCreature(name);
            case "turtle":
                return new TurtleCreature(name);
            default:
                return null;
        }
    }

    public static boolean isKnownSpecies(String species) {
        return create(species, "probe") != null;
    }
}
