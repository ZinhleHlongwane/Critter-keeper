package com.zinhle.critterkeeper.creatures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CreatureFactoryTest {

    @Test
    void createsKnownSpecies() {
        assertInstanceOf(PuppyCreature.class, CreatureFactory.create("puppy", "Rex"));
        assertInstanceOf(KittenCreature.class, CreatureFactory.create("KITTEN", "Whiskers"));
        assertInstanceOf(DragonlingCreature.class, CreatureFactory.create("dragonling", "Spark"));
        assertInstanceOf(RabbitCreature.class, CreatureFactory.create("rabbit", "Thumper"));
        assertInstanceOf(TurtleCreature.class, CreatureFactory.create("turtle", "Shelly"));
    }

    @Test
    void returnsNullForUnknownSpecies() {
        assertNull(CreatureFactory.create("unicorn", "Sparkle"));
        assertNull(CreatureFactory.create(null, "Sparkle"));
    }

    @Test
    void isKnownSpeciesMatchesCreation() {
        assertTrue(CreatureFactory.isKnownSpecies("puppy"));
        assertFalse(CreatureFactory.isKnownSpecies("unicorn"));
    }
}
