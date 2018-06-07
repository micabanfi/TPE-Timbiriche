package Model;

public interface Player {
    Edge play(Object ... objects);
    void incScore();
    int getScore();
    public int getPlayerNumber();
}
