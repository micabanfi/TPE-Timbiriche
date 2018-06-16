package Model;

public interface Player {
    Edge play(Object... arguments);
    void incScore();
    int getScore();
    public int getPlayerNumber();
    /*void setTurn();
    boolean getTurn();*/
}
