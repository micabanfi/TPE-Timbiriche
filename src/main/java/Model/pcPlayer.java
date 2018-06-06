package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class pcPlayer implements Player{
    private int score;
    private int playerNumber;

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
    public Edge play(Board board,int param){
        Edge bestMove=null;
        Integer bestHeuristic=null;
        Queue<Node> q=new LinkedList<>();
        q.offer(new Node(board,this.playerNumber));
        while(param>0 && !q.isEmpty()){
            Node curr=q.remove();
            List<Edge> availableMoves=curr.getBoard().getAvailableMoves();


            for(Edge e:availableMoves){
                Node child=new Node(curr.getNewBoard(e));
                int nodeHeuristic=child.getHeuristic();
                curr.setChild(child);
                if(bestHeuristic==null || nodeHeuristic>bestHeuristic){
                    bestHeuristic=nodeHeuristic;
                    bestMove=e;
                }
                q.offer(child);
            }
            
        }

    }
}
