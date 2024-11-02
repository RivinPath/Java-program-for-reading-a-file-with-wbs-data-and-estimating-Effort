package edu.curtin.app;

import java.util.List;
/*
 * AUTHOR: Rivin Pathirage
 * UNIT: Object Oriented Programming for Software Engineering
 * PURPOSE: Implementing the highest estimate calculation of the efforts
 * REFERENCES: 
 */

@SuppressWarnings("PMD.MissingOverride")
public class HighestEffortEstimator implements EffortEstimator
{
    public int estimateEffort(List<Integer> estimates) 
    {
        int highest = Integer.MIN_VALUE;   //Let's check
        for (int estimate : estimates) 
        {
            if (estimate > highest) 
            {
                highest = estimate;
            }
        }

        System.out.println("Estimated Effort is: "+highest+"\n");

        return highest;
    }
}
