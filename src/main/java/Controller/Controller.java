package Controller;

import Model.*;
import View.GameWindow;

import java.io.FileNotFoundException;

public class Controller {
    public static void main(String[] args) {
        Model gameModel = new Model(Integer.parseInt(args[1]), Integer.parseInt(args[3]),args[5],
        		Integer.parseInt(args[7]), args[9]);
        GameWindow window = new GameWindow(Integer.parseInt(args[1]),Integer.parseInt(args[3]));
        
        while(!gameModel.isOver()){
        	window.setTurnText(gameModel.getTurn());
        	// Game waits for Player to input move or undo move
            if(gameModel.isHumanTurn()){
                Edge edge = window.getEdge();
                if(edge != null) {
                	gameModel.addMove(edge);
                } else {
                	window.undoMoves(gameModel.undo());
                }
            } else { // AI chooses a move
                Edge e = gameModel.play();
                gameModel.addMove(e);
                window.paintEdge(e);
            }
            window.setScoreP1(gameModel.getScoreP1());
            window.setScoreP2(gameModel.getScoreP2());
        }
        
        window.endGame(gameModel.over());
    }
}
