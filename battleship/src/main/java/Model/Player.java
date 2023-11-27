package Model;
import View.Board;
import java.util.ArrayList;

public class Player {
  private static final int[] BOAT_LENGTHS = {3, 4, 5, 6};
  private static final int NUM_BOATS = 4;
  private String name;

  private Board boardPlayer;
  private Board boardOpp;

  private ArrayList<Boat> boats;
  private boolean winner;

  public Player(String name) {
    boats = new ArrayList<Boat>();
    for(int i=0; i<NUM_BOATS; i++){
      Boat boatAux = new Boat(BOAT_LENGTHS[i]);
      boats.add(boatAux);
    }

    this.name = name;
    winner = false;
    boardPlayer = new Board();
    boardOpp = new Board();
  }

  public String getName() { return name; }

  public boolean hasWon() { return winner; }

  public int numBoatsLeftToSet(){
    int counter = NUM_BOATS;

    for (Boat b: boats)
    {
      if (b.isLocationSet() && b.isDirectionSet())
        counter--;
    }
    return counter;
  }

  public ArrayList<Boat> getBoats() { return boats; }
  public Board getPlayerBoard() { return boardPlayer; }
  public Board getOppBoard() { return boardOpp; }

  protected void setPlayerBoard(Board b){ boardPlayer = b; }
  
  //columna=x, fila=y
  public void chooseBoatPosition(Boat b, int row, int col, int direction) {
      b.setLocation(col, row); 
      b.setDirection(direction);
      boardPlayer.addBoat(b);
  }
}
