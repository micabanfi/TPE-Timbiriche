package Model;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class  pcPlayer implements Player{
    private int score;
    private int playerNumber;
    private static Boolean stopSearch=false;
    private static Boolean running=true;
    private static int winCutoff=-1;
    private Player opponent;
    private Boolean prune;
    long timeLimitBranch=-1;
    private StringBuffer dot;


    public pcPlayer(int playerNumber, Boolean prune){
        this.playerNumber=playerNumber;
        this.opponent=null;
        this.prune=prune;
        this.dot=new StringBuffer();
    }

    public pcPlayer(int playerNumber,Player opponent,Boolean prune){
        this.playerNumber=playerNumber;
        this.opponent=opponent;
        this.prune=prune;
        this.dot=new StringBuffer();
    }

    @Override
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
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

    @Override
    public Edge play(Object... arguments){
        dot=new StringBuffer();
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
        dot.append("");
        if(availableMoves!=null) {
            timeLimitBranch=param*1000/availableMoves.size();
            for (Edge e : availableMoves) {

                running=true;

                Node child = new Node(board.getNewBoard(new Move(e,this)), this.playerNumber);

                dot.append("start ->");
                if(e.isHorizontal()){

                    dot.append("\"("+Integer.toString(e.iPosition())+","+Integer.toString(e.jPosition())+")H D:"+param+"\";");
                }
                else{
                    dot.append("\"("+Integer.toString(e.iPosition())+","+Integer.toString(e.jPosition())+")V D:"+param+"\";");
                }

                if(model){//time
                    nodeHeuristic = ids(child, model, param, dot, e);
                }
                else {//depth
                    if (param > 1) {
                        nodeHeuristic = ids(child, model, param - 1, dot, e);
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
        System.out.println(dot);
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

    private int ids(Node state,Boolean model,int param, StringBuffer dot, Edge eAnt){
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
                searchResult=search(state, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, startTime, dot, eAnt);

            }
            else{
                searchResult=search(state, param, Integer.MIN_VALUE, Integer.MAX_VALUE, dot, eAnt);
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

    private int search(Node state, int depth, int alpha, int beta, long startTime, StringBuffer dot, Edge eAnt){
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
                if(eAnt.isHorizontal()){
                    int aux=depth+1;
                    dot.append("\"("+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")H D:"+aux+"\" -> \"(\""+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")H D:"+aux+" ");
                }
                else{
                    int aux=depth+1;
                    dot.append("\"("+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")V D:"+aux+"\" -> \"(\""+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")V D:"+aux+" ");
                }
                if(e.isHorizontal()){
                    dot.append("\"("+Integer.toString(e.iPosition())+","+Integer.toString(e.jPosition())+")H D:"+depth+"\";");
                }
                else {
                    dot.append("\"("+Integer.toString(e.iPosition())+","+Integer.toString(e.jPosition())+")V D:"+depth+"\";");
                }

                Node child=new Node(state.getBoard().getNewBoard(new Move(e,opponent)),this.playerNumber==1?2:1);

                alpha = Math.max(alpha, search(child, depth - 1, alpha, beta, startTime, dot, e));

                if (beta <= alpha) {
                    break;
                }
            }
            return alpha;

        } else {
            for(Edge e:availableMoves){
                if(eAnt.isHorizontal()){
                    int aux=depth+1;
                    dot.append("\"("+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")H D:"+aux+"\" -> \"(\""+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")H D:"+aux+" ");


                }
                else{
                    int aux=depth+1;
                    dot.append("\"("+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")V D:"+aux+"\" -> \"(\""+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")V D:"+aux+" ");


                }
                if(e.isHorizontal()){
                    dot.append("\"("+Integer.toString(e.iPosition())+","+Integer.toString(e.jPosition())+")H D:"+depth+"\";");

                }
                else {
                    dot.append("\"("+Integer.toString(e.iPosition())+","+Integer.toString(e.jPosition())+")V D:"+depth+"\";");
                }

                Node child=new Node(state.getBoard().getNewBoard(new Move(e,this)),this.playerNumber==1?2:1);

                beta = Math.min(beta, search(child, depth - 1, alpha, beta, startTime, dot, e));

                if (beta <= alpha) {
                    break;
                }
            }
            return beta;

        }

    }

    private int search(Node state, int depth, int alpha, int beta, StringBuffer dot, Edge eAnt){

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
                if(eAnt.isHorizontal()){
                    int aux=depth+1;
                    dot.append("\"("+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")H D:"+aux+"\" -> \"(\""+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")H D:"+aux+" ");


                }
                else{
                    int aux=depth+1;
                    dot.append("\"("+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")V D:"+aux+"\" -> \"(\""+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")V D:"+aux+" ");


                }
                if(e.isHorizontal()){
                    dot.append("\"("+Integer.toString(e.iPosition())+","+Integer.toString(e.jPosition())+")H D:"+depth+"\";");

                }
                else {
                    dot.append("\"("+Integer.toString(e.iPosition())+","+Integer.toString(e.jPosition())+")V D:"+depth+"\";");
                }

                Node child=new Node(state.getBoard().getNewBoard(new Move(e,opponent)),this.playerNumber==1?2:1);

                alpha = Math.max(alpha, search(child, depth - 1, alpha, beta, dot, e));

                if(prune){
                    if (beta <= alpha) {
                        break;
                    }
                }

            }
            return alpha;

        } else {

            for(Edge e:availableMoves){
                //state.getBoard().printBoard();
                System.out.println("MIN");

                if(eAnt.isHorizontal()){
                    int aux=depth+1;
                    dot.append("\"("+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")H D:"+aux+"\" -> \"("+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")H D:"+aux+" ");
                }
                else{
                    int aux=depth+1;
                    dot.append("\"("+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")V D:"+aux+"\" -> \"("+Integer.toString(eAnt.iPosition())+","+Integer.toString(eAnt.jPosition())+")V D:"+aux+" ");
                }
                if(e.isHorizontal()){
                    dot.append("("+Integer.toString(e.iPosition())+","+Integer.toString(e.jPosition())+")H D:"+depth+"\";");

                }
                else {
                    dot.append("("+Integer.toString(e.iPosition())+","+Integer.toString(e.jPosition())+")V D:"+depth+"\";");
                }

                Node child=new Node(state.getBoard().getNewBoard(new Move(e,this)),this.playerNumber==1?2:1);

                beta = Math.min(beta, search(child, depth - 1, alpha, beta, dot, e));

                if(prune){
                    if (beta <= alpha) {
                        break;
                    }
                }

            }
            return beta;

        }

    }

    public StringBuffer getDot() {
        return dot;
    }
}
