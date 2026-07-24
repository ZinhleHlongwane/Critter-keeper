package com.zinhle.critterkeeper.creatures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PuppyCreatureTest {

    @Test
    void speciesNameIsPuppy() {
        assertEquals("Puppy", new PuppyCreature("Rex").getSpecies());
    }

    @Test
    void hasASpecialAbilityDescription() {
        assertNotNull(new PuppyCreature("Rex").getSpecialAbility());
    }

    @Test
    void moveEnergyCostIsLow() {
        assertEquals(2, new PuppyCreature("Rex").moveEnergyCost());
    }

    @Test
    void startsWithExpectedDefaultStats() {
        PuppyCreature puppy = new PuppyCreature("Rex");
        assertEquals(20, puppy.getHunger());
        assertEquals(80, puppy.getHappiness());
        assertEquals(100, puppy.getEnergy());
    }

    @Test
    void toStringIncludesNameAndSpecies() {
        String description = new PuppyCreature("Rex").toString();
        assertEquals(true, description.contains("Rex"));
        assertEquals(true, description.contains("Puppy"));
    }
}
