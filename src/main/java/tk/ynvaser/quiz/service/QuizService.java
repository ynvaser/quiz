package tk.ynvaser.quiz.service;

import tk.ynvaser.quiz.model.quiz.Quiz;
import tk.ynvaser.quiz.util.CsvImporter;

import java.util.ArrayList;
import java.util.List;

public class QuizService {
    private List<Quiz> quizes;

    public QuizService() {
        quizes = new ArrayList<>();
    }

    public void importQuiz(String filePath) {
        Quiz quiz = CsvImporter.importFromCsv(filePath);
        quizes.add(quiz);
    }
}
