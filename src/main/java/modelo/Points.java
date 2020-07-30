package modelo;

public class Points {
    private Integer points;
    private Integer factor;

    public Points(){
        this.points = 0;
        this.factor = 1;
    }

    public void increasePoints() {
        this.points++;
    }

    public void givePointsToPlayer(Player player) {
        player.gainAmountOfPoints(this.points * this.factor);
    }

    public void decreasePoints() {
        this.points--;
    }

    public void changeScoreToZero() {
        this.factor = 0;
    }

    public void gainAPoint() {
        this.points = 1;
    }
}