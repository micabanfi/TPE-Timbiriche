package Model;

public class humanPlayer implements Player{
    private int score;
    private int playerNumber;
    private boolean turn;
    private Player opponent;

    public humanPlayer(int playerNumber)
    {
        this.score=0;
        this.playerNumber=playerNumber;
        this.turn=false;
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
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    @Override
    public Edge play(Object... arguments){
        return null;
    }
}
