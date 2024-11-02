package edu.curtin.app;

/*
 * AUTHOR: Rivin Pathirage
 * UNIT: Object Oriented Programming for Software Engineering
 * PURPOSE: Implementing the leaf node implementation for the composite tree
 * REFERENCES: 
 */

@SuppressWarnings("PMD.MissingOverride")
public class TaskLeaf implements TaskComposite
{
    /* default */Task task;

    //Default Constructor
    public TaskLeaf(Task task) 
    {
        this.task = task;
    }

    public void addTask(TaskComposite task) 
    {
        throw new UnsupportedOperationException("Cannot add tasks to a leaf task");
    }

    public void removeTask(TaskComposite task) 
    {
        throw new UnsupportedOperationException("Cannot remove tasks from a leaf task");
    }

    public TaskComposite getTask(int index) 
    {
        throw new IndexOutOfBoundsException("Leaf task has no subtasks");
    }

    //PURPOSE: Accessing the effort of a leaf node task
    public int getEffort() 
    {
        return task.effort;
    }
}
