package tk.ynvaser.quiz.frontend.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import tk.ynvaser.quiz.model.quiz.Quiz;
import tk.ynvaser.quiz.service.QuizService;

@Route
public class QuizMasterView extends VerticalLayout {

    private final QuizService quizService;

    private Select<Quiz> labelSelect;

    @Autowired
    public QuizMasterView(QuizService quizService) {
        this.quizService = quizService;
        initVaadinLayout();
    }

    private void initVaadinLayout() {
        getFileUploader();
        getQuizSelector();
    }

    private void getQuizSelector() {
        if (labelSelect != null) {
            remove(labelSelect);
        }
        labelSelect = new Select<>();
        labelSelect.setItems(quizService.getQuizes());
        labelSelect.setLabel("Elérhető kvízek");
        labelSelect.setItemLabelGenerator(Quiz::toString);
        add(labelSelect);
    }

    private void getFileUploader() {
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setMaxFiles(1);
        upload.setDropLabel(new Label("Kvíz feltöltése .csv file-ból (';' delimiter, max 100MB)"));
        upload.setAcceptedFileTypes(".csv");
        upload.setMaxFileSize(100000);
        Div output = new Div();

        upload.addFileRejectedListener(event -> {
            Paragraph component = new Paragraph();
            showOutput(event.getErrorMessage(), component, output);
        });

        upload.addFinishedListener(event -> {
            quizService.importQuiz(buffer.getInputStream());
            getQuizSelector();
        });

        add(upload, output);
    }

    private void showOutput(String text, Component content,
                            HasComponents outputContainer) {
        HtmlComponent p = new HtmlComponent(Tag.P);
        p.getElement().setText(text);
        outputContainer.add(p);
        outputContainer.add(content);
    }
}
