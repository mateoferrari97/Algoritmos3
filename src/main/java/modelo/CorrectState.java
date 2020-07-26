package modelo;

public class CorrectState extends State {

    public CorrectState() {
       super();
       this.points = 0;
    }

    public void scorePlayer(ScoreBehavior score, Player player){
        score.scorePlayer(player,this.points);
    }

    public String getCorrectAnswer(String text) {
        return text;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
