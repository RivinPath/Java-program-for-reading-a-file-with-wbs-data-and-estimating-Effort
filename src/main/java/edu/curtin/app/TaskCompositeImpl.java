package edu.curtin.app;

import java.util.ArrayList;
import java.util.List;

/*
 * AUTHOR: Rivin Pathirage
 * UNIT: Object Oriented Programming for Software Engineering
 * PURPOSE: Implementing the the composite node of the composite tree
 * REFERENCES: 
 */

@SuppressWarnings("PMD.MissingOverride")
public class TaskCompositeImpl implements TaskComposite
{
    /* default */Task task;
    /* default */List<TaskComposite> subtasks;

    //Default Constructor
    public TaskCompositeImpl(Task task) 
    {
        this.task = task;
        this.subtasks = new ArrayList<>();
    }

    //PURPOSE: Adding a composite task
    public void addTask(TaskComposite subtask) 
    {
        subtasks.add(subtask);
    }

    //PURPOSE: removing a composite task
    public void removeTask(TaskComposite subtask) 
    {
        subtasks.remove(subtask);
    }

    //PURPOSE: Accessing a composite task
    public TaskComposite getTask(int index) 
    {
        return subtasks.get(index);
    }

    //PURPOSE: Accessing the effort of a composite task
    public int getEffort() 
    {
        int totalEffort = task.effort;
        for (TaskComposite subtask : subtasks) 
        {
            totalEffort += subtask.getEffort();
        }
        return totalEffort;
    }
}
