package Controller;

import Model.Boat;
import Model.Player;

import java.util.Scanner;

public class Game {

    public static Scanner reader = new Scanner(System.in);
    private static Player p1;
    private static Player p2;

    public Game(){
        p1=new Player("player1");
        p2=new Player("player2");
    }

    // Simulem la partida entre Jugador 1 i Jugador 2.
    // Fem servir tots els mètodes de la classe per simular la partida i els paràmetres
    // es demanen com a input a la consola i es passen als mètodes que els requereixen.

    public static void main(String[] args) {
        Game currentGame = new Game();

        System.out.println("\nSetup del Jugador 1: ");

        while (p1.numBoatsLeftToSet() > 0){
            for (Boat boat: p1.getBoats()){
                int row = -1;
                int col = -1;
                int dir = -1;
                boolean continua = false;
                do {
                    p1.getPlayerBoard().printBoats();
                    System.out.print("Escriu fila (A-J): ");
                    String userInputRow = reader.next();
                    userInputRow = userInputRow.toUpperCase();
                    row = convertLetterToInt(userInputRow);


                    System.out.print("Escriu columna (0-9): ");
                    col = reader.nextInt();

                    System.out.print("Escriu la direcció (0 és Horizontal i 1 és Vertical): ");
                    dir = reader.nextInt();

                    if(validParams(row,col) && !hasErrors(row,col,dir,p1,boat) && dir!=-1)
                        continua=true;

                }while (!continua);

                setup(p1,row, col, dir, boat);

            }
        }

        System.out.println("\nSetup del Jugador 2: ");

        while (p2.numBoatsLeftToSet() > 0){
            for (Boat boat: p2.getBoats()){
                int row = -1;
                int col = -1;
                int dir = -1;
                boolean continua=false;
                do {
                    p2.getPlayerBoard().printBoats();
                    System.out.print("Escriu la fila (A-J): ");
                    String userInputRow = reader.next();
                    userInputRow = userInputRow.toUpperCase();
                    row = convertLetterToInt(userInputRow);


                    System.out.print("Escriu la columna (0-9): ");
                    col = reader.nextInt();

                    System.out.print("Escriu la direcció (0 és Horitzontal i 1 és Vertical): ");
                    dir = reader.nextInt();

                    if(validParams(row,col) && !hasErrors(row,col,dir,p2,boat) && dir!=-1)
                        continua=true;

                }while (!continua);

                setup(p2, row, col, dir, boat);


            }
        }


        do {
            boolean valid = false;
            int row = 0;
            int col = 0;
            while (!valid) {
                System.out.println("\nEl jugador 1 prova:");
                System.out.println("\nTauler de l'oponent: ");
                p1.getOppBoard().printStatus();

                System.out.print("Escriu la fila (A-J): ");
                String userInputRow = reader.next();
                userInputRow = userInputRow.toUpperCase();
                row = convertLetterToInt(userInputRow);

                System.out.print("Escriu la columna (0-9): ");
                col = reader.nextInt();
                if (row <= 9 && row >= 0 && col >= 0 && col <= 9)
                    valid = true;
            }

            if (askForGuess(p1, p2, row, col)) {
                System.out.print("Tocat a:\t" + "fila=\t" + row + "\tcolumna=\t" + col + "\n");
            } else {
                System.out.print("Fallat a:\t" + "fila=\t" + row + "\tcolumna=\t" + col + "\n");
            }


            valid = false;
            int row2 = 0;
            int col2 = 0;
            while (!valid){
                System.out.println("\nJugador 2 prova:");
                System.out.println("\nTauler de l'oponent: ");
                p2.getOppBoard().printStatus();

                System.out.print("Escriu la fila (A-J): ");
                String userInputRow2 = reader.next();
                userInputRow2 = userInputRow2.toUpperCase();
                row2 = convertLetterToInt(userInputRow2);

                System.out.print("Escriu la columna (0-9): ");
                col2 = reader.nextInt();
                if(row2<=9 && row2>=0 && col2>=0 && col2<=9)
                    valid=true;
            }
            if (askForGuess(p2, p1, row2, col2)){
                System.out.print("Tocat a:\t" + "fila=\t" + row2 + "\tcolumna=\t" + col2 + "\n");
            }
            else{
                System.out.print("Fallat a:\t" + "fila=\t" + row2 + "\tcolumna=\t" + col2 + "\n");
            }

        }while(!p1.getPlayerBoard().hasLost() && !p2.getPlayerBoard().hasLost());

        if(p1.getPlayerBoard().hasLost()){
            System.out.println("El jugador 2 ha guanyat!");
            p2.getOppBoard().printMethod();
        }

        if(p2.getPlayerBoard().hasLost()){
            System.out.println("El jugador 1 ha guanyat!");
            p1.getOppBoard().printMethod();
        }

    }
    public Player getPlayer1(){return p1;}
    public Player getPlayer2(){return p2;}

    // Mètode que recull coordenada[row, col] on ataca el jugador p i comprova
    // si ha tocat o fallat al tauler del jugador
    protected static boolean askForGuess(Player p, Player opp, int row, int col) {
        if(opp.getPlayerBoard().hasShip(row,col))
        {
            p.getOppBoard().markHit(row,col);
            opp.getPlayerBoard().markHit(row,col);
            return true;
        }
        else{
            p.getOppBoard().markMiss(row, col);
            opp.getPlayerBoard().markMiss(row,col);
            return false;
        }
    }

    // Mètode que comprova que la fila i la columna triades pel jugador estiguin
    // dins l'interval definit [0,9] dels eixos del tauler.
    protected static boolean validParams(int row, int col)
    {
        if(col>=0 && col<=9 && row!=-1)
            return true;
        return false; // Si no són vàlids, torna a demanar els paràmetres
    }


    // Mètode que comprova que el vaixell es pugui introduir al tauler.
    // El vaixell introduit no pot estar en cap coordenada on ja
    // hi hagi un altre vaixell ni es pot introduir fora del tauler.
    protected static boolean hasErrors(int row, int col, int dir, Player p, Boat b){
        int length = b.getLength();

        if(dir==0){
            int checker = length+col;
            if(checker>10){
                System.out.println("EL VAIXELL NO HI CAP!");
                return true;
            }
        }
        if (dir == 1) {
            int checker=length+row;
            if(checker>10){
                System.out.println("EL VAIXELL NO HI CAP!");
                return true;
            }
        }

        if(dir==0){
            for(int i=col; i<col+length;i++)
            {
                if(p.getPlayerBoard().hasShip(row,i))
                {
                    System.out.println("JA HI HA UN BARCO");
                    return true;
                }
            }
        }

        if(dir==1){
            for(int i=row; i<row+length;i++)
            {
                if(p.getPlayerBoard().hasShip(i,col))
                {
                    System.out.println("JA HI HA UN BARCO");
                    return true;
                }
            }
        }


        return false;
    }

    // Mètode que col·loca un vaixell a la coordenada[row, col] amb la direcció dir pel jugador p.
    public static void setup(Player p, int row, int col, int dir, Boat b){
        p.chooseBoatPosition(b, row, col, dir);

        System.out.println("Tens " + p.numBoatsLeftToSet() + " vaixells per col·locar.");
    }

    // Mètode per convertir la selecció de la fila d'un jugador i convertir-lo a la integer correcta.
    private static int convertLetterToInt(String input) {
        int toReturn = -1;
        switch (input)
        {
            case "A":   toReturn = 0;
                break;
            case "B":   toReturn = 1;
                break;
            case "C":   toReturn = 2;
                break;
            case "D":   toReturn = 3;
                break;
            case "E":   toReturn = 4;
                break;
            case "F":   toReturn = 5;
                break;
            case "G":   toReturn = 6;
                break;
            case "H":   toReturn = 7;
                break;
            case "I":   toReturn = 8;
                break;
            case "J":   toReturn = 9;
                break;
            default:    toReturn = -1;
                break;
        }

        return toReturn;
    }
}


