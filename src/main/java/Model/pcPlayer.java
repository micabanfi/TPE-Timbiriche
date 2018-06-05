package Model;

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
    public void play(){

    }
}
