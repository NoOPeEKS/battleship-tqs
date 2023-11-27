package Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    @Test
    void testPlayerConstructor(){
        Player marc = new Player("Marc");

        assertFalse(marc.hasWon());
        assertEquals(4, marc.getBoats().size());
        assertNotNull(marc.getPlayerBoard());
        assertNotNull(marc.getOppBoard());
        assertSame("Marc", marc.getName());
        ArrayList<Boat> boats= marc.getBoats();
        Boat boat = (Boat) boats.get(0);
        assertEquals(3, boat.getLength());
    }

    @Test
    void chooseBoatPosition() {
        Player marc = new Player("Marc");
        //pairwise de 100 combinacions
        marc.chooseBoatPosition(marc.getBoats().get(0), 0, 0, 0);
        marc.chooseBoatPosition(marc.getBoats().get(1), 4, 0, 0);
        marc.chooseBoatPosition(marc.getBoats().get(2), 6, 0, 0);
        marc.chooseBoatPosition(marc.getBoats().get(3), 9, 0, 0);

        assertEquals(0, marc.getBoats().get(0).getDirection());
        assertEquals(0, marc.getBoats().get(0).getCoordX());
        assertEquals(0, marc.getBoats().get(0).getCoordY());

        assertEquals(0, marc.getBoats().get(1).getDirection());
        assertEquals(0, marc.getBoats().get(1).getCoordX());
        assertEquals(4, marc.getBoats().get(1).getCoordY());

        assertEquals(0, marc.getBoats().get(2).getDirection());
        assertEquals(0, marc.getBoats().get(2).getCoordX());
        assertEquals(6, marc.getBoats().get(2).getCoordY());

        assertEquals(0, marc.getBoats().get(3).getDirection());
        assertEquals(0, marc.getBoats().get(3).getCoordX());
        assertEquals(9, marc.getBoats().get(3).getCoordY());

        assertTrue(marc.getPlayerBoard().hasShip(0,0));
        assertTrue(marc.getPlayerBoard().hasShip(4,0));
        assertTrue(marc.getPlayerBoard().hasShip(6,0));
        assertTrue(marc.getPlayerBoard().hasShip(9,0));
    }

    //Caixa negra
    @Test
    void numBoatsLeftToSet(){
        Player player1 = new Player("Marc");
        Player player2 = new Player("Arnau");

        for (Boat boat : player2.getBoats()) {
            player2.chooseBoatPosition(boat,12,15,3);
            assertEquals(4,player1.numBoatsLeftToSet());
        }

        assertNotEquals(5,player1.numBoatsLeftToSet());
        assertEquals(4,player1.numBoatsLeftToSet());
        player1.chooseBoatPosition(player1.getBoats().get(0), 0, 0, 0);
        assertEquals(3, player1.numBoatsLeftToSet());
        player1.chooseBoatPosition(player1.getBoats().get(1), 1, 0, 0);
        assertEquals(2, player1.numBoatsLeftToSet());
        player1.chooseBoatPosition(player1.getBoats().get(2), 2, 0, 0);
        assertEquals(1, player1.numBoatsLeftToSet());
        player1.chooseBoatPosition(player1.getBoats().get(3), 3, 0, 0);
        assertEquals(0, player1.numBoatsLeftToSet());
        assertNotEquals(-1,player1.numBoatsLeftToSet());


    }
}
