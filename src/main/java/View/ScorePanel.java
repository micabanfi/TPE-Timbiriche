package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel {
	private BoardPanel bp;
	private JLabel turnLabel;
	private JPanel scores;
	private JLabel scoreP1;
	private JLabel scoreP2;
	private JPanel btnPanel;
	private JButton undoBtn;
	private JButton dotBtn;
	private int ai;
	private String mode;
	private String player1;
	private String player2;
	private Boolean generateDot = false;
	
	public ScorePanel(BoardPanel bp, int ai, String mode) {
		this.bp = bp;
		this.ai = ai;
		this.mode = mode;
		setLayout(new BorderLayout(0, 10));
		
		turnLabel = new JLabel("", SwingConstants.CENTER);
		turnLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		add(turnLabel, BorderLayout.NORTH);
		
		scores = new JPanel(new GridLayout(2, 1));
		scores.setPreferredSize(new Dimension(this.getWidth(), 50));
		if(ai == 0) {
			player1 = "Player 1: ";
			player2 = "Player 2: ";
		} else if(ai == 1 || ai == 2) {
			player1 = "Player: ";
			player2 = "AI: ";
		} else {
			player1 = "AI 1: ";
			player2 = "AI 2: ";
		}
		scoreP1 = new JLabel(player1 + 0, SwingConstants.CENTER);
		scoreP2 = new JLabel(player2 + 0, SwingConstants.CENTER);
		scoreP1.setFont(new Font("Dialog", Font.BOLD, 16));
		scoreP2.setFont(new Font("Dialog", Font.BOLD, 16));
		scores.add(scoreP1);
		scores.add(scoreP2);
		add(scores, BorderLayout.CENTER);
		
		btnPanel = new JPanel();
		if(ai != 3) {
			undoBtn = new JButton("Undo");
			undoBtn.setFocusPainted(false);
			UndoHandler undoHandler = new UndoHandler();
			undoBtn.addActionListener(undoHandler);
			btnPanel.add(undoBtn, BorderLayout.LINE_START);
		}
		if(!mode.equals("time") && ai != 0) {
			dotBtn = new JButton("Dot");
			dotBtn.setFocusPainted(false);
			DotHandler dotHandler = new DotHandler();
			dotBtn.addActionListener(dotHandler);
			btnPanel.add(dotBtn, BorderLayout.LINE_END);
		}
		add(btnPanel, BorderLayout.SOUTH);
		
	}

	public void setTurnText(int turn) {
		if(ai == 0) {
			turnLabel.setText("Player " + turn + " turn");
		} else if(ai == 1 || ai == 2) {
			if(turn == 1) {
				turnLabel.setText("Player turn");
			} else {
				turnLabel.setText("AI turn");
			}
		} else {
			turnLabel.setText("AI " + turn + " turn");
		}
		return;
	}
	
	public void setScoreTextP1(int score) {
		scoreP1.setText(player1 + score);
	}
	
	public void setScoreTextP2(int score) {
		scoreP2.setText(player2 + score);
	}
	
	public void setEndGameText(int winner) {
		if(winner != 3) {
			if(ai == 0) {
				turnLabel.setText("Player " + winner + " wins!");
			} else if(ai == 1 || ai == 2) {
				if(winner == 1) {
					turnLabel.setText("Player wins!");
				} else {
					turnLabel.setText("AI wins!");
				}
			} else {
				turnLabel.setText("AI " + winner + " wins!");
			}
		} else {
			turnLabel.setText("It's a draw!");
		}
		return;
	}
	
	public void removeUndoBtn() {
		btnPanel.remove(undoBtn);
		return;
	}
	
	private class UndoHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			bp.setUndo();
		}
	}
	
	private class DotHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			generateDot = true;
		}
	}
	
	public boolean generateDot() {
		return generateDot;
	}

	public void setGenerateDot(boolean b) {
		generateDot = b;
		return;
	}
	
}
