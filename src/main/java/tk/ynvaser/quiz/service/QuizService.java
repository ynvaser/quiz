package tk.ynvaser.quiz.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.ynvaser.quiz.model.quiz.Quiz;
import tk.ynvaser.quiz.persistence.entity.QuizEntity;
import tk.ynvaser.quiz.persistence.repository.QuizRepository;
import tk.ynvaser.quiz.util.CsvImporter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public void importQuiz(InputStream inputStream) {
        Quiz quiz = CsvImporter.importFromCsv(inputStream);
        quizRepository.save()
    }
}
