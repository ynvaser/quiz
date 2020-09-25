package tk.ynvaser.quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.ynvaser.quiz.service.QuizService;

@Configuration
public class SpringConfig {

    @Bean
    public QuizService quizService() {
        return new QuizService();
    }
}
