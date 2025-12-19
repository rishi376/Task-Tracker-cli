# Task Tracker cli Application
This project is from Roadmap.sh
#### Project Link: https://roadmap.sh/projects/task-tracker

## Task tracker is a project used to track and manage your tasks

This is a simple Java Command Line Interface(CLI) application for managing task

## Features of this application

- Add, Update, and Delete tasks

- Mark a task as in progress or done

- List all tasks

- List all tasks that are done

- List all tasks that are not done

- List all tasks that are in progress

- This is all done directly in the Terminal

## Installation

1. **Clone the Repository:**

   ```bash
    git clone https://github.com/rishi376/Task-Tracker-cli.git
    cd Task-Tracker-cli

2. **Compile the Code:**
   ```bash
   javac Status.java Task.java TaskManager.java TaskTrackerCLI.java
   
3. **Run the application:**
   ```bash
   java TaskTrackerCLI <command> [arguments]

## Usage

  ```bash
  # Lists all the available usage
  java TaskTrackerCLI

  # Adding a new task
  java TaskTrackerCLI add "Buy groceries"
  # Output: Task added successfully (ID: 1)

  # Updating and deleting tasks
  java TaskTrackerCLI update 1 "Buy groceries and cook dinner"
  java TaskTrackerCLI delete 1

  # Marking a task as in progress or done
  java TaskTrackerCLI mark-in-progress 1
  java TaskTrackerCLI mark-done 1

  # Listing all tasks
  java TaskTrackerCLI list

  # Listing tasks by status
  java TaskTrackerCLI list done
  java TaskTrackerCLI list todo
  java TaskTrackerCLI list in-progress
```
  
  
