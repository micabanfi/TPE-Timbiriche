package View;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;

@SuppressWarnings("serial")
public class LinesComponent extends JPanel implements MouseListener {
	private static int n;
	private static LinkedList<Line> lines = new LinkedList<>();
	private LinkedList<Node> nodes = new LinkedList<>();
	private static Node nodeSelected1;
	private static Node nodeSelected2;
	
	public LinesComponent(int n) {
		setLayout(new GridLayout(n, n, 20, 20));
		LinesComponent.n = n;
		//setBackground(Color.yellow);
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		for(int i = 0; i < n; i++) {
	    	for(int j = 0; j < n; j++) {
	    		Node node = new Node(i, j, this);
	    		this.add(node);
	    		nodes.add(node);
	    	}
	    }
		addMouseListener(this);
	}
	
	public void checkAddLine() {
		if(nodeSelected1 == nodeSelected2) {
			resetSelectedNodes();
			return;
		}
		if(nodeSelected1.getLine() == nodeSelected2.getLine()) {
			if(Math.abs(nodeSelected1.getColumn() - nodeSelected2.getColumn()) == 1) {
				addLine(nodeSelected1, nodeSelected2);
			}
		} else if(nodeSelected1.getColumn() == nodeSelected2.getColumn()) {
			if(Math.abs(nodeSelected1.getLine() - nodeSelected2.getLine()) == 1) {
				addLine(nodeSelected1, nodeSelected2);
			}
		}
		resetSelectedNodes();
		return;
	}
	
	public void resetSelectedNodes() {
		nodeSelected1 = null;
		nodeSelected2 = null;
		repaint();
		return;
	}

	public void addLine(Node n1, Node n2) {
		lines.add(new Line(n1, n2));
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
	    g2.setColor(Color.lightGray);
	    g2.setStroke(new BasicStroke(5));
	    for (Line line : lines) {
	        g2.drawLine(line.n1.x + Node.radius, line.n1.y + Node.radius,
	        		line.n2.x + Node.radius, line.n2.y + Node.radius);
	    }
	    g2.setColor(Color.darkGray);
	    for(Node n : nodes) {
	    	n.paintComponent(g2);
	    }
	}

	private static class Line {
		Node n1, n2; 
	
	    public Line(Node n1, Node n2) {
	        this.n1 = n1;
	        this.n2 = n2;
	    }               
	}
	
	private static class Node extends JComponent implements MouseListener {
		private int line;
		private int column;
		private static int radius;
		private int x;
		private int y;
		private static int width;
		private static int height;
		private LinesComponent lc;
		
		public Node(int line, int column, LinesComponent lc) {
			this.line = line;
			this.column = column;
			radius = 10;
			x = (int) ((column + 0.5) * (width / n)) - radius;
			y = (int) ((line + 0.5) * (height / n)) - radius;
			this.lc = lc;
			setBorder(BorderFactory.createLineBorder(Color.black));
			addMouseListener(this);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			x = (int) ((column + 0.5) * (width / n)) - radius;
			y = (int) ((line + 0.5) * (height / n)) - radius;
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			if(this == nodeSelected1 || this == nodeSelected2) {
				g2.setColor(Color.yellow);
			}
	        g2.fillOval(x, y, 2 * radius, 2 * radius);
		}
		
		public int getLine() {
			return line;
		}
		
		public int getColumn() {
			return column;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("clicked: " + line + " " + column);
			if(nodeSelected1 == null) {
				nodeSelected1 = this;
			} else {
				nodeSelected2 = this;
				lc.checkAddLine();
			}
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
		
		//newLineButton.addActionListener(new ActionListener() {
			//
//			            @Override
//			            public void actionPerformed(ActionEvent e) {
//			                int x1 = (int) (Math.random()*320);
//			                int x2 = (int) (Math.random()*320);
//			                int y1 = (int) (Math.random()*200);
//			                int y2 = (int) (Math.random()*200);
//			                comp.addLine(x1, y1, x2, y2);
//			            }
//			        });
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		resetSelectedNodes();
		System.out.println("Panel clicked");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
