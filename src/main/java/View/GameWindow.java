package View;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	private LinesComponent linesComp;
	
    public int[][] board;
    public int n;
    
    public GameWindow() {
    	setTitle("Timbiriche");
    	setSize(700, 700);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	JLabel headerLabel = new JLabel("TIMBIRICHE", JLabel.CENTER);
    	headerLabel.setFont(new Font("Kai", Font.BOLD, 32));
    	
    	// Hay que pasar el numero de lado
    	linesComp = new LinesComponent(12);
    	
    	JPanel bottomPanel = new JPanel();
    	JButton undoButton = new JButton("Undo");
    	bottomPanel.add(undoButton);

    	getContentPane().add(headerLabel, BorderLayout.PAGE_START);
    	getContentPane().add(linesComp, BorderLayout.CENTER);
    	getContentPane().add(bottomPanel, BorderLayout.PAGE_END);
    	
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
