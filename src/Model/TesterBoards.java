package Model;

public class TesterBoards {
    public static void main(String[] args){
        Board board=new Board(4);
        //board.printBoard();

        Edge Hedge1=new Edge(3,2,true);
        Edge Hedge2=new Edge(2,2,true);
        Edge Vedge1=new Edge(2,3,false);
        Edge Vedge2=new Edge(2,2,false);

        Player p1=new Player(true,1);
        Player p2=new Player(true,2);

        board.makeMove(Hedge1,p2);
        board.makeMove(Hedge2,p2);
        board.makeMove(Vedge1,p2);
        board.makeMove(Vedge2,p2);

        board.printBoard(p1,p2);








    }

}
