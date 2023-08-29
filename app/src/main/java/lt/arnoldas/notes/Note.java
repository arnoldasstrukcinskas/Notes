package lt.arnoldas.notes;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class Note {

    private int id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    public Note(){
    }

    public Note(
            int id,
            String title,
            String description,
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setTitle(String title) {
        this.title = title;
        this.updateDate = LocalDateTime.now();
    }

    public void setDescription(String description) {
        this.description = description;
        this.updateDate = LocalDateTime.now();
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(
                "Title: %s\n Description: \n%s\n%s\n%s",
                this.title,
                this.description,
                this.creationDate,
                this.updateDate
        );
    }
}
