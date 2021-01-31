package tk.ynvaser.quiz.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.ynvaser.quiz.model.quiz.Quiz;
import tk.ynvaser.quiz.persistence.repository.QuizRepository;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class QuizService {
    private final QuizRepository quizRepository;

    @Transactional
    public List<Quiz> getQuizes() {
        List<Quiz> quizes = new ArrayList<>();
        quizRepository.findAll().forEach(quizEntity -> quizes.add(Quiz.fromEntity(quizEntity)));
        return quizes;
    }
}
