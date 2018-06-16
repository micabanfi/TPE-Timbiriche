package Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TesterBoards {
    public static void main(String[] args){
        Model gameModel=new Model(7,0,"depth",3,"on");
        gameModel.printModelInfo();
        //board.printBoard();
        Player p1=new humanPlayer(1);
        Player p2=new humanPlayer(2);
        while (!gameModel.isOver()){
            if(gameModel.play()==null){
                //aca agarraria el edge del view




            }
        }









    }

}
