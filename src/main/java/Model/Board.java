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
        Board.n=n;
        this.hEdges=new int[n][n-1];
        this.vEdges=new int[n-1][n];
        this.boxes=new int[n-1][n-1];
        this.edgesLeft=2*n*(n-1);
        this.p1Score=0;
        this.p2Score=0;
    }


    public int makeMove(Move move, Player player){
    	Edge edge = move.getEdge();
        //System.out.println("Make move:"+edge.iPosition()+edge.jPosition());
        Boolean someoneScored=false;
        int i=edge.iPosition();
        int j=edge.jPosition();
        if(edge.isHorizontal()){
            this.hEdges[i][j]=1;
            if(i!=0){
                if(this.hEdges[i-1][j]==1){
                    if(this.vEdges[i-1][j]==1 && this.vEdges[i-1][j+1]==1){
                        this.boxes[i-1][j]=player.getPlayerNumber();
                        player.incScore();//aumento el score del player en 1;
                        if(player.getPlayerNumber()==1){
                        	//edgesLeft--;
                            p1Score++;
                            //return 1;//le toca seguir jugando a p1
                        }
                        else{
                        	//edgesLeft--;
                            p2Score++;
                            //return 2;
                        }
                        someoneScored=true;
                    }
                }
            }
            if(i!=n-1){
                if(this.hEdges[i+1][j]==1){
                    if(this.vEdges[i][j]==1 && this.vEdges[i][j+1]==1) {
                        this.boxes[i][j] = player.getPlayerNumber();
                        player.incScore();//aumento el score del player en 1;
                        if(player.getPlayerNumber()==1){
                        	//edgesLeft--;
                            p1Score++;
                            //return 1;//le toca seguir jugando a p1
                        }
                        else{
                        	//edgesLeft--;
                            p2Score++;
                            //return 2;
                        }
                        someoneScored=true;
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
                        if(player.getPlayerNumber()==1){
                            p1Score++;
                        }
                        else{
                            p2Score++;
                        }
                        someoneScored=true;
                    }
                }
            }
            if(j!=n-1){//chequeo el cuadrado formado a la derecha de mi linea vertical
                if(this.vEdges[i][j+1]==1){
                    if(this.hEdges[i][j]==1 && this.hEdges[i+1][j]==1) {
                        this.boxes[i][j] = player.getPlayerNumber();
                        player.incScore();//aumento el score del player en 1;
                        if(player.getPlayerNumber()==1){
                            p1Score++;
                        }
                        else{
                            p2Score++;
                        }
                        someoneScored=true;
                    }
                }
            }
        }
        edgesLeft--;
        //System.out.println("Faltan:"+edgesLeft);
        if(someoneScored)
            return player.getPlayerNumber()==1?1:2;
        return player.getPlayerNumber()==1?2:1;//no hizo punto,retorna numero del otro jugador

    }

    public boolean isOver(){
        return edgesLeft==0;
    }

    public int getN() {
        return n;
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
                cloned.vEdges[i][j] = vEdges[i][j];

        for(int i=0; i<n; i++)
            for(int j=0; j<(n-1); j++)
                cloned.hEdges[i][j] = hEdges[i][j];

        for(int i=0; i<(n-1); i++)
            for(int j=0; j<(n-1); j++)
                cloned.boxes[i][j] = boxes[i][j];

        cloned.p1Score = p1Score;
        cloned.p2Score = p2Score;

        return cloned;
    }

    public Board getNewBoard(Edge edge){
        Board aux=this.clone();
        if(edge.isHorizontal())
            hEdges[edge.iPosition()][edge.jPosition()]=1;
        else
            vEdges[edge.iPosition()][edge.jPosition()]=1;
        return aux;
    }
    
    public void undoBoard(Edge edge, int turn) {
    	int moveScore = 0;
    	int i = edge.iPosition();
    	int j = edge.jPosition();
    	if(edge.isHorizontal()) {
    		if(i != 0) {
    			// chequear el de arriba
    			if((hEdges[i-1][j] == 1) && (vEdges[i-1][j] == 1) &&(vEdges[i-1][j+1] == 1)) {
    				boxes[i-1][j] = 0;
    				moveScore++;
    			}
    		}
    		if(i != (n - 1)) {
    			// chequear el de abajo
    			if((hEdges[i+1][j] == 1) && (vEdges[i][j] == 1) && (vEdges[i][j+1] == 1)) {
    				boxes[i][j] = 0;
    				moveScore++;
    			}
    		}
    		hEdges[i][j] = 0;
    	} else {
    		if(j != 0) {
    			// chequear el de izq
    			if((vEdges[i][j-1] == 1) && (hEdges[i][j-1] == 1) && (hEdges[i+1][j-1] == 1)) {
    				boxes[i][j-1] = 0;
    				moveScore++;
    			}
    		}
    		if(j != (n - 1)) {
    			// chequear el de der
    			if((vEdges[i][j+1] == 1) && (hEdges[i][j] == 1) && (hEdges[i+1][j] == 1)) {
    				boxes[i][j] = 0;
    				moveScore++;
    			}
    		}    		
    		vEdges[i][j] = 0;
    	}
    	edgesLeft++;
    	if(turn == 1) {
    		p1Score -= moveScore;
    	} else {
    		p2Score -= moveScore;
    	}
    	return;
    }
    
    public boolean empty() {
    	return edgesLeft == 2*n*(n-1);
    }
    
}
