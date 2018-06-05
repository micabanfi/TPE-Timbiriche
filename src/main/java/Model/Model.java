package Model;

public class Model {
    private Board board;
    private Player p1;
    private Player p2;
    private int role;
    private Boolean time; //false -> depth
    private int param;
    private Boolean prune;

    public Model(int n,int role,String mode,int param,String prune) throws IllegalArgumentException{
        this.board=new Board(n);
        switch(role){
            case 0:
                this.p1=new humanPlayer(1);
                this.p2=new humanPlayer(2);
                break;
            case 1:
                this.p1=new pcPlayer(1);
                this.p2=new humanPlayer(2);
                break;
            case 2:
                this.p1=new humanPlayer(1);
                this.p2=new pcPlayer(2);
                break;
            case 3:
                this.p1=new pcPlayer(1);
                this.p2=new pcPlayer(2);
                break;
            default:
                throw new IllegalArgumentException();
        }
        this.role=role;
        switch(mode){
            case "time":
                this.time=true;
                break;
            case "depth":
                this.time=false;
                break;
            default:
                throw new IllegalArgumentException();
        }
        if(param<=0)
            throw new IllegalArgumentException();
        else
            this.param=param;
        switch(prune){
            case "on":
                this.prune=true;
                break;
            case "off":
                this.prune=false;
                break;
            default:
                throw new IllegalArgumentException();
        }

    }

    //retorna 1 si gana p1,2 si gana p2, 3 si es empate
    public int play(){
        int turn=1;
        while(!board.isOver()){
            if(turn==1){
                p1.play();
                turn=2;
            }
            else{
                p2.play();
                turn=1;
            }
        }
        if(p1.getScore()>p2.getScore()){//gana 1
            return 1;
        }
        else if(p2.getScore()>p1.getScore()){//gana 2
            return 2;
        }
        else{//empate
            return 3;
        }
    }

}
