package Controller;


public class Location {

  public static final int UNGUESSED = 0;
  public static final int MISSED = 2;
  public static final int HIT = 1;


  private boolean hasShip;
  private int shipLength;
  private int shipDirection;
  private int status;


  public Location() {
    status = 0;
    hasShip = false;
    shipLength = -1;
    shipDirection = -1;
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

  public int getShipDirection() {
    return shipDirection;
  }

  public void setShipDirection(int val) {
    shipDirection = val;
  }

  public int getShipLength() {
    return shipLength;
  }

  public void setShipLength(int val) {
    shipLength = val;
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

  public void setStatus(int status) {
    if (status == HIT || status == MISSED)
      this.status = status;
    else
      this.status = UNGUESSED;
  }

  public int getStatus() {
    return status;
  }

}
