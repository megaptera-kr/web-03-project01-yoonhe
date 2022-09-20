package models;

import java.util.ArrayList;
import java.util.List;

public class Pomodoro {
    private List<Objective> objectives = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private int quantity;

    public List<Objective> getObjectives() {
        return objectives;
    }

    public void addObjective(Objective objective) {
        objectives.add(objective);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTasks(Task task) {
        tasks.add(task);
    }

    public void setQuantity(int i) {
        quantity = i;
    }

    public int getQuantity() {
        return quantity;
    }
}