package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class  pcPlayer implements Player{
    private int score;
    private int playerNumber;
    private static Boolean stopSearch=false;

    public pcPlayer(int playerNumber){
        this.playerNumber=playerNumber;
    }

    public int getPlayerNumber(){
        return this.playerNumber;
    }

    public void incScore() {
        this.score++;
    }

    public int getScore() {
        return score;
    }

    //llama en algun momento a makeMove de Board
    //aca va la IA
    public Edge play(Object... arguments){
        Board board= (Board) arguments[0];
        Boolean model= (Boolean) arguments[1];
        int depth= (int) arguments[2];
        Edge bestMove=null;
        Integer bestHeuristic=Integer.MIN_VALUE;
        int nodeHeuristic;
        //hago solo como si fuese depth, por ahora paso model al pedo

            List<Edge> availableMoves=board.getAvailableMoves();

            //hay que ver como cortar aca
            for(Edge e:availableMoves){
                Node child=new Node(board.getNewBoard(e),this.playerNumber);

//                if(model)//time
//                    limit=param/availableMoves.size();
//                else
//                    limit=param;
//
                if(depth>1)
                    nodeHeuristic=ids(child,model,depth-1);
                else
                    nodeHeuristic=child.getHeuristic(this.playerNumber);

                if(nodeHeuristic>bestHeuristic){
                    bestHeuristic=nodeHeuristic;
                    bestMove=e;
                }
                //caso que sea igual hay que hacer random no se como
            }
            return bestMove;
    }

    private int ids(Node state,Boolean model,int depth){

//        if(model)//time
//            // long startTime = System.currentTimeMillis();
//        long endTime = startTime + timeLimit;

        stopSearch=false;
        int heuristic=state.getHeuristic(this.playerNumber);

//        Boolean running=true;

//        while(running) {
            //long currentTime = System.currentTimeMillis();
            //if (currentTime <= endTime) {
                int searchResult=search(state, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);

//                if(!stopSearch){
//                    heuristic=searchResult;
//                }
            //}
//        }

        return searchResult;
    }

    private int search(Node state, int depth, int alpha, int beta){
        List<Edge> availableMoves = state.getBoard().getAvailableMoves();
        int turn = state.getPlayerNumber()==1? 2:1;
        int savedScore = (turn==this.playerNumber) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int score = state.getHeuristic(turn);
//        long currentTime = System.currentTimeMillis();
//        long elapsedTime = (currentTime - startTime);

//        if (elapsedTime >= timeLimit) {
//            searchCutoff = true;
//        }

        //
        // If this is a terminal node or a win for either player, abort the search
        //
        if (depth == 0 || (availableMoves.size() == 0)){ //|| (score >= winCutoff) || (score <= -winCutoff) || searchCutOff) {
            return score;
        }

        if (turn==this.playerNumber) {
            for(Edge e:availableMoves){

                Node child=new Node(state.getBoard().getNewBoard(e),this.playerNumber);

                alpha = Math.max(alpha, search(child, depth - 1, alpha, beta));//, startTime, timeLimit));

                if (beta <= alpha) {
                    break;//no esta bueno esto
                }
            }
            return alpha;

        } else {
            for(Edge e:availableMoves){
                Node child=new Node(state.getBoard().getNewBoard(e),this.playerNumber);

                beta = Math.min(beta, search(child, depth - 1, alpha, beta));//, startTime, timeLimit));

                if (beta <= alpha) {
                    break;//no esta bueno esto
                }
            }
            return beta;

        }

    }
}
