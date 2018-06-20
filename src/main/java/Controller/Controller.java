package Controller;

import Model.*;
import View.GameWindow;

import java.io.FileNotFoundException;

public class Controller {
    public static void main(String[] args) {
        Model gameModel = new Model(Integer.parseInt(args[1]), Integer.parseInt(args[3]), args[5],
        		Integer.parseInt(args[7]), args[9]);
        GameWindow window = new GameWindow(Integer.parseInt(args[1]),Integer.parseInt(args[3]), args[5]);
        
        while(!gameModel.isOver()){
        	window.setTurnText(gameModel.getTurn());
        	// Game waits for Player to input move or undo move
            if(gameModel.isHumanTurn()){
                System.out.println("Turno jugador "+gameModel.getTurn());
                Edge edge = window.getEdge();
                if(edge != null) {
                	gameModel.addMove(edge);
                } else {
                	window.undoMoves(gameModel.undo());
                }
                System.out.println("Ya elijio humano");
            } else { // AI chooses a move
                System.out.println("Turno Computadora "+ gameModel.getTurn());
                Edge e = gameModel.play();
                System.out.println("Computer EDGE:"+e.iPosition()+"-"+e.jPosition()+"-"+e.isHorizontal());
                gameModel.addMove(e);
                window.paintEdge(e);
                System.out.println("Ya elijio pc");
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
