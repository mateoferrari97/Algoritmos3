package modelo.questions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import exceptions.InvalidJsonRecognizerClassException;
import exceptions.InvalidSizeException;
import modelo.Player;
import modelo.Points;
import modelo.options.Option;
import modelo.scorers.QuestionScorer;

import java.util.ArrayList;
import java.util.List;

public class GroupChoiceQuestion extends Question {
    protected List<Option> optionsB;

    public GroupChoiceQuestion(String text, List<Option> options, QuestionScorer scorer){}

    public GroupChoiceQuestion(String test, List<Option> optionsA, List<Option> optionsB, QuestionScorer scorer) throws InvalidSizeException {
        super();

        Integer optionsSize = optionsA.size() + optionsB.size();
        if (optionsSize < 2 || optionsSize > 6) {
            String error = "invalid options size: want minimum 2, maximum 6. got: " + optionsSize;
            throw new InvalidSizeException(error);
        }

        this.options = optionsA;
        this.optionsB = optionsB;
        this.text = text;
        this.scorer = scorer;
        this.points = new Points();
    }

    public void score(Player player, List<Option> playerOptionsOne, List<Option> playerOptionsTwo){
        Integer optionsASize = playerOptionsOne.size();
        Integer optionsBSize = playerOptionsTwo.size();

        Points pointsA = new Points();
        Points pointsB = new Points();

        for (Option aOption : playerOptionsOne) {aOption.calculatePoints(scorer, pointsA);}
        for (Option aOption : playerOptionsTwo) { aOption.calculatePoints(scorer, pointsB);}

        if (pointsA.getPoints() != optionsASize && pointsA.getPoints() != 0) {
            this.points.changeScoreToZero();
            return;
        }
        if (pointsB.getPoints() != optionsBSize && pointsB.getPoints() != 0) {
            this.points.changeScoreToZero();
            return;
        }
        this.points.gainAPoint();

        scorer.score(player,this.points);
    }

    public static Question unmarshal(JsonObject json) throws InvalidJsonRecognizerClassException, InvalidSizeException {
        try {

            String text = json.get("text").getAsString();
            String scorerString = json.get("scorer").getAsString();

            List<Option> options = new ArrayList<Option>();
            JsonArray arrayOptions = json.getAsJsonArray("options");
            for (JsonElement jsonOption : arrayOptions) {
                Option option = Option.unmarshal(jsonOption.getAsJsonObject());
                options.add(option);
            }

            QuestionScorer questionScorer = selectScorer(scorerString);

            //Question question = question(text, options, questionScorer);
            return new GroupChoiceQuestion(text, options, questionScorer);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void score(Player player, List<Option> playerAnswers) {
        
    }
}

