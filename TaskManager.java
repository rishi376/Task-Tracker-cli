import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskManager {

    private final Path FILE_PATH = Path.of("tasks.json");

    List<Task> tasks = new ArrayList<Task>();

    public TaskManager() {
        this.tasks = getTasks();
    }

    public void add(String description) {
        tasks.add(new Task(description));
        saveTasks();
        System.out.println("Inside add " + description);
    }

    public void update(int id, String description) {
        tasks.stream().filter(task -> task.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No task found with id " + id))
                .updateDescription(description);

        saveTasks();
        System.out.println("Inside update " + id + " " + description );
    }

    public void delete(int id) {
        Task filteredTask = tasks.stream().filter(t -> t.getId() == id).findFirst().orElseThrow(() -> new IllegalArgumentException("No task found with id " + id));
        tasks.remove(filteredTask);
        saveTasks();
        System.out.println("Inside delete " + id);
    }

    public void listTodos(String status) {
        if(tasks.isEmpty()) {
            System.out.println("There are no pending tasks!!");
        }
        else if(status.equals("all")) {
            tasks.forEach(System.out::println);
        }
        else {
            List<Task> filteredTasks = tasks.stream().filter(t -> t.getStatus().toString().equals(status)).toList();
            if(filteredTasks.isEmpty()) {
                System.out.println("There are no tasks with status " + status);
            }
            filteredTasks.forEach(System.out::println);
        }
        System.out.println("Inside list " + status);
    }

    public void updateStatus(int id, Status status) {
        tasks.stream().filter(t -> t.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No task found with id " + id))
                .updateStatus(status);
        saveTasks();
        System.out.println("Inside update status " + id + " " + status);
    }

    public List<Task> getTasks() {

        if(!Files.exists(FILE_PATH)) {
            return new ArrayList<>();
        }

        List<Task> savedTasks = new ArrayList<>();

        try {
            String jsonContent = Files.readString(FILE_PATH);
            String[] jsonTasks = jsonContent.replace("[", "").replace("]", "").split("},");
            //Arrays.stream(tasks).forEach(System.out::println);

            for (String task : jsonTasks) {
                task = task.replace("{", "").replace("}", "").replace("\"", "");
                Task newTask = Task.fromJson(task);
                savedTasks.add(newTask);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return savedTasks;
    }

    public void saveTasks() {
        StringBuilder json = new StringBuilder("[\n");
        int checkIfLast = 0;
        for (Task task : tasks) {
            json.append("{\n");
            json.append("\"id\": ").append(task.getId()).append(",\n");
            json.append("\"description\": ").append("\"").append(task.getDescription()).append("\",\n");
            json.append("\"createdAt\": ").append("\"").append(task.getCreatedAt()).append("\",\n");
            json.append("\"updatedOn\": ").append("\"").append(task.getUpdatedAt()).append("\",\n");
            json.append("\"status\": ").append("\"").append(task.getStatus());
            json.append("\"\n}");
            if(checkIfLast++ != tasks.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("]");
        try {
            Files.writeString(FILE_PATH, json);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
