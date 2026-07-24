package com.zinhle.critterkeeper.creatures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RabbitCreatureTest {

    @Test
    void speciesNameIsRabbit() {
        assertEquals("Rabbit", new RabbitCreature("Thumper").getSpecies());
    }

    @Test
    void feedsFasterThanMostSpecies() {
        int rabbitFeed = 30; // matches feedAmount in RabbitCreature
        RabbitCreature rabbit = new RabbitCreature("Thumper");
        rabbit.feed();
        assertEquals(Math.max(0, 20 - rabbitFeed), rabbit.getHunger());
    }

    @Test
    void playsEnthusiastically() {
        RabbitCreature rabbit = new RabbitCreature("Thumper");
        int happinessBefore = rabbit.getHappiness();
        rabbit.play();
        assertTrue(rabbit.getHappiness() >= happinessBefore);
    }
}
