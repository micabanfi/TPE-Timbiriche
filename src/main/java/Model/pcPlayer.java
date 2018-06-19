package Model;



import java.util.*;

public class  pcPlayer implements Player{
    private int score;
    private int playerNumber;
    private static Boolean stopSearch=false;
    private static Boolean running=true;
    private static int winCutoff=-1;
    private Player oponent;
    long timeLimitBranch=-1;


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
        Set<Edge> auxShuffle=new HashSet<>();

        List<Edge> availableMoves=board.getAvailableMoves();
        if(availableMoves!=null) {
            timeLimitBranch=param*1000/availableMoves.size();
            for (Edge e : availableMoves) {

                running=true;

                Node child = new Node(board.getNewBoard(new Move(e,this)), this.playerNumber);

                if(model){//time
                    nodeHeuristic = ids(child, model, param);
                }
                else {//depth
                    if (param > 1) {
                        nodeHeuristic = ids(child, model, param - 1);
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

    private int ids(Node state,Boolean model,int param){
        int depth=1;
        long startTime=-1;
        if(model){//time
            startTime = System.currentTimeMillis();
        }
        stopSearch=false;
        int heuristic=state.getHeuristic(this.playerNumber);
        while(running) {

            int searchResult;

            if(model){//time

                if ((System.currentTimeMillis() - startTime) >= timeLimitBranch) {
                    break;
                }
                searchResult=search(state, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, startTime);

            }
            else{
                searchResult=search(state, param, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }


            if(searchResult >= winCutoff){
                return searchResult;
            }
            if(!stopSearch){
                heuristic=searchResult;
            }
            else
                running=false;

            if(model)
                depth++;

        }

        return heuristic;
    }

    private int search(Node state, int depth, int alpha, int beta, long startTime){
        List<Edge> availableMoves = state.getBoard().getAvailableMoves();
        int turn = state.getPlayerNumber()==1? 2:1;
        int score = state.getHeuristic(turn);

        if (System.currentTimeMillis() - startTime >= timeLimitBranch) {
            stopSearch = true;
        }

        if(stopSearch || depth==0 || availableMoves.size() == 0 || score >= winCutoff || score <= -winCutoff){
            return score;
        }

        if (turn==this.playerNumber) {
            for(Edge e:availableMoves){
                Node child=new Node(state.getBoard().getNewBoard(new Move(e,oponent)),this.playerNumber==1?2:1);

                alpha = Math.max(alpha, search(child, depth - 1, alpha, beta, startTime));

                if (beta <= alpha) {
                    break;
                }
            }
            return alpha;

        } else {
            for(Edge e:availableMoves){

                Node child=new Node(state.getBoard().getNewBoard(new Move(e,this)),this.playerNumber==1?2:1);

                beta = Math.min(beta, search(child, depth - 1, alpha, beta, startTime));

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
        int score = state.getHeuristic(turn);


        if(depth==0 || availableMoves.size() == 0 || stopSearch){
           running=false;
           return score;
        }

        if (turn==this.playerNumber) {
            for(Edge e:availableMoves){
                //state.getBoard().printBoard();
                System.out.println("MAX");
                Node child=new Node(state.getBoard().getNewBoard(new Move(e,oponent)),this.playerNumber==1?2:1);

                alpha = Math.max(alpha, search(child, depth - 1, alpha, beta));

                if (beta <= alpha) {
                    break;
                }
            }
            return alpha;

        } else {

            for(Edge e:availableMoves){
                //state.getBoard().printBoard();
                System.out.println("MIN");

                Node child=new Node(state.getBoard().getNewBoard(new Move(e,this)),this.playerNumber==1?2:1);

                beta = Math.min(beta, search(child, depth - 1, alpha, beta));

                if (beta <= alpha) {
                    break;
                }
            }
            return beta;

        }

    }
}
