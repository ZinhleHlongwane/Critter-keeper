package com.zinhle.critterkeeper.world;

import org.junit.jupiter.api.Test;
import com.zinhle.critterkeeper.creatures.PuppyCreature;
import com.zinhle.critterkeeper.creatures.RabbitCreature;
import com.zinhle.critterkeeper.hazards.MudPuddle;
import com.zinhle.critterkeeper.hazards.Pond;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ShelterTest {

    @Test
    void keeperWithoutCreatureCannotMove() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        String reason = shelter.tryMove(keeper, 1);
        assertEquals(Common.REASON_NO_CREATURE, reason);
    }

    @Test
    void moveIsRejectedAtTheEdge() {
        Shelter shelter = new Shelter(2, 2);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new PuppyCreature("Rex"));
        keeper.setPosition(new Position(1, 1));
        keeper.setFacing(Direction.NORTH);
        String reason = shelter.tryMove(keeper, 5);
        assertEquals(Common.REASON_AT_EDGE, reason);
    }

    @Test
    void pondBlocksMovementForMostCreatures() {
        Shelter shelter = new Shelter(10, 10);
        shelter.addHazard(new Pond(new Position(0, 1)));
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new PuppyCreature("Rex"));
        keeper.setPosition(new Position(0, 0));
        keeper.setFacing(Direction.NORTH);
        String reason = shelter.tryMove(keeper, 1);
        assertEquals(Common.REASON_BLOCKED, reason);
    }

    @Test
    void rabbitCanHopOverMudPuddle() {
        Shelter shelter = new Shelter(10, 10);
        shelter.addHazard(new MudPuddle(new Position(0, 1)));
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new RabbitCreature("Thumper"));
        keeper.setPosition(new Position(0, 0));
        keeper.setFacing(Direction.NORTH);
        String reason = shelter.tryMove(keeper, 1);
        assertNull(reason);
        assertEquals(new Position(0, 1), keeper.getPosition());
    }

    @Test
    void successfulMoveUpdatesPositionAndCosts() {
        Shelter shelter = new Shelter(10, 10);
        Keeper keeper = shelter.addKeeper("Zinhle");
        keeper.adopt(new PuppyCreature("Rex"));
        keeper.setPosition(new Position(0, 0));
        keeper.setFacing(Direction.EAST);
        int energyBefore = keeper.getCreature().getEnergy();
        String reason = shelter.tryMove(keeper, 1);
        assertNull(reason);
        assertEquals(new Position(1, 0), keeper.getPosition());
        assertEquals(energyBefore - keeper.getCreature().moveEnergyCost(), keeper.getCreature().getEnergy());
    }

    @Test
    void hazardAtFindsTheRightHazard() {
        Shelter shelter = new Shelter(10, 10);
        Pond pond = new Pond(new Position(3, 3));
        shelter.addHazard(pond);
        assertNotNull(shelter.hazardAt(new Position(3, 3)));
        assertNull(shelter.hazardAt(new Position(4, 4)));
    }
}
