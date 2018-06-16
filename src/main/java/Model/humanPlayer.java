package Model;

public class humanPlayer implements Player{
    private int score;
    private int playerNumber;
    private boolean turn;

    public humanPlayer(int playerNumber)
    {
        this.score=0;
        this.playerNumber=playerNumber;
        this.turn=false;
    }

    public int getPlayerNumber(){
        return this.playerNumber;
    }

   /* @Override
    public void setTurn() {
        turn=!turn;
    }

    @Override
    public boolean getTurn() {
        return turn;
    }*/

    public void incScore() {
        this.score++;
    }

    public int getScore() {
        return score;
    }

    //llama en algun momento a makeMove de Board
    //recibe desde el controller que recibe desde el view lo que elige el jugador humano
    public Edge play(Object... arguments){
        return null;
    }
}
