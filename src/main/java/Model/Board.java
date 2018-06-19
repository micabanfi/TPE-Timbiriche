package Model;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private static int n;
    private int hEdges[][];
    private int vEdges[][];
    private int boxes[][];
    private int edgesLeft;
    private int p1Score, p2Score;

    public Board(Board b){
        this.hEdges=new int [n][n-1];
        this.hEdges=b.hEdges;
        this.vEdges=b.vEdges;
        this.boxes=b.boxes;
        this.edgesLeft=b.edgesLeft;
        this.p1Score=b.p1Score;
        this.p2Score=b.p2Score;
    }


    public Board(int n) {
        Board.n = n;
        this.hEdges = new int[n][n-1];
        this.vEdges = new int[n-1][n];
        this.boxes = new int[n-1][n-1];
        this.edgesLeft = 2*n*(n-1);
        this.p1Score = 0;
        this.p2Score = 0;
    }

    // Returns number of player who moves next
    public int makeMove(Move move) {
    	Edge edge = move.getEdge();
    	Player player = move.getPlayer();
        Boolean someoneScored = false;
        int i = edge.iPosition();
        int j = edge.jPosition();
        
        if(edge.isHorizontal()) {
            hEdges[i][j] = 1;
            // Check if top square is filled
            if(i != 0) {
                if(hEdges[i-1][j] == 1) {
                    if((vEdges[i-1][j] == 1) && (vEdges[i-1][j+1] == 1)) {
                        boxes[i-1][j] = player.getPlayerNumber();
                        player.incScore();
                        if(player.getPlayerNumber() == 1) {
                            p1Score++;
                        }
                        else {
                            p2Score++;
                        }
                        move.setFillerTop(true);
                        someoneScored=true;
                    }
                }
            }
            // Check if bottom square is filled
            if(i != (n - 1)) {
                if(hEdges[i+1][j] == 1) {
                    if((vEdges[i][j] == 1) && (vEdges[i][j+1] == 1)) {
                        boxes[i][j] = player.getPlayerNumber();
                        player.incScore();
                        if(player.getPlayerNumber() == 1) {
                            p1Score++;
                        } else {
                            p2Score++;
                        }
                        move.setFillerBottom(true);
                        someoneScored=true;
                    }
                }
            }
        } else {
            vEdges[i][j] = 1;
            // Check if left square is filled
            if(j != 0) {
                if(vEdges[i][j-1] == 1) {
                    if((hEdges[i][j-1] == 1) && (hEdges[i+1][j-1] == 1)) {
                        boxes[i][j-1] = player.getPlayerNumber();
                        player.incScore();
                        if(player.getPlayerNumber() == 1) {
                            p1Score++;
                        } else {
                            p2Score++;
                        }
                        move.setFillerLeft(true);
                        someoneScored = true;
                    }
                }
            }
            // Check if right square is filled
            if(j != n-1){
                if(vEdges[i][j+1] == 1){
                    if((hEdges[i][j] == 1) && (hEdges[i+1][j] == 1)) {
                        boxes[i][j] = player.getPlayerNumber();
                        player.incScore();
                        if(player.getPlayerNumber() == 1) {
                            p1Score++;
                        } else {
                            p2Score++;
                        }
                        move.setFillerRight(true);
                        someoneScored = true;
                    }
                }
            }
        }
        edgesLeft--;
        
        // Set next turn player
        if(someoneScored)
            return (player.getPlayerNumber() == 1)? 1 : 2;
        return (player.getPlayerNumber() == 1)? 2 : 1;
    }

    public int fakeMove(Move move) {
        Edge edge = move.getEdge();
        Player player = move.getPlayer();
        Boolean someoneScored = false;
        int i = edge.iPosition();
        int j = edge.jPosition();
        
        if(edge.isHorizontal()) {
            hEdges[i][j] = 1;
            // Check if top square is filled
            if(i != 0) {
                if(hEdges[i-1][j] == 1) {
                    if((vEdges[i-1][j] == 1) && (vEdges[i-1][j+1] == 1)) {
                        boxes[i-1][j] = player.getPlayerNumber();
                        if(player.getPlayerNumber() == 1) {
                            p1Score++;
                        }
                        else {
                            p2Score++;
                        }
                        move.setFillerTop(true);
                        someoneScored=true;
                    }
                }
            }
            // Check if bottom square is filled
            if(i != (n - 1)) {
                if(hEdges[i+1][j] == 1) {
                    if((vEdges[i][j] == 1) && (vEdges[i][j+1] == 1)) {
                        boxes[i][j] = player.getPlayerNumber();
                        if(player.getPlayerNumber() == 1) {
                            p1Score++;
                        } else {
                            p2Score++;
                        }
                        move.setFillerBottom(true);
                        someoneScored=true;
                    }
                }
            }
        } else {
            vEdges[i][j] = 1;
            // Check if left square is filled
            if(j != 0) {
                if(vEdges[i][j-1] == 1) {
                    if((hEdges[i][j-1] == 1) && (hEdges[i+1][j-1] == 1)) {
                        boxes[i][j-1] = player.getPlayerNumber();
                        if(player.getPlayerNumber() == 1) {
                            p1Score++;
                        } else {
                            p2Score++;
                        }
                        move.setFillerLeft(true);
                        someoneScored = true;
                    }
                }
            }
            // Check if right square is filled
            if(j != n-1){
                if(vEdges[i][j+1] == 1){
                    if((hEdges[i][j] == 1) && (hEdges[i+1][j] == 1)) {
                        boxes[i][j] = player.getPlayerNumber();
                        if(player.getPlayerNumber() == 1) {
                            p1Score++;
                        } else {
                            p2Score++;
                        }
                        move.setFillerRight(true);
                        someoneScored = true;
                    }
                }
            }
        }
        edgesLeft--;

        // Set next turn player
        if(someoneScored)
            return (player.getPlayerNumber() == 1)? 1 : 2;
        return (player.getPlayerNumber() == 1)? 2 : 1;
    }

    public boolean isOver() {
        return edgesLeft == 0;
    }

    public int getN() {
        return n;
    }

    public void printBoard(){
        System.out.println("************************************");
        System.out.println("Print boxes:");
        for(int i = 0; i < n-1; i++) {
            for(int j = 0; j < n-1; j++) {
                System.out.print(boxes[i][j] + " ");
            }
            System.out.print('\n');
        }
        System.out.println("************************************");
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

        return new Board(cloned);
    }

    public Board getNewBoard(Move move){
        Board aux=this.clone();
        Edge edge=move.getEdge();

//        if(edge.isHorizontal())
//            hEdges[edge.iPosition()][edge.jPosition()]=1;
//        else
//            vEdges[edge.iPosition()][edge.jPosition()]=1;
        aux.fakeMove(move);
        return aux;
    }
    
    public void undoBoard(Move move, Player player) {
    	int i = move.getEdge().iPosition();
    	int j = move.getEdge().jPosition();
    	if(move.getEdge().isHorizontal()) {
    		if(i != 0) {
    			if(move.isFillerTop()) {
    				boxes[i-1][j] = 0;
    				player.decScore();
    			}
    		}
    		if(i != (n - 1)) {
    			if(move.isFillerBottom()) {
    				boxes[i][j] = 0;
    				player.decScore();
    			}
    		}
    		hEdges[i][j] = 0;
    	} else {
    		if(j != 0) {
    			if(move.isFillerLeft()) {
    				boxes[i][j-1] = 0;
    				player.decScore();
    			}
    		}
    		if(j != (n - 1)) {
    			if(move.isFillerRight()) {
    				boxes[i][j] = 0;
    				player.decScore();
    			}
    		}    		
    		vEdges[i][j] = 0;
    	}
    	edgesLeft++;
    	return;
    }
    
}
