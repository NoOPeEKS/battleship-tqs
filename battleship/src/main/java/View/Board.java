package View;

import Controller.Location;
import Model.Boat;

public class Board {

    private Location[][] board;
    private int points;
    private static final int NUM_ROWS = 10;
    private static final int NUM_COLS = 10;

    public Board() {
        board = new Location[NUM_ROWS][NUM_COLS];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                Location location = new Location();
                board[i][j] = location;
            }
        }
    }

    public int getNumrows() { return NUM_ROWS; }
    public int getNumCols() { return NUM_COLS; }
    public Location get(int row, int col) { return board[row][col]; }
    public int getPoints() { return points; }
    public void markHit(int row, int col) {
        board[row][col].markHit();
        points++;
    }

    public void markMiss(int row, int col) { board[row][col].markMiss(); }
    public boolean alreadyGuessed(int row, int col) { return !board[row][col].isUnguessed(); }
    public int getStatus(int row, int col) { return board[row][col].getStatus(); }
    public boolean hasLost() {
        if(points == 18)
            return true;
        else
            return false;
    }

    public void addBoat(Boat b) {
        int row = b.getCoordY();
        int col = b.getCoordX();
        int length = b.getLength();
        int direction = b.getDirection();

        if(direction == 0) {
            for(int i = col; i<col+length; i++){
                if(i<= NUM_COLS) {
                    setShip(row, i, true);
                    setLengthOfBoat(row, i , length);
                    setDirectionOfBoat(row, i, direction);
                }
            }
        }
        if(direction == 1) {
            for(int i = row; i < row+length; i++) {
                if(i<=NUM_ROWS) {
                    setShip(i,col,true);
                    setLengthOfBoat(i, col, length);
                    setDirectionOfBoat(i, col, direction);
                }
            }
        }
    }

    public void setShip(int row, int col, boolean val) { board[row][col].setShip(val);}
    public void setLengthOfBoat(int row, int col, int length){ board[row][col].setShipLength(length); }
    public void setDirectionOfBoat(int row, int col, int dir){ board[row][col].setShipDirection(dir); }
    public boolean hasShip(int row, int col)
    {
        return board[row][col].hasShip();
    }

    protected void setPoints(int points) { this.points = points; }

    public void printStatus() {

        printHeader();

        for (int i = 0; i < NUM_ROWS; i++) {
            System.out.print(switchIntToChar(i) + "\t");
            for (int j = 0; j < NUM_COLS; j++) {

                if (board[i][j].isUnguessed())
                    System.out.print("- ");
                else if (board[i][j].checkMiss())
                    System.out.print("O ");
                else if (board[i][j].checkHit())
                    System.out.print("X ");
            }
            System.out.println();
        }

    }
    public void printBoats() {

        printHeader();

        for (int i = 0; i < NUM_ROWS; i++) {
            System.out.print(switchIntToChar(i) + "\t");

            for (int j = 0; j < NUM_COLS; j++) {

                if (board[i][j].hasShip()) {
                    if (board[i][j].getShipLength() == 3) {
                        System.out.print("T ");
                    }
                    else if (board[i][j].getShipLength() == 4) {
                        System.out.print("Q ");
                    }
                    else if (board[i][j].getShipLength() == 5) {
                        System.out.print("M ");
                    }
                    else if (board[i][j].getShipLength() == 6) {
                        System.out.print("S ");
                    }
                }
                else if (!(board[i][j].hasShip())) {
                    System.out.print("- ");
                }

            }
            System.out.println();
        }

    }

    public void printMethod() {

        printHeader();

        for (int i = 0; i < NUM_ROWS; i++) {
            System.out.print(switchIntToChar(i) + "\t");

            for (int j = 0; j < NUM_COLS; j++) {
                if (board[i][j].checkHit())
                    System.out.print("X ");
                else if (board[i][j].hasShip()) {
                    if (board[i][j].getShipLength() == 3)
                    {
                        System.out.print("T ");
                    }
                    else if (board[i][j].getShipLength() == 4)
                    {
                        System.out.print("Q ");
                    }
                    else if (board[i][j].getShipLength() == 5)
                    {
                        System.out.print("M ");
                    }
                    else if (board[i][j].getShipLength() == 6)
                    {
                        System.out.print("S ");
                    }
                }
                else if (!(board[i][j].hasShip())) {
                    System.out.print("- ");
                }

            }
            System.out.println("\t");
        }
        System.out.println();
    }

    public char switchIntToChar(int x){
        char res;

        switch (x) {
            case 0:
                res = 'A';
                break;
            case 1:
                res = 'B';
                break;
            case 2:
                res = 'C';
                break;
            case 3:
                res = 'D';
                break;
            case 4:
                res = 'E';
                break;
            case 5:
                res = 'F';
                break;
            case 6:
                res = 'G';
                break;
            case 7:
                res = 'H';
                break;
            case 8:
                res = 'I';
                break;
            case 9:
                res = 'J';
                break;
            default:
                return 'Z';
        }

        return res;
    }

    private void printHeader() {
        System.out.print("\t");
        for (int i = 0; i < NUM_COLS; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
