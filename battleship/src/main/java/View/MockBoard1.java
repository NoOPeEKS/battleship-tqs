package View;

public class MockBoard1 extends Board{

  public MockBoard1(){
    super();

    //Barco de mira 3 en coord[0,0]
    for (int col = 0; col < 2; col++){
      setShip(0, col, true);
    }
    
    //Barco de mida 6 en coord[3,0]
    for (int col = 0; col < 5; col++){
      setShip(3, col, true);
    }
    //Barco de mida 4 en coord[1,0]
    for (int col = 0; col < 3; col++){
      setShip(1, col, true);
    }

    //Barco de mida 5 en coord[2,0]
    for (int col = 0; col < 4; col++){
      setShip(2, col, true);
    }
  }
  
  public void setMockBoardPoints(int p){
    setPoints(p);
  }
}
