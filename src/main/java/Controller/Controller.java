package Controller;

import Model.Edge;
import Model.Model;
import View.GameWindow;

public class Controller {
    public static void main(String[] args) {
        Model gameModel=new Model(Integer.parseInt(args[1]), Integer.parseInt(args[3]), args[5], Integer.parseInt(args[7]), args[9]);
        GameWindow window=new GameWindow(Integer.parseInt(args[1]), Integer.parseInt(args[3]));
        gameModel.printModelInfo();
        
        Edge e=new Edge(0,0,true);
        System.out.println("EDGE="+e.iPosition());
        window.paintEdge(e);
        
        while(!gameModel.isOver()){
        	window.setTurnText(gameModel.getTurn());
            if(gameModel.isHumanTurn()){
                System.out.println("Turno jugador "+gameModel.getTurn());
                gameModel.addMove(window.getEdge());
                System.out.println("Ya elijio humano");
            }
            else{//le toca a la pc y va a retornar un EDGE
                System.out.println("Turno Computadora "+gameModel.getTurn());
                Edge ee=gameModel.play();
                gameModel.addMove(e);
                System.out.println("COMPUTER EDGE:"+e.iPosition());
               window.paintEdge(e);
                System.out.println("Ya elijio pc");

            }
            
            /*window.printP1(gameModel.getScoreP1());
            window.printP2(gameModel.getScoreP2());
*/
        }
        int rta = gameModel.over();
        //window.printScore(rta);
        System.out.println("Ganador: " + rta);
    }
}
