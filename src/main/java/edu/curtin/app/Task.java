package edu.curtin.app;

import java.util.ArrayList;
import java.util.List;

/*
 * AUTHOR: Rivin Pathirage
 * UNIT: Object Oriented Programming for Software Engineering
 * PURPOSE: Implementing the data structure of the task
 * REFERENCES: 
 */

public class Task 
{
    /* default */String id;
    /* default */String description;
    /* default */int effort;
    /* default */Task parent;
    /* default */List<Task> subtasks;

    //Default Constructor
    public Task(String id, String description, int effort, Task parent) 
    {
        this.id = id;
        this.description = description;
        this.effort = effort;
        this.parent = parent;
        this.subtasks = new ArrayList<>();
    }

    //PURPOSE: Adding a subtask to a task
    public void addSubtask(Task subtask) 
    {
        subtasks.add(subtask);
        subtask.parent = this;
    }
    
    //PURPOSE: Accessing the effort of a task
    public int getEffort() 
    {
        return effort;
    }
}
