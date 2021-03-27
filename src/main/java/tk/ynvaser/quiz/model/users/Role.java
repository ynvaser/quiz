package tk.ynvaser.quiz.model.users;

public enum Role {
    ;
    public static final String ADMIN = "admin";
    public static final String QUIZ_MASTER = "quizMaster";
    public static final String TEAM_LEADER = "teamLeader";
    public static final String USER = "user";

    public static String[] getAllRoles() {
        return new String[]{QUIZ_MASTER, TEAM_LEADER, USER, ADMIN};
    }
}