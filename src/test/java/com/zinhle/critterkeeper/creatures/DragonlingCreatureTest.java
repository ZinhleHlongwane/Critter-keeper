package com.zinhle.critterkeeper.creatures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DragonlingCreatureTest {

    @Test
    void speciesNameIsDragonling() {
        assertEquals("Dragonling", new DragonlingCreature("Spark").getSpecies());
    }

    @Test
    void isTheMostExpensiveMoverOfAllSpecies() {
        int dragonlingCost = new DragonlingCreature("Spark").moveEnergyCost();
        assertTrue(dragonlingCost >= new PuppyCreature("Rex").moveEnergyCost());
        assertTrue(dragonlingCost >= new KittenCreature("Whiskers").moveEnergyCost());
        assertTrue(dragonlingCost >= new RabbitCreature("Thumper").moveEnergyCost());
        assertTrue(dragonlingCost >= new TurtleCreature("Shelly").moveEnergyCost());
    }

    @Test
    void playCostsMoreEnergyThanItGainsInHappinessRelatively() {
        DragonlingCreature dragonling = new DragonlingCreature("Spark");
        int energyBefore = dragonling.getEnergy();
        dragonling.play();
        assertTrue(dragonling.getEnergy() < energyBefore);
    }
}
