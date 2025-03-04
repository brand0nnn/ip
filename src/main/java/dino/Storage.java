package dino;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load(String filePath) {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) {
            return tasks;
        }
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine().trim();
                String[] parts = line.split("\\|", 5);
                switch (parts[0]) {
                case "T" -> tasks.add(new Todo(parts[2]));
                case "D" -> tasks.add(new Deadline(parts[2], parts[4]));
                case "E" -> tasks.add(new Event(parts[2], parts[3], parts[4]));
                }
                if (parts[1].equals("X")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        }
        return tasks;
    }

    public void save(String filePath, TaskList tasklist) {
        ArrayList<Task> tasks = tasklist.getTasks();
        File dir = new File("./data");
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                System.out.println("Error: Failed to make directory");
                return;
            }
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.getTypeIcon() + "|" + task.getStatusIcon() + "|" + task.description + "|" + task.getStartDate() + "|" + task.getEndDate() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
