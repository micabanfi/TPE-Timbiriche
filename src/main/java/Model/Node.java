package Model;

import java.util.LinkedList;
import java.util.List;

public class Node {
    private Board board;
    private int heuristic;
    private int playerNumber;
    List<Node> children;

    public Node(Board board,int playerNumber){
        this.board=board;
        this.playerNumber=playerNumber;
        this.heuristic=board.getHeuristic(playerNumber);
        this.children=new LinkedList<>();
    }

    public Board getBoard(){
        return this.board;
    }

    public int getHeuristic(int playerNumber){
        return board.getHeuristic(playerNumber);
    }

    public void setChild(Node node){
        this.children.add(node);
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}
