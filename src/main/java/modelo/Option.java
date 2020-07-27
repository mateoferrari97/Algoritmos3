package modelo;

public class Option {
    private String text;
    private IOptionScorer scorer;

    public Option(String text, IOptionScorer scorer) {
        this.text = text;
        this.scorer = scorer;
    }

    public void score(Player player) {
        scorer.score(player);
    }
}
