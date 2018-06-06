package Model;

public class Node {
    private Board board;
    private int heuristic;
    private int playerNumber;

    public Node(Board board,int playerNumber){
        this.board=board;
        this.playerNumber=playerNumber;// no se si hace falta
        this.heuristic=board.getHeuristic(playerNumber);
    }

    public Board getBoard(){
        return this.board;
    }
}
