package View;

import java.awt.*;
import javax.swing.*;

import Model.*;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	private LinesComponent linesComp;
	private ScorePanel scorePanel;

	public GameWindow(int size, int ai) {
		setTitle("Timbiriche");
		setSize(550, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel headerLabel = new JLabel("TIMBIRICHE", JLabel.CENTER);
		headerLabel.setFont(new Font("Kai", Font.BOLD, 32));
		
		linesComp = new LinesComponent(size);
		linesComp.setPreferredSize(new Dimension(600, 600));

		scorePanel = new ScorePanel(ai);

		getContentPane().add(headerLabel, BorderLayout.PAGE_START);
		getContentPane().add(linesComp, BorderLayout.CENTER);
		getContentPane().add(scorePanel, BorderLayout.PAGE_END);

		setVisible(true);
	}
	
	public void paintEdge(Edge edge) {
		linesComp.paintEdge(edge);
		return;
	}
	
	public Edge getEdge() {
		return linesComp.getEdge();
	}
	
	public boolean nodesSelected() {
		return linesComp.nodesSelected();
	}
	
	public void setTurnText(int turn) {
		scorePanel.setTurnText(turn);
	}
	
}
