package edu.curtin.app;

/*
 * AUTHOR: Rivin Pathirage
 * UNIT: Object Oriented Programming for Software Engineering
 * PURPOSE: Implementing the interface for the composite tree
 * REFERENCES: 
 */

interface TaskComposite 
{
    void addTask(TaskComposite task);

    void removeTask(TaskComposite task);

    TaskComposite getTask(int index);
    
    int getEffort();
}
