package Model;

public class humanPlayer implements Player{
    private int score;
    private int playerNumber;

    public humanPlayer(int playerNumber)
    {
        this.score=0;
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
    //recibe desde el controller que recibe desde el view lo que elige el jugador humano
    public Edge play(){

    }
}
