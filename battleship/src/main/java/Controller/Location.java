package Controller;

public class Location {
    public static final int UNGUESSED = 0;
    public static final int HIT = 1;
    public static final int MISSED = 2;

    private boolean hasShip;
    private int status;
    private int lengthOfShip;
    private int directionOfShip;

    public Location() {
        this.status = 0;
        this.hasShip = false;
        this.lengthOfShip = -1;
        this.directionOfShip = -1;
    }

    public boolean checkHit() {
        return status == HIT;
    }

    public boolean checkMiss() {
        return status == MISSED;
    }

    public boolean isUnguessed() {
        return status == UNGUESSED;
    }

    public void markHit() {
        setStatus(HIT);
    }

    public void markMiss() {
        setStatus(MISSED);
    }

    public boolean hasShip() {
        return hasShip;
    }

    public void setShip(Boolean bool) {
        this.hasShip = bool;
    }

    public void setStatus(int status)
    {
        if(status == HIT || status == MISSED)
            this.status = status;
        else
            this.status = UNGUESSED;
    }

    public int getStatus() {
        return status;
    }

    public int getLengthOfShip() {
        return lengthOfShip;
    }

    public void setLengthOfShip(int val) {
        lengthOfShip = val;
    }

    public int getDirectionOfShip() {
        return directionOfShip;
    }

    public void setDirectionOfShip(int val) {
        directionOfShip = val;
    }
}
