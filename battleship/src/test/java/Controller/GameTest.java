package Controller;

import Model.Boat;
import Model.MockPlayer1;
import Model.Player;
import View.Board;
import View.MockBoard1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GameTest {
    @Test
    void askForGuess(){
        Game game1 = new Game();
        Boat boats = new Boat(3);
        boats.setLocation(0,0);
        boats.setDirection(0);  //horizontal
        game1.getPlayer1().getPlayerBoard().addBoat(boats);

        assertTrue(game1.askForGuess(game1.getPlayer2(),game1.getPlayer1(),0,0));
        assertTrue(game1.askForGuess(game1.getPlayer2(),game1.getPlayer1(),0,1));
        assertTrue(game1.askForGuess(game1.getPlayer2(),game1.getPlayer1(),0,2));
        assertFalse(game1.askForGuess(game1.getPlayer2(),game1.getPlayer1(),0,3));
        assertFalse(game1.askForGuess(game1.getPlayer2(),game1.getPlayer1(),1,0));
        assertFalse(game1.askForGuess(game1.getPlayer2(),game1.getPlayer1(),1,1));


        // Mocks de Player y Board para testear Game::askForGuess() y Board::hasLost()
        Player player = new Player("Marc");
        MockPlayer1 oponent = new MockPlayer1("Arnau");
        MockBoard1 mockBoard1 = new MockBoard1();
        mockBoard1.setMockBoardPoints(17);
        oponent.setMockPlayerBoard(mockBoard1);

        assertTrue(game1.askForGuess(player,oponent,0,0));
        assertTrue(oponent.getPlayerBoard().hasLost());

    }

    @Test
    void validParams(){
        //decision coverage+condition coverage
        Game game1 = new Game();
        assertTrue(game1.validParams(0,0));
        assertFalse(game1.validParams(-1,12));
        assertFalse(game1.validParams(4,-2));
        assertFalse(game1.validParams(5,10));
    }

    @Test
    void hasErrors(){
        Player player1 = new Player("Arnau");
        Boat boat0 = new Boat(3);
        Boat boat1 = new Boat(4);
        Boat boat2 = new Boat(6);
        Game game = new Game();


        assertTrue(player1.getBoats().get(0).getLength()==3);
        assertTrue(game.hasErrors(0,8,0,player1,boat0));
        assertTrue(game.hasErrors(8,0,1,player1,boat0));
        assertFalse(game.hasErrors(7,0,1,player1,boat0));
        assertFalse(game.hasErrors(0,7,0,player1,boat0));
        assertTrue(game.hasErrors(0,6,0,player1,boat2));


        Boat boats = new Boat(4);
        boats.setDirection(0); //horitzontal
        boats.setLocation(2,2);
        game.getPlayer1().getPlayerBoard().addBoat(boats);
        assertTrue(game.hasErrors(0,3,1,game.getPlayer1(),boat0));
        assertFalse(game.hasErrors(1,6,1,game.getPlayer1(),boat0));
        assertFalse(game.hasErrors(1,1,1,game.getPlayer1(),boat0));
        assertFalse(game.hasErrors(2,6,0,game.getPlayer1(),boat0));

        boats.setDirection(1); //vertical
        boats.setLocation(3,2);
        game.getPlayer2().getPlayerBoard().addBoat(boats);
        assertFalse(game.hasErrors(3,0,0,game.getPlayer2(),boat0));
        assertTrue(game.hasErrors(5,0,0,game.getPlayer2(),boat1));
        assertFalse(game.hasErrors(6,0,0,game.getPlayer2(),boat1));
        assertTrue(game.hasErrors(3,0,0,game.getPlayer2(),boat1));

    }


    @Test
    void convertLetterToInt(){
        Game game = new Game();
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        for (int i = 0; i < 10; i++){
            int n = game.accessConvertLetterToInt(letters[i]);
            assertEquals(n, i);
        }
        assertEquals(-1, game.accessConvertLetterToInt("Z"));
    }


    @Test
    void setup(){
        Game game = new Game();
        Board board = game.getPlayer1().getPlayerBoard();
        Boat boat = game.getPlayer1().getBoats().get(0);

        assertNotEquals(0, boat.getDirection());
        assertNotEquals(0, boat.getCoordX());
        assertNotEquals(1, boat.getCoordY());
        assertFalse(board.hasShip(1,0));
        assertFalse(board.hasShip(1,1));
        assertFalse(board.hasShip(1,2));
        assertFalse(board.hasShip(1,3));
        assertFalse(board.hasShip(2,0));

        game.setup(game.getPlayer1(),1,0,0,boat);

        assertEquals(0, boat.getDirection()); //horizontal
        assertEquals(0, boat.getCoordX());
        assertEquals(1, boat.getCoordY());
        assertTrue(board.hasShip(1,0));
        assertTrue(board.hasShip(1,1));
        assertTrue(board.hasShip(1,2));
        assertFalse(board.hasShip(1,3));
        assertFalse(board.hasShip(2,0));

    }
}
