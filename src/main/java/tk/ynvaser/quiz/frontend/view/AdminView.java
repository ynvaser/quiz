package tk.ynvaser.quiz.frontend.view;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import tk.ynvaser.quiz.model.engine.Game;
import tk.ynvaser.quiz.model.quiz.Quiz;
import tk.ynvaser.quiz.service.CsvImporterService;
import tk.ynvaser.quiz.service.GameService;
import tk.ynvaser.quiz.service.QuizService;

@Route(value = "admin-view", layout = MainView.class)
@PageTitle("Adminisztráció")
@CssImport("./styles/views/quizname/quizname-view.css")
public class AdminView extends VerticalLayout {
    private final transient CsvImporterService csvImporterService;
    private final transient QuizService quizService;
    private final transient GameService gameService;

    private final Select<Quiz> labelSelect = new Select<>();
    private Button createGameButton = new Button("Create Game");

    @Autowired
    public AdminView(CsvImporterService csvImporterService, QuizService quizService, GameService gameService) {
        this.csvImporterService = csvImporterService;
        this.quizService = quizService;
        this.gameService = gameService;
        initVaadinLayout();
    }

    private void initVaadinLayout() {
        createFileUploader();
        createGameCreator();
    }

    private void createGameCreator() {
        labelSelect.setItems(quizService.getQuizes());
        labelSelect.setLabel("Elérhető kvízek");
        labelSelect.setItemLabelGenerator(Quiz::toString);
        labelSelect.
                addValueChangeListener(this::handleValueChange);
        add(labelSelect);
    }

    private void createFileUploader() {
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setMaxFiles(1);
        upload.setDropLabel(new Label("Kvíz feltöltése .csv file-ból (';' delimiter, max 100MB)"));
        upload.setAcceptedFileTypes(".csv");
        upload.setMaxFileSize(100000);
        Div errorContainer = new Div();

        upload.addFileRejectedListener(event -> {
            remove(errorContainer);
            Paragraph component = new Paragraph(event.getErrorMessage());
            errorContainer.add(component);
            add(errorContainer);
        });

        upload.addFinishedListener(event -> {
            csvImporterService.importFromCsv(event.getFileName(), buffer.getInputStream());
            labelSelect.setItems(quizService.getQuizes());
            remove(errorContainer);
        });

        add(upload);
    }

    private void handleValueChange(AbstractField.ComponentValueChangeEvent<Select<Quiz>, Quiz> event) {
        remove(createGameButton);
        createGameButton = new Button("Create Game");
        createGameButton.addClickListener(click -> gameService.createGame(new Game(event.getValue())));
        add(createGameButton);
    }
}
