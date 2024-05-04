package co.edu.uniminuto.entity;

public class Tasks {
    private  String title_list;
    private String title_tasks;
    private  String description_tasks;
    private String date_tasks;
    private String user_id;

    public Tasks(String title_list, String title_tasks, String description_tasks, String date_tasks, String user_id) {
        this.title_list = title_list;
        this.title_tasks = title_tasks;
        this.description_tasks = description_tasks;
        this.date_tasks = date_tasks;
        this.user_id = user_id;
    }

    public Tasks() {
    }

    public String getTitle_list() {
        return title_list;
    }

    public void setTitle_list(String title_list) {
        this.title_list = title_list;
    }

    public String getTitle_tasks() {
        return title_tasks;
    }

    public void setTitle_tasks(String title_tasks) {
        this.title_tasks = title_tasks;
    }

    public String getDescription_tasks() {
        return description_tasks;
    }

    public void setDescription_tasks(String description_tasks) {
        this.description_tasks = description_tasks;
    }

    public String getDate_tasks() {
        return date_tasks;
    }

    public void setDate_tasks(String date_tasks) {
        this.date_tasks = date_tasks;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tasks{");
        sb.append("title_list='").append(title_list).append('\'');
        sb.append(", title_tasks='").append(title_tasks).append('\'');
        sb.append(", description_tasks='").append(description_tasks).append('\'');
        sb.append(", date_tasks='").append(date_tasks).append('\'');
        sb.append(", user_id='").append(user_id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
