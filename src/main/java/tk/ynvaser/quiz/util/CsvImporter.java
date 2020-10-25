package tk.ynvaser.quiz.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.ynvaser.quiz.model.quiz.Category;
import tk.ynvaser.quiz.model.quiz.Question;
import tk.ynvaser.quiz.model.quiz.Quiz;

import java.io.*;
import java.nio.charset.StandardCharsets;

public final class CsvImporter {
    public static char DELIMITER = ';';
    private static Logger LOG = LoggerFactory.getLogger(CsvImporter.class);

    public static Quiz importFromCsv(InputStream inputStream) {
        try (InputStreamReader input = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            CSVParser csvParser = CSVFormat.newFormat(DELIMITER).withFirstRecordAsHeader().parse(input);
            Quiz quiz = new Quiz();
            csvParser.forEach(record->parseLine(quiz, record));
            return quiz;
        } catch (FileNotFoundException e) {
            LOG.error("File nem található!");
        } catch (IOException e) {
            LOG.error("Olvasási hiba!");
        } catch (NumberFormatException e) {
            LOG.error("Az egyik pont értékedbe szöveg csúszott, vagy nem '{}' karakterrel választottad el a bejegyzéseid!", DELIMITER);
        }
        return null;
    }

    private static void parseLine(Quiz quiz, CSVRecord record) {
        String categoryName = record.get("Kategória");
        Category category = quiz.getCategoryByName(categoryName);
        if (category == null) {
            category = new Category(categoryName);
            quiz.addCategory(categoryName, category);
        }
        String point = record.get("Pont");
        String name = record.get("Név");
        String text = record.get("Szöveg");
        String answer = record.get("Válasz");
        int pointValue = Integer.parseInt(point);
        Question question = Question.builder()
                .points(pointValue)
                .name(name)
                .text(text)
                .answerText(answer)
                .build();
        category.addQuestion(question);
    }
}
