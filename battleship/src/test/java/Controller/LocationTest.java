package Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationTest {
    private Location location = null;

    @Test
    void checkHit() {
        location = new Location();

        assertFalse(location.checkHit());
        assertEquals(0, location.getStatus());
        location.markHit();
        assertTrue(location.checkHit());
        assertEquals(1, location.getStatus());
        assertFalse(location.getStatus() != 1);
    }

    @Test
    void testLocationConstructor(){
        location = new Location();

        assertEquals(0, location.getStatus());
        assertFalse(location.hasShip());
        assertEquals(-1, location.getShipLength());
        assertEquals(-1, location.getShipDirection());
    }

    @Test
    void isUnguessed() {
        location = new Location();

        assertTrue(location.isUnguessed());
        assertEquals(0, location.getStatus());
        location.markMiss();
        assertFalse(location.isUnguessed());
        location.markHit();
        assertFalse(location.isUnguessed());
        assertFalse(location.isUnguessed());
    }

    @Test
    void checkMiss() {
        location = new Location();

        assertFalse(location.checkMiss());
        assertEquals(0, location.getStatus());
        location.markMiss();
        assertTrue(location.checkMiss());
        assertEquals(2, location.getStatus());
        assertFalse(location.getStatus() != 2);
    }

    @Test
    void setStatus(){
        location = new Location();

        location.setStatus(Location.HIT);
        assertEquals(Location.HIT,location.getStatus());
        location.setStatus(Location.MISSED);
        assertEquals(Location.MISSED,location.getStatus());
        location.setStatus(3);
        assertEquals(Location.UNGUESSED,location.getStatus());
        location.setStatus(-1);
        assertEquals(Location.UNGUESSED,location.getStatus());

    }

    @Test
    void hasShip() {
        location = new Location();

        assertFalse(location.hasShip());
        location.setShip(true);
        assertTrue(location.hasShip());
        location.setShip(false);
        assertFalse(location.hasShip());
    }
}
