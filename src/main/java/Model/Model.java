package Model;

import java.util.Stack;

public class Model {
    private Board board;
    private Player p1;
    private Player p2;
    private int role;//0:human vs human - 1:AI vs human - 2:human vs AI - 3:AI vs AI
    private Boolean mode; //false -> depth
    private int param;
    private Boolean prune;
    private int turn;
    private Stack<Move> moves;

    public void printModelInfo(){
        System.out.println("Board N:"+board.getN());
        System.out.println("Player1:"+p1.getPlayerNumber()+" Player2: "+p2.getPlayerNumber());
        System.out.println("Role:"+role);
        //System.out.println("Time:"+time);
        System.out.println("Parametro time:"+param);
        System.out.println("Prune:"+prune);
    }

    public Model(int n,int role,String mode,int param,String prune) throws IllegalArgumentException{
        this.moves=new Stack<>();
        this.board=new Board(n);
        switch(role){
            case 0:
                this.p1=new humanPlayer(1);
                this.p2=new humanPlayer(2);
                this.turn = 1;
                break;
            case 1:
                this.p1=new humanPlayer(1);
                this.p2=new pcPlayer(2,p1);
                this.turn = 2;
                break;
            case 2:
                this.p1=new humanPlayer(1);
                this.p2=new pcPlayer(2,p1);
                this.turn = 1;
                break;
            case 3:
                this.p1=new pcPlayer(1);
                this.p2=new pcPlayer(2);
                //ALERT
                this.turn = 1;
                break;
            default:
                throw new IllegalArgumentException();
        }
        this.role=role;
        switch(mode){
            case "time":
                this.mode=true;
                break;
            case "depth":
                this.mode=false;
                break;
            default:
                throw new IllegalArgumentException();
        }
        if(param<=0)
            throw new IllegalArgumentException();
        else
            this.param=param;
        switch(prune){
            case "on":
                this.prune=true;
                break;
            case "off":
                this.prune=false;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public boolean isOver(){//0 isOver,1 gana 1,2 gana 2,3 empate
       return board.isOver();
    }

    public int over() {
        if(p1.getScore() > p2.getScore()) {//gana 1
            return 1;
        }
        else if(p2.getScore() > p1.getScore()) {//gana 2
            return 2;
        }
        else {//empate
           return 3;
        }
    }


    public Edge play() {
    	if(turn==1) {
    		return p1.play(board,mode,param);
    	} else {
    		return p2.play(board,mode,param);

        }
    }

    public void addMove(Edge edge) {//1 si gano 1,2 si gano 2,3 si empate
    	Move move = new Move(edge, (turn == 1)? p1 : p2);
    	if(!moves.contains(move)) {
    		moves.push(move);
            turn = board.makeMove(move);//deberia devolver a quien le toca seguir jugando
    	}
    	return;
    }

    public int getTurn(){
        return turn;
    }

    public int getScoreP1(){
        return p1.getScore();
    }

    public int getScoreP2(){
        return p2.getScore();
    }
    
    public boolean isHumanTurn(){
        if(turn==1){
            return p1 instanceof humanPlayer;
        }
        return p2 instanceof humanPlayer;
    }
    
    public int undo() {
    	int undoMoves = 0;
    	if(!moves.isEmpty()) {
    		Move move;
        	if(((role == 1 && moves.size() != 1) || role == 2) && (turn == 1)) {
        		if(moves.peek().getPlayer().getPlayerNumber() == 2) {
	        		move = moves.pop();
	    			board.undoBoard(move, move.getPlayer());
	    			undoMoves++;
	    			while(moves.peek().getPlayer().getPlayerNumber() == 2) {
	    				move = moves.pop();
	        			board.undoBoard(move, move.getPlayer());
	        			undoMoves++;
	    			}
        		}
        		move = moves.pop();
    			board.undoBoard(move, move.getPlayer());
    			undoMoves++;
        	} else if((role == 0)) {
        		move = moves.pop();
    			board.undoBoard(move, move.getPlayer());
    			undoMoves++;
    			if(!move.isFiller()) {
    				if(turn == 1) {
                		turn = 2;
                	} else {
                		turn = 1;
                	}
    			}
        	}
    	}
    	return undoMoves;
    }

  /*  //retorna 1 si gana p1,2 si gana p2, 3 si es empate
    public int play(){
        int turn=1;
        while(!board.isOver()){
            if(turn==1){
                p1.play();
                turn=2;
            }
            else{
                p2.play();
                turn=1;
            }
        }
        if(p1.getScore()>p2.getScore()){//gana 1
            return 1;
        }
        else if(p2.getScore()>p1.getScore()){//gana 2
            return 2;
        }
        else{//empate
            return 3;
        }
    }*/

}
