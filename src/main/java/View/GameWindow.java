package View;

import java.awt.*;
import javax.swing.*;

import Model.*;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	private BoardPanel boardPanel;
	private ScorePanel scorePanel;
	private int ai;

	public GameWindow(int size, int ai) {
		setTitle("Timbiriche");
		setSize(550, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.ai = ai;

		JLabel headerLabel = new JLabel("TIMBIRICHE", JLabel.CENTER);
		headerLabel.setFont(new Font("Kai", Font.BOLD, 32));
		
		boardPanel = new BoardPanel(size, ai);
		boardPanel.setPreferredSize(new Dimension(600, 600));

		scorePanel = new ScorePanel(boardPanel, ai);

		getContentPane().add(headerLabel, BorderLayout.PAGE_START);
		getContentPane().add(boardPanel, BorderLayout.CENTER);
		getContentPane().add(scorePanel, BorderLayout.PAGE_END);

		setVisible(true);
	}
	
	public void paintEdge(Edge edge) {
		boardPanel.paintEdge(edge);
		return;
	}
	
	public Edge getEdge() {
		return boardPanel.getEdge();
	}
	
	public boolean nodesSelected() {
		return boardPanel.nodesSelected();
	}
	
	public void setTurnText(int turn) {
		scorePanel.setTurnText(turn);
	}
	
	public void setScoreP1(int score) {
		scorePanel.setScoreTextP1(score);
	}
	
	public void setScoreP2(int score) {
		scorePanel.setScoreTextP2(score);
	}
	
	public void endGame(int winner) {
		scorePanel.setEndGameText(winner);
		if(this.ai != 3) {
			scorePanel.removeUndoBtn();
		}		
		this.remove(boardPanel);
		repaint();
		return;
	}
	
}
