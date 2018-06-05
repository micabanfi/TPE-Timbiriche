package Model;

public class Board {
    private static int n;
    private int hEdges[][];
    private int vEdges[][];
    private int boxes[][];
    private int edgesLeft;

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

    public Board(int n){
        this.n=n;
        this.hEdges=new int[n][n-1];
        this.vEdges=new int[n-1][n];
        this.boxes=new int[n-1][n-1];
        this.edgesLeft=2*n*(n-1);
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
                    }
                }
            }
            if(i!=n-1){
                if(this.hEdges[i+1][j]==1){
                    if(this.vEdges[i][j]==1 && this.vEdges[i][j+1]==1) {
                        this.boxes[i][j] = player.getPlayerNumber();
                        player.incScore();//aumento el score del player en 1;
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

                    }
                }
            }
            if(j!=n-1){//chequeo el cuadrado formado a la derecha de mi linea vertical
                if(this.vEdges[i][j+1]==1){
                    if(this.hEdges[i][j]==1 && this.hEdges[i+1][j]==1) {
                        this.boxes[i][j] = player.getPlayerNumber();
                        player.incScore();//aumento el score del player en 1;
                    }
                }
            }
        }
        edgesLeft--;
    }

    public boolean isOver(){
        return edgesLeft==0;
    }


}
