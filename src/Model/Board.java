package Model;

public class Board {
    private static int n;
    private int hEdges[][];
    private int vEdges[][];
    private int boxes[][];
    private int edgesLeft;

    public Board(int n){
        this.n=n;
        this.hEdges=new int[n][n-1];
        this.vEdges=new int[n-1][n];
        this.boxes=new int[n-1][n-1];
        this.edgesLeft=n;
    }

    public int makeMove(Edge edge,Player player){
        markMove(edge,player);
        edgesLeft--;
        if(edgesLeft==0)
            return 0;
        return 1;
    }

    private void markMove(Edge edge,Player player){
        int i=edge.iPosition();
        int j=edge.jPosition();
        if(edge.isHorizontal()){
            this.hEdges[i][j]=1;
            if(i!=0){
                if(this.hEdges[i-1][j]==1){
                    if(this.vEdges[i-1][j]==1 && this.vEdges[i-1][j+1]==1){
                        this.boxes[i-1][j]=player.getPlayerNumber();
                    }
                }
            }
            if(i!=n-1){
                if(this.hEdges[i+1][j]==1){
                    if(this.vEdges[i][j]==1 && this.vEdges[i][j+1]==1)
                        this.boxes[i][j]=player.getPlayerNumber();
                }
            }
        }
        else{
            this.vEdges[i][j]=1;
            if(j!=0){
                if(this.vEdges[i][j-1]==1){
                    if(this.hEdges[i][j-1]==1 && this.hEdges[i+1][j-1]==1){
                        this.boxes[i][j-1]=player.getPlayerNumber();
                    }
                }
            }
            if(j!=n-2){
                if(this.hEdges[i][j+1]==1){
                    if(this.vEdges[i][j]==1 && this.vEdges[i+1][j]==1)
                        this.boxes[i][j]=player.getPlayerNumber();
                }
            }
        }
    }

}
