package tk.ynvaser.quiz.persistence.entity;

import javax.persistence.*;

@Entity
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "JSON")
    private String gameJson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameJson() {
        return gameJson;
    }

    public void setGameJson(String gameJson) {
        this.gameJson = gameJson;
    }
}
