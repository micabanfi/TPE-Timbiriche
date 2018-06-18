package Model;

public class Edge {

    private int i;
    private int j;
    private Boolean horizontal;

    public Edge(Boolean horizontal){
        this.i=-1;
        this.j=-1;
        this.horizontal=horizontal;

    }

    public Edge(int i,int j,Boolean horizontal){
        this.i=i;
        this.j=j;
        this.horizontal=horizontal;

    }

    public int iPosition(){
        return this.i;
    }

    public int jPosition(){
        return this.j;
    }

    public Boolean isHorizontal(){
        return this.horizontal;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((horizontal == null) ? 0 : horizontal.hashCode());
		result = prime * result + i;
		result = prime * result + j;
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
		Edge edge = (Edge) obj;
		if (horizontal == null) {
			if (edge.horizontal != null)
				return false;
		} else if (!horizontal.equals(edge.horizontal))
			return false;
		if (i != edge.i)
			return false;
		if (j != edge.j)
			return false;
		return true;
	}
    
    

}
