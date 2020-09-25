package tk.ynvaser.quiz.model.quiz;

public class Question {
    private int points;
    private String name;
    private String text;
    private String answerText;

    public Question(int points, String name, String text, String answerText) {
        this.points = points;
        this.name = name;
        this.text = text;
        this.answerText = answerText;
    }
}
