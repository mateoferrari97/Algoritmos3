package modelo;
import java.util.ArrayList;
import java.util.List;

public class BooleanQuestion extends Question {
    public BooleanQuestion(String text, List<Option> options, QuestionScorer scorer) {
        super();
        this.options = options;
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
    }

    public void score(Player player) {
        for(Option aOption : options){
            aOption.calculatePoints(scorer, this.points);
        }
        scorer.score(player,this.points);
    }
}