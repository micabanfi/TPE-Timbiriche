import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameWindow extends JFrame {
	JPanel boardPanel;
	//private Board board;
	
    public int[][] board;
    public int n;
    
    public GameWindow() {
    	setTitle("Timbiriche");
    	setSize(700, 700);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	JLabel headerLabel = new JLabel("TIMBIRICHE",JLabel.CENTER );
    	headerLabel.setFont(new Font("Kai", Font.BOLD, 32));
    	
    	boardPanel = new JPanel()
//    	{
//    	    protected void paintComponent(Graphics g) {
//    	        super.paintComponent(g);
//    	        g.drawLine(0,0, 20, 35);
//    	    }}
    	;
    	
//    	JPanel panel = new JPanel();
//    	panel.setBackground(Color.white);
//    	panel.setPreferredSize(new Dimension(500, 500));
//    	boardPanel.add(panel);
    	
    	final LinesComponent comp = new LinesComponent();
    	comp.setBackground(Color.darkGray);
        comp.setPreferredSize(new Dimension(320, 200));
        
    	boardPanel.add(comp);

    	getContentPane().add(headerLabel, BorderLayout.PAGE_START);
    	getContentPane().add(boardPanel, BorderLayout.CENTER);
    	//pack();
    	setVisible(true);
    }

    public static void main(String[] args) {
    	GameWindow window = new GameWindow();
//    	BoardGUI gui = new BoardGUI();
//        gui.n = 2;
//        gui.board = new int[2][2];
//        gui.board[0][0] = 0;
//        gui.board[0][1] = 1;
//        gui.board[1][0] = 0;
//        gui.board[1][1] = 1;
//        
//        
//        gui.showMainFrame();
        //printBoardInConsole();
    	
        
        
//        testFrame.getContentPane().add(comp, BorderLayout.CENTER);
//        JPanel buttonsPanel = new JPanel();
//        JButton newLineButton = new JButton("New Line");
//        JButton clearButton = new JButton("Clear");
//        buttonsPanel.add(newLineButton);
//        buttonsPanel.add(clearButton);
//        testFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
//        newLineButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int x1 = (int) (Math.random()*320);
//                int x2 = (int) (Math.random()*320);
//                int y1 = (int) (Math.random()*200);
//                int y2 = (int) (Math.random()*200);
//                comp.addLine(x1, y1, x2, y2);
//            }
//        });
//        clearButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                comp.clearLines();
//            }
//        });
    }

//    public static void printBoardInConsole() {
//        for(int i = 0; i < n; i++) {
//            for(int j = 0; j < n; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.print('\n');
//        }
//    }
}
