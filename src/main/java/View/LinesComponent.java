package View;

import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;

public class LinesComponent extends JPanel {
	private static int n;
	private LinkedList<Line> lines = new LinkedList<>();
	private LinkedList<Node> nodes = new LinkedList<>();
	//private JPanel panel;
	
	public LinesComponent(int n) {
		LinesComponent.n = n;
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		for(int i = 0; i < n; i++) {
	    	for(int j = 0; j < n; j++) {
	    		nodes.add(new Node(i, j));
	    	}
	    }
	}

	public void addLine(int x1, int x2, int x3, int x4) {
		lines.add(new Line(x1,x2,x3,x4));
		repaint();
	}
	
	public void undoLine() {
	    lines.removeLast();
	    repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Node.height = getHeight();
		Node.width = getWidth();
		Graphics2D g2 = (Graphics2D) g;
	    super.paintComponent(g);
	    for (Line line : lines) {
	        g2.drawLine(line.x1, line.y1, line.x2, line.y2);
	    }
	    for(Node n : nodes) {
	    	n.paintComponent(g2);
	    }
	}

	private static class Line {
		final int x1; 
	    final int y1;
	    final int x2;
	    final int y2;   
	
	    public Line(int x1, int y1, int x2, int y2) {
	        this.x1 = x1;
	        this.y1 = y1;
	        this.x2 = x2;
	        this.y2 = y2;
	    }               
	}
	
	private static class Node extends JComponent {
		private int line;
		private int column;
		public static int width;
		public static int height;
		
		public Node(int line, int column) {
			this.line = line;
			this.column = column;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
		    super.paintComponent(g);
	        g2.fillOval(column * (width - 80) / (n - 1) + 30,
	        		line * (height - 80) / (n - 1) + 30, 20, 20);
		}
		
		public int getLine() {
			return line;
		}
		
		public int getColumn() {
			return column;
		}
	}
}
