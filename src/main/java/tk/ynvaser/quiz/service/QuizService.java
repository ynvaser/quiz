package tk.ynvaser.quiz.service;

import lombok.Getter;
import tk.ynvaser.quiz.model.quiz.Quiz;
import tk.ynvaser.quiz.util.CsvImporter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Getter
public class QuizService {
    private List<Quiz> quizes;

    public QuizService() {
        quizes = new ArrayList<>();
    }

    public void importQuiz(InputStream inputStream) {
        Quiz quiz = CsvImporter.importFromCsv(inputStream);
        quizes.add(quiz);
    }
}
