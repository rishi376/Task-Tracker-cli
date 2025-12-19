public class TaskTrackerCLI {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        if(args.length < 1) {
            System.out.println("Usage: TrackTrackerCli - ");
            System.out.println("    To add a new Todo - add <description>");
            System.out.println("    To update an existing Todo - update <id> <description>");
            System.out.println("    To delete an existing Todo - delete <id>");
            System.out.println("    To list all the todos based - list");
            System.out.println("    To list all the todos based on status[all, done, todo, in-progress] - list <status>");
            System.out.println("    To update status of an existing task options[mark-done, mark-in-progress, mark-todo] - [option] <id>");
            return;
        }

        switch (args[0]) {
            case "add":
                if(args.length < 2) {
                    System.out.println("Usage: TaskTrackerCLI add <description>");
                    return;
                }
                taskManager.add(args[1]);
                break;
            case "update":
                if(args.length < 3) {
                    System.out.println("Usage: TaskTrackerCLI update <id> <new description>");
                    return;
                }

                try {
                    taskManager.update(Integer.parseInt(args[1]), args[2]);
                    System.out.println("Successfully updated task with id " + args[1]);
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "delete":
                if(args.length < 2) {
                    System.out.println("Usage: TaskTrackerClI delete <id>");
                    break;
                }
                try {
                    taskManager.delete(Integer.parseInt(args[1]));
                }catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

                break;
            case "list":
                if(args.length < 2) {
                    taskManager.listTodos("all");
                    break;
                }
                Status filterStatus;
                try {
                    filterStatus = Status.valueOf(args[1].toUpperCase().replace("-", "_"));
                } catch (IllegalArgumentException e) {
                    System.out.println("Usage: TaskTrackerClI <status [options = done, in-progress, todo]>");
                    return;
                }
                taskManager.listTodos(filterStatus.toString());
                break;
            case "mark-done":
                if(args.length < 2) {
                    System.out.println("usage: TrackTrackerCLI mark-done <id>");
                    break;
                }
                try {
                    taskManager.updateStatus(Integer.parseInt(args[1]), Status.DONE);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

                break;
            case "mark-in-progress":
                if(args.length < 2) {
                    System.out.println("usage: TrackTrackerCLI mark-in-progress <id>");
                    break;
                }
                try {
                    taskManager.updateStatus(Integer.parseInt(args[1]), Status.IN_PROGRESS);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "mark-todo":
                if(args.length < 2) {
                    System.out.println("usage: TrackTrackerCLI mark-todo <id>");
                    break;
                }
                try {
                    taskManager.updateStatus(Integer.parseInt(args[1]), Status.TODO);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Usage: TrackTrackerCli - ");
                System.out.println("    To add a new Todo - add <description>");
                System.out.println("    To update an existing Todo - update <id> <description>");
                System.out.println("    To delete an existing Todo - delete <id>");
                System.out.println("    To list all the todos based - list");
                System.out.println("    To list all the todos based on status[all, done, todo, in-progress] - list <status>");
                System.out.println("    To update status of an existing task options[mark-done, mark-in-progress, mark-todo] - [option] <id>");
        }
    }
}
