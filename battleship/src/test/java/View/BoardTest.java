package View;

import Controller.Location;
import Model.Boat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    @Test
    void testBoardConstructor(){
        Board board = new Board();

        for (int row = 0; row < board.getNumrows(); row++){
            for (int col = 0; col < board.getNumCols(); col++){
                assertEquals(0, board.getStatus(row, col));
            }
        }
    }

    @Test
    void markHit(){
        Board board = new Board();
        assertEquals(0, board.getPoints());
        Location loc = board.get(1,1);
        assertNotEquals(Location.HIT, loc.getStatus());
        board.markHit(1,1);

        assertEquals(1, board.getPoints());
        Location locHitted = board.get(1,1);
        assertEquals(Location.HIT, locHitted.getStatus());
        Location locNoHit = board.get(1,2);
        assertNotEquals(Location.HIT, locNoHit.getStatus());
    }

    @Test
    void markMiss(){
        Board board = new Board();
        Location loc = board.get(1,1);
        assertNotEquals(Location.MISSED, loc.getStatus());
        board.markMiss(1,1);

        Location locMissed = board.get(1,1);
        assertEquals(Location.MISSED, locMissed.getStatus());
        Location locNoHit = board.get(1,2);
        assertNotEquals(Location.MISSED, locNoHit.getStatus());
    }

    @Test
    void alreadyGuessed(){
        Board board = new Board();
        assertFalse(board.alreadyGuessed(1,1));

        board.markHit(1,1);
        assertTrue(board.alreadyGuessed(1,1));
    }

    @Test
    void hasLost(){
        Board board = new Board();
        assertFalse(board.hasLost());
        for (int i=0;i<9;i++)
            board.markHit(i,0);
        for (int i=0;i<9;i++)
            board.markHit(i,5);

        assertTrue(board.hasLost());
    }


    @Test
    void addBoat(){
        Board board = new Board();
        Boat boats = new Boat(3);
        boats.setDirection(Boat.VERTICAL);
        boats.setLocation(0,0);

        assertFalse(board.hasShip(0,0));
        assertFalse(board.hasShip(1,0));
        assertFalse(board.hasShip(2,0));
        assertFalse(board.hasShip(3,0));
        assertFalse(board.hasShip(0,6));

        board.addBoat(boats);

        assertTrue(board.hasShip(0,0));
        assertTrue(board.hasShip(1,0));
        assertTrue(board.hasShip(2,0));
        assertFalse(board.hasShip(3,0));
        assertFalse(board.hasShip(0,6));

        Board board1 = new Board();
        Boat boat1 = new Boat(4);
        boat1.setDirection(Boat.HORIZONTAL);
        boat1.setLocation(6,9);

        assertFalse(board1.hasShip(9,6));
        assertFalse(board1.hasShip(9,7));
        assertFalse(board1.hasShip(9,8));
        assertFalse(board1.hasShip(9,9));
        assertFalse(board1.hasShip(9,5));

        board1.addBoat(boat1);

        assertTrue(board1.hasShip(9,6));
        assertTrue(board1.hasShip(9,7));
        assertTrue(board1.hasShip(9,8));
        assertTrue(board1.hasShip(9,9));
        assertFalse(board1.hasShip(9,5));
    }

    @Test
    void switchIntToChar(){
        Board board = new Board();

        char[] letter = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        for (int i = 0; i < 10; i++){
            char charact = board.switchIntToChar(i);
            assertEquals(charact, letter[i]);
        }
        char c = board.switchIntToChar(12);
        assertEquals('Z', c);
    }
}
