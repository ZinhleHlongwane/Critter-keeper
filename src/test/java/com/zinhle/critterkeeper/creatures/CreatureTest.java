package com.zinhle.critterkeeper.creatures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreatureTest {

    @Test
    void feedingReducesHungerButNotBelowZero() {
        Creature puppy = new PuppyCreature("Rex");
        for (int i = 0; i < 10; i++) {
            puppy.feed();
        }
        assertEquals(0, puppy.getHunger());
    }

    @Test
    void playingIncreasesHappinessAndCostsEnergy() {
        Creature kitten = new KittenCreature("Whiskers");
        int happinessBefore = kitten.getHappiness();
        int energyBefore = kitten.getEnergy();
        kitten.play();
        assertTrue(kitten.getHappiness() >= happinessBefore);
        assertTrue(kitten.getEnergy() < energyBefore);
    }

    @Test
    void restingRestoresEnergyButNotAboveMax() {
        Creature turtle = new TurtleCreature("Shelly");
        for (int i = 0; i < 20; i++) {
            turtle.rest();
        }
        assertEquals(Creature.MAX_STAT, turtle.getEnergy());
    }

    @Test
    void spendEnergyForMoveDrainsEnergyAndAddsHunger() {
        Creature dragonling = new DragonlingCreature("Spark");
        int energyBefore = dragonling.getEnergy();
        int hungerBefore = dragonling.getHunger();
        dragonling.spendEnergyForMove();
        assertEquals(energyBefore - dragonling.moveEnergyCost(), dragonling.getEnergy());
        assertEquals(hungerBefore + 1, dragonling.getHunger());
    }

    @Test
    void creatureIsAsleepWhenEnergyReachesZero() {
        Creature rabbit = new RabbitCreature("Thumper");
        while (rabbit.getEnergy() > 0) {
            rabbit.spendEnergyForMove();
        }
        assertTrue(rabbit.isAsleep());
        assertFalse(new RabbitCreature("Fresh").isAsleep());
    }
}
