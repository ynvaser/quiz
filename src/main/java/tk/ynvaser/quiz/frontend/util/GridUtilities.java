package tk.ynvaser.quiz.frontend.util;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.util.ArrayList;
import java.util.List;

public class GridUtilities {
    private GridUtilities() {
    }

    public static <T> List<T> getItems(Grid<T> grid) {
        if (grid.getDataProvider() instanceof ListDataProvider) {
            @SuppressWarnings("unchecked")
            ListDataProvider<T> dataProvider = (ListDataProvider<T>) grid.getDataProvider();
            return new ArrayList<>(dataProvider.getItems());
        } else {
            throw new IllegalStateException("Unexpected Data Provider");
        }
    }
}
