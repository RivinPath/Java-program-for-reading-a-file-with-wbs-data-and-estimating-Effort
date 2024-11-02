package edu.curtin.app;

import java.util.List;

/*
 * AUTHOR: Rivin Pathirage
 * UNIT: Object Oriented Programming for Software Engineering
 * PURPOSE: Implementing the default wauy of calculating the efforts
 * REFERENCES: 
 */

@SuppressWarnings("PMD.MissingOverride")
public class AverageEffortEstimator implements EffortEstimator
{

    public int estimateEffort(List<Integer> estimates) 
    {
        int sum = 0;
        int result;
        for (int estimate : estimates) 
        {
            sum += estimate;
        }

        result = sum / estimates.size();
        System.out.println("Estimated Effort is: "+result+"\n");
        //return sum / estimates.size();
        return result;
    }
}
