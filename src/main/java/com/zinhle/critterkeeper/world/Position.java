package com.zinhle.critterkeeper.world;

import java.util.Objects;

/**
 * An (x, y) coordinate inside the shelter grid.
 */
public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /** Returns a new Position obtained by stepping `steps` times in `direction`. */
    public Position step(Direction direction, int steps) {
        return new Position(x + direction.deltaX() * steps, y + direction.deltaY() * steps);
    }

    public int distanceTo(Position other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
