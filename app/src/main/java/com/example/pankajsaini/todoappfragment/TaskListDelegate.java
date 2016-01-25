package com.example.pankajsaini.todoappfragment;

import java.util.ArrayList;

/**
 * Created by pankaj.saini on 20/01/16.
 */
public interface TaskListDelegate {
    void switchToAddTaskFragment();
    void switchToTaskDetails(Task task);
}
