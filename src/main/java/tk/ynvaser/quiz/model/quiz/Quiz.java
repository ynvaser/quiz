package tk.ynvaser.quiz.model.quiz;

import java.util.HashMap;
import java.util.Map;

public class Quiz {
    private Map<String, Category> categories;

    public Quiz() {
        categories = new HashMap<>();
    }

    public void addCategory(String name, Category category) {
        categories.put(name, category);
    }

    public Category getCategoryByName(String name) {
        return categories.get(name);
    }
}
