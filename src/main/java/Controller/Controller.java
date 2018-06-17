package Controller;

import Model.Model;
import View.GameWindow;

public class Controller {
    public static void main(String[] args) {
        Model gameModel=new Model(Integer.parseInt(args[1]),Integer.parseInt(args[3]),args[5],Integer.parseInt(args[7]),args[9]);
        GameWindow window=new GameWindow(Integer.parseInt(args[1]), Integer.parseInt(args[3]));
        gameModel.printModelInfo();
        while(!gameModel.isOver()) {
        	window.setTurnText(gameModel.getTurn());
            if(gameModel.play() == null) {//human player retorna null jugando
                gameModel.addMove(window.getEdge());
               	
                
            }
            else {//le toca a la pc y va a retornar un EDGE
                /*window.paintEdge(gameModel.play())*/
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
