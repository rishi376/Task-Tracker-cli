import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private static int lastId = 0;
    private int id;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private final static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Task(String description) {
        this.id = ++lastId;
        this.description = description;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void updateDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public Status getStatus() {
        return status;
    }

    public void updateStatus(Status status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static Task fromJson(String jsonTask) {
        String[] keyValue = jsonTask.split(",");
        String id = keyValue[0].split(":")[1].strip();
        String description = keyValue[1].split(":")[1].strip();
        String createdAt = keyValue[2].split(":", 2)[1].strip();
        String updateAt = keyValue[3].split(":", 2)[1].strip();
        String status = keyValue[4].split(":")[1].strip().toUpperCase();

        Task newTask = new Task(description);

        newTask.id = Integer.parseInt(id);
        newTask.status = Status.valueOf(status.toUpperCase().replace("-", "_"));
        newTask.createdAt  = LocalDateTime.parse(createdAt, formatter);
        newTask.updatedAt = LocalDateTime.parse(updateAt, formatter);

        return newTask;
    }

    @Override
    public String toString() {
        return "id: " + id + " \ndescription: " + description + " \ncreated at: " + createdAt + " \nupdated on: " + updatedAt + " \nstatus: " + status;
    }
}
