package Model;

public class Move {
	private Edge edge;
	private int player;
	private boolean fillerMove;
	
	public Move(Edge edge, int player) {
		this.edge = edge;
		this.player = player;
	}

	public boolean isFillerMove() {
		return fillerMove;
	}

	public void setFillerMove(boolean fillerMove) {
		this.fillerMove = fillerMove;
	}

	public Edge getEdge() {
		return edge;
	}

	public int getPlayer() {
		return player;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edge == null) ? 0 : edge.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move move = (Move) obj;
		if (edge == null) {
			if (move.edge != null)
				return false;
		} else if (!edge.equals(move.edge))
			return false;
		return true;
	}
	
}
