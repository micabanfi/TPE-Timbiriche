package Controller;

import java.io.FileNotFoundException;

import Model.Edge;
import Model.Model;
import View.GameWindow;

public class Controller {
    public static void main(String[] args) {
        Model gameModel = new Model(Integer.parseInt(args[1]), Integer.parseInt(args[3]), args[5],
        		Integer.parseInt(args[7]), args[9]);
        GameWindow window = new GameWindow(Integer.parseInt(args[1]),Integer.parseInt(args[3]), args[5]);
        
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
            if(window.generateDot()) {
        		try {
					gameModel.dotCreation();
					window.setGenerateDot(false);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
        	}
            window.setScoreP1(gameModel.getScoreP1());
            window.setScoreP2(gameModel.getScoreP2());
        }
        
        window.endGame(gameModel.over());
    }
}
