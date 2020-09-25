package tk.ynvaser.quiz.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.ynvaser.quiz.model.quiz.Category;
import tk.ynvaser.quiz.model.quiz.Question;
import tk.ynvaser.quiz.model.quiz.Quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class CsvImporter {
    private static Logger LOG = LoggerFactory.getLogger(CsvImporter.class);

    public static Quiz importFromCsv(String filePath) {
        Quiz result = new Quiz();
        Path pathToFile = Paths.get(filePath);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            int lineNumber = 0;
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(";");
                if (attributes.length == 5) {
                    Category category = result.getCategoryByName(attributes[0]);
                    if (category == null) {
                        category = new Category(attributes[0]);
                    }
                    category.addQuestion(new Question(Integer.parseInt(attributes[1]), attributes[2], attributes[3], attributes[4]));
                    line = br.readLine();
                    lineNumber++;
                } else {
                    throw new RuntimeException("Érvénytelen kérdés (nincs elég cella)! Sor: " + lineNumber);
                }
            }
        } catch (IOException e) {
            LOG.error("Importálás sikertelen!", e);
        }
        return result;
    }
}
