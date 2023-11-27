package Model;

import View.Board;

public class MockPlayer1 extends Player {
    public MockPlayer1(String name) { super(name); }

    public void setMockPlayerBoard(Board opponentBoard) { setPlayerBoard(opponentBoard); }
}
