package Model;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class  pcPlayer implements Player{
    private int score;
    private int playerNumber;
    private static Boolean stopSearch=false;
    private static Boolean running=true;
    private static int winCutoff=-1;
    private Player oponent;


    public pcPlayer(int playerNumber){
        this.playerNumber=playerNumber;
        this.oponent=null;
    }

    public pcPlayer(int playerNumber,Player oponent){
        this.playerNumber=playerNumber;
        this.oponent=oponent;
    }


    public void setOponent(Player oponent) {
        this.oponent = oponent;
    }

    public int getPlayerNumber(){
        return this.playerNumber;
    }

    public void incScore() {
        this.score++;
    }
    
    public void decScore() {
        this.score--;
    }

    public int getScore() {
        return score;
    }


    public Edge play(Object... arguments){
        running=true;
        Board board= (Board) arguments[0];
        if(winCutoff==-1)
            winCutoff= (int) Math.pow(board.getN()-1,2);
        Boolean model= (Boolean) arguments[1];
        int param= (int) arguments[2];
        Edge bestMove=null;
        Integer bestHeuristic=Integer.MIN_VALUE;
        int nodeHeuristic;
        long searchTimeLimit=-1;
        Set<Edge> auxShuffle=new HashSet<>();

        List<Edge> availableMoves=board.getAvailableMoves();
        if(availableMoves!=null) {
            for (Edge e : availableMoves) {
                Node child = new Node(board.getNewBoard(new Move(e,this)), this.playerNumber);

                if(model){//time
                    nodeHeuristic = ids(child, model, param, availableMoves.size());
                }
                else {
                    if (param > 1) {
                        nodeHeuristic = ids(child, model, param - 1, availableMoves.size());
                    } else {
                        nodeHeuristic = child.getHeuristic(this.playerNumber);
                    }
                }

                if(nodeHeuristic >= winCutoff){
                    return e;
                }

                if (nodeHeuristic > bestHeuristic) {
                    auxShuffle=null;
                    bestHeuristic = nodeHeuristic;
                    bestMove = e;
                }

                else if (nodeHeuristic == bestHeuristic){
                    if(auxShuffle==null)
                        auxShuffle= new HashSet<>();
                    auxShuffle.add(e);
                }
            }
        }
        //System.out.println("Computer EDGE:"+bestMove.iPosition()+"-"+bestMove.jPosition()+"-"+bestMove.isHorizontal());
        if(bestMove==null){
            System.out.println("ES NULL");
        }
        if(auxShuffle!=null){
            int size = auxShuffle.size();
            int item = new Random().nextInt(size);
            int i = 0;
            for(Edge edge : auxShuffle)
            {
                if (i == item)
                    return edge;
                i++;
            }
        }
        return bestMove;
    }

    private int ids(Node state,Boolean model,int param, int availableMovesSize){
        //int score=0;
        int depth=1;
        long startTime;
        long endTime = -1;
        if(model){//time
            long timeLimit = (1000*param)/availableMovesSize;
            startTime = System.currentTimeMillis();
            endTime = startTime + timeLimit;
            System.out.println("en, start, limit");
            System.out.println(endTime);
            System.out.println(startTime);
            System.out.println(timeLimit);

        }

        stopSearch=false;
        int heuristic=state.getHeuristic(this.playerNumber);

        while(running) {

            int searchResult;

            if(model){//time

                long currentTime = System.currentTimeMillis();
                System.out.println("current");
                System.out.println(currentTime);
                if (currentTime >= endTime) {
                    break;
                }
                searchResult=search(state, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, currentTime, endTime-currentTime);

            }
            else{
                searchResult=search(state, (int) param, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }


            if(searchResult >= winCutoff){
                return searchResult;
            }
            if(!stopSearch){
                heuristic=searchResult;
            }
            if(model)
                depth++;
        }

        return heuristic;
    }

    private int search(Node state, int depth, int alpha, int beta, long startTime, long timeLimit){

        List<Edge> availableMoves = state.getBoard().getAvailableMoves();
        int turn = state.getPlayerNumber()==1? 2:1;
//        int savedScore = (turn==this.playerNumber) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int score = state.getHeuristic(turn);
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        if (elapsedTime >= timeLimit) {
            stopSearch = true;
        }

        if(stopSearch || depth==0 || availableMoves.size() == 0 || score >= winCutoff || score <= -winCutoff){
            running=false;
            return score;
        }

        if (turn==this.playerNumber) {
            for(Edge e:availableMoves){
                state.getBoard().printBoard();
                Node child=new Node(state.getBoard().getNewBoard(new Move(e,oponent)),this.playerNumber);

                alpha = Math.max(alpha, search(child, depth - 1, alpha, beta, startTime, timeLimit));

                if (beta <= alpha) {
                    break;
                }
            }
            return alpha;

        } else {

            for(Edge e:availableMoves){
                state.getBoard().printBoard();
                Node child=new Node(state.getBoard().getNewBoard(new Move(e,this)),this.playerNumber);

                beta = Math.min(beta, search(child, depth - 1, alpha, beta, startTime, timeLimit));

                if (beta <= alpha) {
                    break;
                }
            }
            return beta;

        }

    }

    private int search(Node state, int depth, int alpha, int beta){

        List<Edge> availableMoves = state.getBoard().getAvailableMoves();
        int turn = state.getPlayerNumber()==1? 2:1;
        int savedScore = (turn==this.playerNumber) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int score = state.getHeuristic(turn);


        if(depth==0 || availableMoves.size() == 0 || stopSearch){
           running=false;
           return score;
        }

        if (turn==this.playerNumber) {
            for(Edge e:availableMoves){
                state.getBoard().printBoard();
                Node child=new Node(state.getBoard().getNewBoard(new Move(e,oponent)),this.playerNumber);

                alpha = Math.max(alpha, search(child, depth - 1, alpha, beta));//, startTime, timeLimit));

                if (beta <= alpha) {
                    break;
                }
            }
            return alpha;

        } else {

            for(Edge e:availableMoves){
                state.getBoard().printBoard();
                Node child=new Node(state.getBoard().getNewBoard(new Move(e,this)),this.playerNumber);

                beta = Math.min(beta, search(child, depth - 1, alpha, beta));//, startTime, timeLimit));

                if (beta <= alpha) {
                    break;
                }
            }
            return beta;

        }

    }
}
