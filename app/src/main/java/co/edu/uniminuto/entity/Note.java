package co.edu.uniminuto.entity;

public class Note {
    private String title;
    private String descriptions;
    private String dateNote;

    public Note(String title, String descriptions, String dateNote) {
        this.title = title;
        this.descriptions = descriptions;
        this.dateNote = dateNote;
    }

    public Note() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getDateNote() {
        return dateNote;
    }

    public void setDateNote(String dateNote) {
        this.dateNote = dateNote;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Note{");
        sb.append("title='").append(title).append('\'');
        sb.append(", descriptions='").append(descriptions).append('\'');
        sb.append(", dateNote='").append(dateNote).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
