package tk.ynvaser.quiz.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.ynvaser.quiz.persistence.entity.CategoryEntity;
import tk.ynvaser.quiz.persistence.entity.QuestionEntity;
import tk.ynvaser.quiz.persistence.entity.QuizEntity;
import tk.ynvaser.quiz.persistence.repository.QuizRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
public class CsvImporterService {
    public static final char DELIMITER = ';';
    private final QuizRepository quizRepository;

    @Transactional
    public void importFromCsv(String name, InputStream inputStream) {
        try (InputStreamReader input = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            CSVParser csvParser = CSVFormat.newFormat(DELIMITER).withFirstRecordAsHeader().parse(input);
            QuizEntity quizEntity = new QuizEntity();
            quizEntity.setName(name);
            csvParser.forEach(record -> parseLine(quizEntity, record));
            quizRepository.save(quizEntity);
        } catch (FileNotFoundException e) {
            log.error("File nem található!");
        } catch (IOException e) {
            log.error("Olvasási hiba!");
        } catch (NumberFormatException e) {
            log.error("Az egyik pont értékedbe szöveg csúszott, vagy nem '{}' karakterrel választottad el a bejegyzéseid!", DELIMITER);
        }
    }

    private void parseLine(QuizEntity quizEntity, CSVRecord record) {
        String categoryName = record.get("Kategória");
        CategoryEntity categoryEntity = quizEntity.findCategoryByName(categoryName).orElse(null);
        if (categoryEntity == null) {
            categoryEntity = new CategoryEntity();
            categoryEntity.setName(categoryName);
            quizEntity.getCategories().add(categoryEntity);
        }
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setName(record.get("Név"));
        questionEntity.setText(record.get("Szöveg"));
        questionEntity.setAnswerText(record.get("Válasz"));
        questionEntity.setPoints(Integer.parseInt(record.get("Pont")));
        categoryEntity.getQuestions().add(questionEntity);
    }
}
