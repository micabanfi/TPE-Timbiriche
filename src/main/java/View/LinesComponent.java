package View;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;

import Model.*;

@SuppressWarnings("serial")
public class LinesComponent extends JPanel {
	private static int n;
	private static LinkedList<Line> lines = new LinkedList<>();
	private LinkedList<Node> nodes = new LinkedList<>();
	private static Node nodeSelected1;
	private static Node nodeSelected2;
	private boolean nodesSelected;
	private Edge edge;
	
	public LinesComponent(int n) {
		setLayout(new GridLayout(n, n));
		LinesComponent.n = n;
		nodesSelected = false;
		for(int i = 0; i < n; i++) {
	    	for(int j = 0; j < n; j++) {
	    		Node node = new Node(i, j, this);
	    		this.add(node);
	    		nodes.add(node);
	    	}
	    }
	}
	
	public void checkNodesSelected() {
		if(nodeSelected1 != nodeSelected2) {
			if(nodeSelected1.getLine() == nodeSelected2.getLine()) {
				if(Math.abs(nodeSelected1.getColumn() - nodeSelected2.getColumn()) == 1) {
					edge = new Edge(nodeSelected1.getLine(), minColumn(), true);
					nodesSelected = true;
					addLine(nodeSelected1, nodeSelected2);
				}
			} else if(nodeSelected1.getColumn() == nodeSelected2.getColumn()) {
				if(Math.abs(nodeSelected1.getLine() - nodeSelected2.getLine()) == 1) {
					edge = new Edge(minLine(), nodeSelected1.getColumn(), false);
					nodesSelected = true;
					addLine(nodeSelected1, nodeSelected2);		
				}
			}
		}
		resetSelectedNodes();
		return;
	}
	
	private int minLine() {
		int line1 = nodeSelected1.getLine();
		int line2 = nodeSelected2.getLine();
		return (line1 < line2)? line1 : line2;
	}
	
	private int minColumn() {
		int col1 = nodeSelected1.getColumn();
		int col2 = nodeSelected2.getColumn();
		return (col1 < col2)? col1 : col2;
	}
	
	// Returns Edge chosen by Human Player
	public Edge getEdge() {
		nodesSelected = false;
		return edge;
	}
	
	// Paints the Edge chosen by AI
	public void paintEdge(Edge edge) {
		int i = edge.iPosition();
		int j = edge.jPosition();
		
		if(edge.isHorizontal()) {
			nodeSelected1 = getNode(i, j);
			nodeSelected2 = getNode(i, j + 1);
		} else {
			nodeSelected1 = getNode(i, j);
			nodeSelected2 = getNode(i + 1, j);
		}
		
		addLine(nodeSelected1, nodeSelected2);
		return;
	}
	
	private Node getNode(int i, int j) {
		for(Node node : nodes) {
			if((node.getLine() == i) && (node.getColumn() == j)) {
				return node;
			}
		}
		return null;
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
		return;
	}
	
	public void undoLine() {
	    lines.removeLast();
	    repaint();
	    return;
	}
	
	public boolean nodesSelected() {
		return nodesSelected;
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
	    return;
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
			System.out.println("Clicked (" + line + ", " + column + ")");
			
			if(nodeSelected1 == null) {
				nodeSelected1 = this;
			} else {
				nodeSelected2 = this;
				lc.checkNodesSelected();
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
		
	}

}
