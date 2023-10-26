package Model;

public class Boat {

    public static final int NOT_SET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private boolean sunk = false;
    private int length;
    private int coordX;
    private int coordY;
    private int direction ;

    public Boat(int length) {
        this.length = length;
        this.coordX = NOT_SET;
        this.coordY = NOT_SET;
        this.direction = NOT_SET;
    }

    public void setLocation(int x, int y) {
        this.coordX = x;
        this.coordY = y;
    }

    public void setDirection(int direction) { this.direction = direction; }
    public int getDirection() { return this.direction; }
    public boolean isDirectionSet() { return direction != NOT_SET; }
    public int getLength() { return this.length; }
    public int getCoordX() { return this.coordX; }
    public int getCoordY() { return this.coordY; }
}
