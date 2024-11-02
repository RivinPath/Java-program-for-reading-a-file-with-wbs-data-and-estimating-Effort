package edu.curtin.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.*;

/*
 * AUTHOR: Rivin Pathirage
 * UNIT: Object Oriented Programming for Software Engineering
 * PURPOSE: Managing the WBS
 * REFERENCES: 
 */

public class WBS
{


    /* default */Map<String, Task> tasks = new HashMap<>();
    /* default */EffortEstimator estimator;
    /* default */int numEstimates;
    /* default */private static final Logger logger = Logger.getLogger(/*edu.curtin.app.*/WBS.class.getName());
    

    //Default Constructor
    public WBS() 
    {
        this.estimator = new AverageEffortEstimator();
        this.numEstimates = 3;
    }

    /*
    * PURPOSE: executing the program
    */
    public static void main(String[] args)
    {

        

        
        
        // If you wish to change the name and/or package of the class containing 'main()', you 
        // will also need to update the 'mainClass = ...' line in build.gradle.

        if (args.length < 1 )
        {
            System.out.println("Insufficient arguments");
        }
        else
        {
            String filename = args[0];
            //System.out.println(filename);
            //logger.info("My variable value: " + filename);
            //logger.log(Level.INFO, "My variable value: " + filename);
            logger.info(() ->"My variable value: " + filename);
            

            WBS wbs = new WBS();
            
            
            wbs.loadFromFile(filename);

            wbs.displayWBS();

            int displayTotEffort = wbs.getTotalEffort();
            System.out.println("\nTotal known effort: "+displayTotEffort+"\n");

            int displayUnknownCount = wbs.getUnknownTaskCount();
            System.out.println("Unknown tasks: "+displayUnknownCount+"\n");


            boolean keepMenu = true;
            Scanner sc = new Scanner(System.in);

            while(keepMenu == true)
            {
                System.out.println("********************************MENU*****************************");
                System.out.println("1) Estimate Effort of a task*************************************");
                System.out.println("2) Configure*****************************************************");
                System.out.println("3) Exit**********************************************************");
                System.out.println("*****************************************************************\n");

                
                System.out.println("Please enter option: ");
                int option = sc.nextInt();
                //sc.nextLine();
                

                if (option == 1)
                {
                    
                    Scanner strScanner = new Scanner(System.in);
                    System.out.println("Please enter task ID: ");
                    String idForEstimate = strScanner.nextLine();
                    wbs.estimateEffort(idForEstimate,filename);
                    //strScanner.close();
                }
                else if (option == 2)
                {
                    System.out.println("\n");
                    wbs.configureSettings(filename);
                }
                else if (option == 3)
                {
                    System.out.println("\nGood Bye!");
                    keepMenu = false;
                }
                else
                {
                    System.out.println("Invalid option number! (Enter the number of the menu item you wish to use\n Example \"1\")\n");
                }
                
                
            }

            sc.close();

            
            
        }

        
    }
    


    //PURPOSE: loading data from the text file
    public void loadFromFile(String filename) 
    {
        try (Scanner scanner = new Scanner(new File(filename))) 
        {
            while (scanner.hasNextLine()) 
            {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() ) 
                {
                    continue;
                }
        
                String[] fields = line.split(";");
                    
                String parentId = fields[0].trim().isEmpty() ? null : fields[0].trim();

                    //
                    //System.out.println(parentId);
                    logger.info(parentId);
                    //

                String taskId = fields[1].trim();

                    //
                    //System.out.println(taskId);
                    logger.info(taskId);
                    //

                String description = fields[2].trim();

                    //
                    //System.out.println(description);
                    logger.info(description);
                    //

                int effort = fields.length > 3 ? Integer.parseInt(fields[3].trim()) : 0; //Let's checkooo7

                    //
                    //System.out.println(effort+"\n");
                    logger.info(() -> "Estimate: " + effort+"\n");
                    //logger.info(() ->"My variable value: " + filename);
                    //

                

                

                
                Task task = new Task(taskId, description, effort, null);
                
                if (parentId == null)
                {
                    tasks.put(taskId, task);
                }
            
                /*if (parentId != null) 
                {*/
                    Task parent = tasks.get(parentId);
                    if (parent == null) 
                    {
                        System.out.println( taskId+" is a Main task \n" );
                    } 
                    else 
                    {
                        parent.addSubtask(task);
                    }
                //}
                    
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found: " + filename);
        }
        
    }

    //PURPOSE: displaying the Work Breakdown stuctutre
    public void displayWBS() 
    {
        for (Task task : tasks.values()) 
        {
            displayTask(task, 0);
        }
    }


    //PURPOSE: Recursive method for displaying subtasks
    private void displayTask(Task task, int indent) //Let's check
    {
        String indentStr = new String(new char[indent]).replace("\0", " ");
        String effortStr = task.effort > 0 ? ", effort = " + task.effort : "";
        System.out.println(indentStr + task.id + ": " + task.description + effortStr);

        for (Task subtask : task.subtasks) 
        {
            displayTask(subtask, indent + 2);
        }
    }


    //PURPOSE: Method for implementing the configuration settings and menu
    public void configureSettings(String filename) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the new value of N (number of estimators): ");
        numEstimates = scanner.nextInt();

        System.out.println("Choose a reconciliation approach:");
        System.out.println("1. Take the highest estimate");
        System.out.println("2. Take the median estimate");
        System.out.println("3. Keep the default way of calculating\n ");
        System.out.println("\nEnter your option: ");
        int choice = scanner.nextInt();

        Scanner esScan = new Scanner(System.in);
        String conAveEstimate;

        switch (choice) 
        {
            
            case 1:
                estimator = new HighestEffortEstimator();
                
                System.out.println("Please enter task ID: ");
                conAveEstimate = esScan.nextLine();
                estimateEffort(conAveEstimate,filename);

                break;
            case 2:

                estimator = new MedianEffortEstimator();
                System.out.println("Please enter task ID: ");
                conAveEstimate = esScan.nextLine();
                estimateEffort(conAveEstimate,filename);

                break;

            default:
                

                System.out.println("Please enter task ID: ");

                //Scanner esScan = new Scanner(System.in);

                conAveEstimate = esScan.nextLine();
                estimateEffort(conAveEstimate,filename);
        }

        //scanner.close();
    }

    //PURPOSE: Getting the sum of the effort in the WBS
    public int getTotalEffort() 
    {
        int totalEffort = 0;
        for (Task task : tasks.values()) 
        {
            totalEffort += getTaskEffort(task);
        }
        return totalEffort;
    }
    

    //PURPOSE: Recursive method for getting the efforts of the subtasks for effort sum calculation
    private int getTaskEffort(Task task) 
    {
        int effort = task.effort;
        for (Task subtask : task.subtasks) 
        {
            effort += getTaskEffort(subtask);
        }
        return effort;
    }

    //PURPOSE: Getting the amount of tasks that doesn't have an effort estimated
    public int getUnknownTaskCount() 
    {
        int unknownCount = 0;
        for (Task task : tasks.values()) 
        {
            unknownCount += getUnknownTaskCountForTask(task);
        }
        return unknownCount;
    }
    
    //PURPOSE: Recursive method for getting amount of subtasks without an effort estimated 
    private int getUnknownTaskCountForTask(Task task) 
    {
        int unknownCount = 0;
        if (task.effort == 0) 
        {
            if (task.subtasks.isEmpty()) 
            {
                unknownCount++;
            } 
            else 
            {
                for (Task subtask : task.subtasks) 
                {
                    unknownCount += getUnknownTaskCountForTask(subtask);
                }
            }
        }
        return unknownCount;
    }

    //PURPOSE: Getting the estimations form the user
    private List<Integer> getEstimatesFromUser(String description, int numEstimates) 
    {
        List<Integer> estimates = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        System.out.println("Provide " + numEstimates + " estimates for: " + description);
        for (int i = 0; i < numEstimates; i++) 
        {
            System.out.print("Estimate " + (i + 1) + ": ");
            estimates.add(scan.nextInt());
        }

        //scan.close();

        //System.out.println("Works");
        logger.info("Works");

        return estimates;
    }

    //PURPOSE: Estimating the effort of a task for the default way and updating the text file
    public void estimateEffort(String taskId, String filename) 
    {
        Task task = tasks.get(taskId);
        if (task == null) 
        {
            System.out.println("Task not found: " + taskId);
            return;
        }

        estimateEffortRecursive(task);
        writeToFile(filename);
    }

    //PURPOSE: Recursive method for accessing the subtasks for the effort estimation
    private void estimateEffortRecursive(Task task) 
    {
        if (task.subtasks.isEmpty()) 
        {
            List<Integer> estimates = getEstimatesFromUser(task.description, numEstimates);
            task.effort = estimator.estimateEffort(estimates);
        } 
        else 
        {
            for (Task subtask : task.subtasks) 
            {
                estimateEffortRecursive(subtask);
            }
        }
    }

    //PURPOSE: Updating the text file
    private void writeToFile(String filename) 
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(/*filename*/filename))) 
        {
            for (Task task : tasks.values()) 
            {
                writeTaskToFile(writer, task, "");
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    //PURPOSE: Recursive method for updating the subtasks of the text file
    private void writeTaskToFile(PrintWriter writer, Task task, String indent) 
    {
        String parentId = task.parent == null ? "" : task.parent.id;
        String effortStr = task.effort > 0 ? " ; " + task.effort : "";
        writer.println(/*indent +*/ parentId + " ; " + task.id + " ; " + task.description + effortStr);
    
        for (Task subtask : task.subtasks) 
        {
            writeTaskToFile(writer, subtask, indent + task.id + " ; ");
        }
    }

    



}
