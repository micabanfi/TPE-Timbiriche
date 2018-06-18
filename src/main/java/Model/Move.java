package Model;

public class Move {
	private Edge edge;
	private Player player;
	private boolean fillerTop;
	private boolean fillerBottom;
	private boolean fillerLeft;
	private boolean fillerRight;
	
	public Move(Edge edge, Player player) {
		this.edge = edge;
		this.player = player;
	}
	
	public boolean isFiller() {
		if(edge.isHorizontal()) {
			return fillerTop || fillerBottom;
		} else {
			return fillerLeft || fillerRight;
		}
	}

	public Edge getEdge() {
		return edge;
	}

	public Player getPlayer() {
		return player;
	}
	
	public boolean isFillerTop() {
		return fillerTop;
	}

	public void setFillerTop(boolean fillerTop) {
		this.fillerTop = fillerTop;
	}

	public boolean isFillerBottom() {
		return fillerBottom;
	}

	public void setFillerBottom(boolean fillerBottom) {
		this.fillerBottom = fillerBottom;
	}

	public boolean isFillerLeft() {
		return fillerLeft;
	}

	public void setFillerLeft(boolean fillerLeft) {
		this.fillerLeft = fillerLeft;
	}

	public boolean isFillerRight() {
		return fillerRight;
	}

	public void setFillerRight(boolean fillerRight) {
		this.fillerRight = fillerRight;
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
