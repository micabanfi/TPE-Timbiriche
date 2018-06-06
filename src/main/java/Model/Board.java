package Model;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private static int n;
    private int hEdges[][];
    private int vEdges[][];
    private int boxes[][];
    private int edgesLeft;
    private int p1Score,p2Score;



    public Board(int n){
        this.n=n;
        this.hEdges=new int[n][n-1];
        this.vEdges=new int[n-1][n];
        this.boxes=new int[n-1][n-1];
        this.edgesLeft=2*n*(n-1);
        this.p1Score=0;
        this.p2Score=0;
    }


    public void makeMove(Edge edge,Player player){
        int i=edge.iPosition();
        int j=edge.jPosition();
        if(edge.isHorizontal()){
            this.hEdges[i][j]=1;
            if(i!=0){
                if(this.hEdges[i-1][j]==1){
                    if(this.vEdges[i-1][j]==1 && this.vEdges[i-1][j+1]==1){
                        this.boxes[i-1][j]=player.getPlayerNumber();
                        player.incScore();//aumento el score del player en 1;
                        if(player.getPlayerNumber()==1)
                            p1Score++;
                        else
                            p2Score++;
                    }
                }
            }
            if(i!=n-1){
                if(this.hEdges[i+1][j]==1){
                    if(this.vEdges[i][j]==1 && this.vEdges[i][j+1]==1) {
                        this.boxes[i][j] = player.getPlayerNumber();
                        player.incScore();//aumento el score del player en 1;
                        if(player.getPlayerNumber()==1)
                            p1Score++;
                        else
                            p2Score++;
                    }

                }
            }
        }
        else{
            this.vEdges[i][j]=1;
            if(j!=0){//chequeo el cuadrado que puedo formar a la izquierda de mi linea vertical
                if(this.vEdges[i][j-1]==1){
                    if(this.hEdges[i][j-1]==1 && this.hEdges[i+1][j-1]==1){
                        this.boxes[i][j-1]=player.getPlayerNumber();
                        player.incScore();//aumnto el score del player en 1;
                        if(player.getPlayerNumber()==1)
                            p1Score++;
                        else
                            p2Score++;

                    }
                }
            }
            if(j!=n-1){//chequeo el cuadrado formado a la derecha de mi linea vertical
                if(this.vEdges[i][j+1]==1){
                    if(this.hEdges[i][j]==1 && this.hEdges[i+1][j]==1) {
                        this.boxes[i][j] = player.getPlayerNumber();
                        player.incScore();//aumento el score del player en 1;
                        if(player.getPlayerNumber()==1)
                            p1Score++;
                        else
                            p2Score++;
                    }
                }
            }
        }
        edgesLeft--;
    }

    public boolean isOver(){
        return edgesLeft==0;
    }

    public void printBoard(Player p1,Player p2){
        System.out.println("Print boxes:");
        for(int i = 0; i < n-1; i++) {
            for(int j = 0; j < n-1; j++) {
                System.out.print(boxes[i][j] + " ");
            }
            System.out.print('\n');
        }
        System.out.println("Print horizontal:");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n-1; j++) {
                System.out.print(hEdges[i][j] + " ");
            }
            System.out.print('\n');
        }
        System.out.println("Print vertical:");
        for(int i = 0; i < n-1; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(vEdges[i][j] + " ");
            }
            System.out.print('\n');
        }

        System.out.println("Player 1: "+p1.getScore()+" Player 2: "+p2.getScore());
    }

    public int getHeuristic(int playerNumber){
        if(playerNumber==1)
            return p1Score-p2Score;
        else
            return p2Score-p1Score;
    }

    public List<Edge> getAvailableMoves(){
        List<Edge> availableMoves=new LinkedList<>();
        for(int i=0;i<hEdges.length;i++)
            for(int j=0;j<hEdges[0].length;j++){
                if(hEdges[i][j]==0){
                    availableMoves.add(new Edge(i,j,true));
                }
            }
        for(int i=0;i<vEdges.length;i++)
            for(int j=0;j<vEdges[0].length;j++){
                if(vEdges[i][j]==0){
                    availableMoves.add(new Edge(i,j,false));
                }
            }
        return availableMoves;
    }

    public Board clone() {
        Board cloned = new Board(n);

        for(int i=0; i<(n-1); i++)
            for(int j=0; j<n; j++)
                cloned.hEdges[i][j] = hEdges[i][j];

        for(int i=0; i<n; i++)
            for(int j=0; j<(n-1); j++)
                cloned.vEdges[i][j] = vEdges[i][j];

        for(int i=0; i<(n-1); i++)
            for(int j=0; j<(n-1); j++)
                cloned.boxes[i][j] = boxes[i][j];

        cloned.p1Score = p1Score;
        cloned.p2Score = p2Score;

        return cloned;
    }

    public Board getNewBoard(Edge edge){
        Board aux=this.clone();
        

    }


}
