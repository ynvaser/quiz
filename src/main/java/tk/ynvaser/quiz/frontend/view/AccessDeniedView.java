package tk.ynvaser.quiz.frontend.view;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.*;
import com.vaadin.flow.templatemodel.TemplateModel;
import tk.ynvaser.quiz.frontend.exceptions.AccessDeniedException;

import javax.servlet.http.HttpServletResponse;

@Tag("access-denied-view")
@JsModule("./styles/views/errors/access-denied-view.js")
@ParentLayout(MainView.class)
@PageTitle("Access Denied")
public class AccessDeniedView extends PolymerTemplate<TemplateModel> implements HasErrorParameter<AccessDeniedException> {

    @Override
    public int setErrorParameter(BeforeEnterEvent beforeEnterEvent, ErrorParameter<AccessDeniedException> errorParameter) {
        return HttpServletResponse.SC_FORBIDDEN;
    }
}