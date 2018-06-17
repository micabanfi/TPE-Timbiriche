package View;

import java.awt.*;
import javax.swing.*;

import Model.*;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	private LinesComponent linesComp;

	public int[][] board;
	public int n;

	public GameWindow(int size) {
		setTitle("Timbiriche");
		setSize(700, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel headerLabel = new JLabel("TIMBIRICHE", JLabel.CENTER);
		headerLabel.setFont(new Font("Kai", Font.BOLD, 32));
		
		linesComp = new LinesComponent(size);

		JPanel bottomPanel = new JPanel();
		JButton undoButton = new JButton("Undo");
		bottomPanel.add(undoButton);

		getContentPane().add(headerLabel, BorderLayout.PAGE_START);
		getContentPane().add(linesComp, BorderLayout.CENTER);
		getContentPane().add(bottomPanel, BorderLayout.PAGE_END);

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
	
}
