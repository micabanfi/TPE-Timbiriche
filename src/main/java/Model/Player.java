package Model;

public interface Player {
    Edge play(Object... arguments);
    void incScore();
    void decScore();
    int getScore();
    public int getPlayerNumber();

    void setOpponent(Player p1);

}
