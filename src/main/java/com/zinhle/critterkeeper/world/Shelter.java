package com.zinhle.critterkeeper.world;

import com.zinhle.critterkeeper.creatures.RabbitCreature;
import com.zinhle.critterkeeper.hazards.Hazard;
import com.zinhle.critterkeeper.hazards.MudPuddle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Shelter is the shared grid all keepers move around in. It owns the
 * hazards laid out in the yard and every keeper currently visiting.
 */
public class Shelter {

    private final int width;
    private final int height;
    private final List<Hazard> hazards = new ArrayList<>();
    private final Map<String, Keeper> keepers = new HashMap<>();

    public Shelter(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addHazard(Hazard hazard) {
        hazards.add(hazard);
    }

    public List<Hazard> getHazards() {
        return hazards;
    }

    public boolean isInBounds(Position position) {
        int half = width / 2;
        int halfH = height / 2;
        return position.getX() >= -half && position.getX() <= half
                && position.getY() >= -halfH && position.getY() <= halfH;
    }

    public Hazard hazardAt(Position position) {
        for (Hazard hazard : hazards) {
            if (hazard.getPosition().equals(position)) {
                return hazard;
            }
        }
        return null;
    }

    public Keeper addKeeper(String name) {
        Keeper keeper = new Keeper(name, findFreeStartingSquare(), Direction.NORTH);
        keepers.put(name, keeper);
        return keeper;
    }

    public Keeper getKeeper(String name) {
        return keepers.get(name);
    }

    public void removeKeeper(String name) {
        keepers.remove(name);
    }

    public List<Keeper> allKeepers() {
        return new ArrayList<>(keepers.values());
    }

    /**
     * Attempts to move a keeper's creature one step forward. Returns null on
     * success, or a reason string describing why the move was rejected.
     */
    public String tryMove(Keeper keeper, int steps) {
        if (!keeper.hasCreature()) {
            return Common.REASON_NO_CREATURE;
        }
        Position next = keeper.getPosition().step(keeper.getFacing(), steps);
        if (!isInBounds(next)) {
            return Common.REASON_AT_EDGE;
        }
        Hazard hazard = hazardAt(next);
        if (hazard != null) {
            boolean isRabbitSkippingPuddle = keeper.getCreature() instanceof RabbitCreature
                    && hazard instanceof MudPuddle;
            if (hazard.blocksMovement() && !isRabbitSkippingPuddle) {
                return Common.REASON_BLOCKED;
            }
        }
        keeper.setPosition(next);
        keeper.getCreature().spendEnergyForMove();
        return null;
    }

    private Position findFreeStartingSquare() {
        // Simple deterministic placement: first free square scanning from the
        // bottom-left corner of the grid.
        int half = width / 2;
        int halfH = height / 2;
        for (int y = -halfH; y <= halfH; y++) {
            for (int x = -half; x <= half; x++) {
                Position candidate = new Position(x, y);
                if (hazardAt(candidate) == null && !occupiedByKeeper(candidate)) {
                    return candidate;
                }
            }
        }
        return new Position(0, 0);
    }

    private boolean occupiedByKeeper(Position position) {
        for (Keeper keeper : keepers.values()) {
            if (keeper.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }
}
