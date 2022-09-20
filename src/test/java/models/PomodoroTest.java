package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PomodoroTest {
    @Test
    void creation() {
        new Pomodoro();
    }

    @Test
    void getObjectives() {
        Pomodoro pomodoro = new Pomodoro();

        assertNotNull(pomodoro.getObjectives());
    }

    @Test
    void addObjective() {
        Pomodoro pomodoro = new Pomodoro();

        pomodoro.addObjective(new Objective("강의 반복 과제", 0));
        pomodoro.addObjective(new Objective("강의 반복 과제", 0));
        pomodoro.addObjective(new Objective("강의 반복 과제", 0));

        assertEquals(3, pomodoro.getObjectives().size());
    }

    @Test
    void getTasks() {
        Pomodoro pomodoro = new Pomodoro();

        assertNotNull(pomodoro.getTasks());
    }

    @Test
    void addTasks() {
        Pomodoro pomodoro = new Pomodoro();

        pomodoro.addTasks(new Task("강의 반복 과제", false));

        assertEquals(1, pomodoro.getTasks().size());
    }

    @Test
    void setTasks() {
        Pomodoro pomodoro = new Pomodoro();

        pomodoro.setQuantity(5);

        assertEquals(5, pomodoro.getQuantity());
    }
}