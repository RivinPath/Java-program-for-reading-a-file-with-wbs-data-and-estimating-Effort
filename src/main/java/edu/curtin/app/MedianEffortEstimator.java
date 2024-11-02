package edu.curtin.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * AUTHOR: Rivin Pathirage
 * UNIT: Object Oriented Programming for Software Engineering
 * PURPOSE: Implementing the Median estimate calculation of the efforts
 * REFERENCES: 
 */

@SuppressWarnings("PMD.MissingOverride")
public class MedianEffortEstimator implements EffortEstimator
{
    public int estimateEffort(List<Integer> estimates) 
    {
        List<Integer> sortedEstimates = new ArrayList<>(estimates);
        Collections.sort(sortedEstimates); //Let's check
        int size = sortedEstimates.size();
        int result;
        if (size % 2 == 0) 
        {
            int mid1 = sortedEstimates.get(size / 2 - 1);
            int mid2 = sortedEstimates.get(size / 2);

            result = (mid1 + mid2) / 2;
            System.out.println("Estmated Effort is: "+result+"\n");
            return result;
        } 
        else 
        {

            result = sortedEstimates.get(size / 2);
            System.out.println("Estmated Effort is: "+result+"\n");
            return result;

            //return sortedEstimates.get(size / 2);
        }
    }
}
