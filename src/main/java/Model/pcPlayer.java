package Model;

import java.util.*;

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

    //llama en algun momento a makeMove de Board
    //aca va la IA
    public Edge play(Object... arguments){
        Board board= (Board) arguments[0];
        if(winCutoff==-1)
            winCutoff= (int) Math.pow(board.getN()-1,2);
        Board aux=board.clone();
        Boolean model= (Boolean) arguments[1];
        int depth= (int) arguments[2];
        Edge bestMove=null;
        Integer bestHeuristic=Integer.MIN_VALUE;
        int nodeHeuristic;
        Set<Edge> auxShuffle=new HashSet<>();
        //hago solo como si fuese depth, por ahora paso model al pedo
        List<Edge> availableMoves=board.getAvailableMoves();
        //hay que ver como cortar aca
        if(availableMoves!=null) {
            for (Edge e : availableMoves) {
                Node child = new Node(aux, this.playerNumber);
//                if(model)//time
//                    limit=param/availableMoves.size();
//                else
//                    limit=param;
//
                if (depth > 1) {
                    nodeHeuristic = ids(child, model, depth - 1);
                } else {
                    nodeHeuristic = child.getHeuristic(this.playerNumber);
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
                //caso que sea igual hay que hacer random no se como
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

    private int ids(Node state,Boolean model,int depth){
        //int score=0;
//        if(model)//time
//            // long startTime = System.currentTimeMillis();
//        long endTime = startTime + timeLimit;
        stopSearch=false;
        int heuristic=state.getHeuristic(this.playerNumber);

        while(running) {

            //long currentTime = System.currentTimeMillis();
            //if (currentTime <= endTime) {}
            int searchResult=search(state, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);

            if(searchResult >= winCutoff){
                return searchResult;
            }
            if(!stopSearch){
                heuristic=searchResult;
            }
            //depth++;
        }

        return heuristic;
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
        if(depth==0 || availableMoves.size() == 0 || stopSearch){
           running=false;
           return score;
        }
//        if (depth == 0 || ()){ //|| (score >= winCutoff) || (score <= -winCutoff) || searchCutOff) {
//            return score;
//        }

        if (turn==this.playerNumber) {
            for(Edge e:availableMoves){

                Node child=new Node(state.getBoard().getNewBoard(new Move(e,oponent)),this.playerNumber);

                alpha = Math.max(alpha, search(child, depth - 1, alpha, beta));//, startTime, timeLimit));

                if (beta <= alpha) {
                    break;//no esta bueno esto
                }
            }
            return alpha;

        } else {

            for(Edge e:availableMoves){
                Node child=new Node(state.getBoard().getNewBoard(new Move(e,this)),this.playerNumber);

                beta = Math.min(beta, search(child, depth - 1, alpha, beta));//, startTime, timeLimit));

                if (beta <= alpha) {
                    break;//no esta bueno esto
                }
            }
            return beta;

        }

    }
}
