package dino;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the storage of task data by reading from and writing to a file.
 */
public class Storage {
    String filePath;

    /**
     * Constructor for Storage
     *
     * @param filePath The path to the save file where the data of TaskList is stored
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads and stores the data of the save file specified by filePath, if any,
     * and returns it in a TaskList, else it returns an empty TaskList.
     *
     * @param filePath The path to the save file where the data of TaskList is stored
     * @return an ArrayList of Task objects containing the loaded data, or an empty ArrayList if no save data exists
     */
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

    /**
     * Saves the data from the program to a save file with location specified by filePath
     *
     * @param filePath The path of the save file where the data of TaskList is stored
     * @param tasklist The TaskList where the current data of the program is written
     */
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
