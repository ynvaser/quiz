package tk.ynvaser.quiz.persistence.converter;

import tk.ynvaser.quiz.model.engine.Game;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JsonToGameConverter implements AttributeConverter<Game, String> {
    @Override
    public String convertToDatabaseColumn(Game attribute) {
        return attribute.toJson();
    }

    @Override
    public Game convertToEntityAttribute(String dbData) {
        String json = dbData.replace("\\", "");
        if (json.startsWith("\"") && json.endsWith("\"")) {
            json = json.substring(1, json.length() - 1);
        }
        return Game.fromJson(json);
    }
}
