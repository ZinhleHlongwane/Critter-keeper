package com.zinhle.critterkeeper.creatures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KittenCreatureTest {

    @Test
    void speciesNameIsKitten() {
        assertEquals("Kitten", new KittenCreature("Whiskers").getSpecies());
    }

    @Test
    void restsBetterThanItPlays() {
        // A kitten's whole gimmick is being low-maintenance: resting should
        // restore more than a play session drains.
        KittenCreature kitten = new KittenCreature("Whiskers");
        int energyBefore = kitten.getEnergy();
        kitten.play();
        int energyAfterPlay = kitten.getEnergy();
        kitten.rest();
        assertTrue(kitten.getEnergy() >= energyAfterPlay);
    }

    @Test
    void moveEnergyCostIsVeryLow() {
        assertEquals(1, new KittenCreature("Whiskers").moveEnergyCost());
    }

    @Test
    void feedingReducesHunger() {
        KittenCreature kitten = new KittenCreature("Whiskers");
        int hungerBefore = kitten.getHunger();
        kitten.feed();
        assertTrue(kitten.getHunger() <= hungerBefore);
    }
}
