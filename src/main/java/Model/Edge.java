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

}
