package Model;

public class Player {
    private Boolean human;
    private int score;
    private int playerNumber;

    public Player(Boolean human,int playerNumber)
    {
        this.score=0;
        this.human=human;
        this.playerNumber=playerNumber;
    }

    public int getPlayerNumber(){
        return this.playerNumber;
    }

}
