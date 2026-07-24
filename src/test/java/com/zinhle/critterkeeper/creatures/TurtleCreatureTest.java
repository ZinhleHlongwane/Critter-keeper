package com.zinhle.critterkeeper.creatures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TurtleCreatureTest {

    @Test
    void speciesNameIsTurtle() {
        assertEquals("Turtle", new TurtleCreature("Shelly").getSpecies());
    }

    @Test
    void isTheCheapestMoverOfAllSpecies() {
        int turtleCost = new TurtleCreature("Shelly").moveEnergyCost();
        assertTrue(turtleCost <= new PuppyCreature("Rex").moveEnergyCost());
        assertTrue(turtleCost <= new DragonlingCreature("Spark").moveEnergyCost());
        assertTrue(turtleCost <= new RabbitCreature("Thumper").moveEnergyCost());
    }

    @Test
    void barelyGetsHungryFromMoving() {
        TurtleCreature turtle = new TurtleCreature("Shelly");
        int hungerBefore = turtle.getHunger();
        turtle.spendEnergyForMove();
        assertEquals(hungerBefore + 1, turtle.getHunger());
    }
}
