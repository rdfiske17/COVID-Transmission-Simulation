package fiskerileyproject8;

import java.util.ArrayList;
import java.util.Random;

/**
 * Updated purpose is the original purpose plus looping the simulation multiple
 * times and creating a numeric summary after each simulation, and an average
 * after all simulations have been completed.
 * 
 * "The purpose of this class is to define the walls of the campus,
 * create some Citizens to put on the campus, and then to turn them loose.
 * 
 * @author Nathan Axvig"
 * @author rfiske
 */

public class Tester {

public static final int sleepTime = 1; //compile project everytime after updating this
public static final int TOTAL_TIME = 200;
public static final int NUMBER_OF_TRIALS = 100; //number of times simulation will run
    /**
     * The main method.
     */
    public static void main(String[] args)
    {
        boolean[][] map ={
            {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {true ,true ,true ,true ,true ,true ,true ,true ,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,true ,false,false,false,false,false,true ,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,true ,true ,true ,true ,true ,true ,true ,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,true ,true ,true ,true ,true ,true ,true ,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false,true ,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}
        };
        
        /**
         * A list of ArrayLists that will hold values throughout multiple simulations
         * and later be used to calculate averages between simulations.
         */
        ArrayList<Double> initialRuleAbiderCounters = new ArrayList<>();
        ArrayList<Double> initialSuperSpreaderCounters = new ArrayList<>();
        ArrayList<Double> initialMaskAuthorityCounters = new ArrayList<>();
        ArrayList<Double> finalRuleAbiderCounters = new ArrayList<>();
        ArrayList<Double> finalSuperSpreaderCounters = new ArrayList<>();
        ArrayList<Double> finalMaskAuthorityCounters = new ArrayList<>();
        ArrayList<Double> susceptibleCounters = new ArrayList<>();
        ArrayList<Double> infectedCounters = new ArrayList<>();
        ArrayList<Double> recoveredCounters = new ArrayList<>();
        ArrayList<Double> deceasedCounters = new ArrayList<>();
        
        //Loops the whole simulation as many times as desired
        for(int trialNumber = 1; trialNumber <= NUMBER_OF_TRIALS; trialNumber++)
        {
                System.out.println("Trial Number " + trialNumber);
                Campus campus = new Campus(map);
                campus.initializeCampus();
                campus.showCampus();
                ArrayList<Citizen> population = new ArrayList<Citizen>();
                Random citizenSelector = new Random();
                boolean masked = true;
                boolean unmasked = false;

                for(int ii = 0; ii < Campus.NUMBER_OF_ROWS; ii += 3)
                {
                   for(int jj = 0; jj < Campus.NUMBER_OF_COLUMNS; jj += 2)
                   {
                       if(map[ii][jj] == false)//then a cell is not a wall.
                       {

                        int newCitizen = citizenSelector.nextInt(100);
                        if(newCitizen > 90) 
                        {
                            population.add(new MaskAuthority("MaskAuthority", masked, Citizen.SUSCEPTIBLE, campus, ii, jj));
                        }
                        else if(newCitizen > 55)
                        {
                            population.add(new SuperSpreader("SuperSpreader", unmasked, Citizen.SUSCEPTIBLE, campus, ii, jj));
                        }
                        else
                        {
                            population.add(new RuleAbider("RuleAbider", masked, Citizen.SUSCEPTIBLE, campus, ii, jj));
                        }
                    }
                }
            }
            population.add(new SuperSpreader("", masked, Citizen.INFECTED, campus, 1,1));
            //population.add(new Nurse("", masked, Citizen.SUSCEPTIBLE, campus, 10,10));

            
            //add up initial amounts of each subclass
            double initialRuleAbiderCounter = 0;
            double initialSuperSpreaderCounter = 0;
            double initialMaskAuthorityCounter = 0;
            for(Citizen cc : population)
            {
                if(cc instanceof RuleAbider)
                {
                    initialRuleAbiderCounter++;
                }
                if(cc instanceof SuperSpreader)
                {
                    initialSuperSpreaderCounter++;
                }
                if(cc instanceof MaskAuthority)
                {
                    initialMaskAuthorityCounter++;
                }
            }

            //add amounts to the array list to calculate averages later
            initialRuleAbiderCounters.add(initialRuleAbiderCounter);
            initialSuperSpreaderCounters.add(initialSuperSpreaderCounter);
            initialMaskAuthorityCounters.add(initialMaskAuthorityCounter);

            double finalSusceptibleCounter = 0;
            double finalInfectedCounter = 0;
            double finalDeceasedCounter = 0;
            double finalRecoveredCounter = 0;
            for(int time = 0; time <= TOTAL_TIME; time++)
            {
                int susceptibleCounter = 0;
                int infectedCounter = 0;
                int deceasedCounter = 0;
                int recoveredCounter = 0;
                if(time % 5 == 0)
                {
                    for(Citizen cc : population)
                    {
                        if(cc.getInfectionStatus() == Citizen.SUSCEPTIBLE)
                        {
                            susceptibleCounter++;
                        }
                        if(cc.getInfectionStatus() == Citizen.INFECTED)
                        {
                            infectedCounter++;
                        }
                        if(cc.getInfectionStatus() == Citizen.DECEASED)
                        {
                            deceasedCounter++;
                        }
                        if(cc.getInfectionStatus() == Citizen.RECOVERED)
                        {
                            recoveredCounter++;
                        }
                    }
                    //prints out a summary of the campus every 5 time units
                    System.out.println("At time " + time + ", there are " + susceptibleCounter + " susceptible, " + infectedCounter + " infected, " +
                    deceasedCounter + " deceased, and " + recoveredCounter + " recovered indiviudals");
                    //assigns current values if the simulation has reached it's end
                    if(time == TOTAL_TIME)
                    {
                        finalSusceptibleCounter = susceptibleCounter;
                        finalInfectedCounter = infectedCounter;
                        finalDeceasedCounter = deceasedCounter;
                        finalRecoveredCounter = recoveredCounter;
                    }
                }
                for(Citizen cc : population)
                {
                    cc.move();
                }
            }
            
            //add the final counts to the final array lists to calculate averages
            susceptibleCounters.add(finalSusceptibleCounter);
            infectedCounters.add(finalInfectedCounter);
            recoveredCounters.add(finalRecoveredCounter);
            deceasedCounters.add(finalDeceasedCounter);

            //counts up the remaining amount of each subclass on the campus
            double finalRuleAbiderCounter = 0;
            double finalSuperSpreaderCounter = 0;
            double finalMaskAuthorityCounter = 0;
            for(Citizen cc : population)
            {
                if(cc instanceof RuleAbider && cc.getInfectionStatus() != cc.DECEASED)
                {
                    finalRuleAbiderCounter++;
                }
                if(cc instanceof SuperSpreader && cc.getInfectionStatus() != cc.DECEASED)
                {
                    finalSuperSpreaderCounter++;
                }
                if(cc instanceof MaskAuthority && cc.getInfectionStatus() != cc.DECEASED)
                {
                    finalMaskAuthorityCounter++;
                }
            }

            //add the final counts of each to the array lists to calculate averages later
            finalRuleAbiderCounters.add(finalRuleAbiderCounter);
            finalSuperSpreaderCounters.add(finalSuperSpreaderCounter);
            finalMaskAuthorityCounters.add(finalMaskAuthorityCounter);

            //print out a little summary
            System.out.println("-------------------------------------------------------------------------------");
            double overallInfectionPercentage = 100* ((population.size() - finalSusceptibleCounter) / population.size());
            System.out.print("At the end of trial " + trialNumber + " with a population size of " + population.size() +", ");
            System.out.printf("%.3f", overallInfectionPercentage);
            System.out.print("% of the population was infected at some point:\n");
            System.out.printf("%.3f",100*(finalDeceasedCounter/population.size()));
            System.out.print("% of the population is deceased, ");
            System.out.printf("%.3f",100*(finalInfectedCounter/population.size()));
            System.out.print("% of the population is currently infected, ");
            System.out.printf("%.3f",100*(finalRecoveredCounter/population.size()));
            System.out.print("% of the population recovered, \nand ");
            System.out.printf("%.3f", 100*(finalSusceptibleCounter/population.size()));
            System.out.print("% of the population was never infected.\n");
            System.out.println(finalRuleAbiderCounter + " of " + initialRuleAbiderCounter + " RuleAbiders, " + finalSuperSpreaderCounter
            + " of " + initialSuperSpreaderCounter + " SuperSpreaders, and " + finalMaskAuthorityCounter + " of " + initialMaskAuthorityCounter + " MaskAuthorities survived.");
            System.out.println("-------------------------------------------------------------------------------");
        }
        
        //print out a final overall summary using averages of the array lists
        double populationSize = initialRuleAbiderCounters.get(0) + initialSuperSpreaderCounters.get(0) + initialMaskAuthorityCounters.get(0);

        double overallInfectionPercentage = 100 * ((populationSize - getAverage(susceptibleCounters)) / populationSize);
        System.out.print("After " + NUMBER_OF_TRIALS + " trials with a population size of "); 
        System.out.printf("%.0f", populationSize);
        System.out.print(", on average ");
        System.out.printf("%.3f", overallInfectionPercentage);
        System.out.print("% of the population was infected at some point:\n");
        System.out.printf("%.3f",100 *(getAverage(deceasedCounters)/populationSize));
        System.out.print("% of the population is deceased, ");
        System.out.printf("%.3f",100 *(getAverage(infectedCounters)/populationSize));
        System.out.print("% of the population is currently infected, ");
        System.out.printf("%.3f",100 *(getAverage(recoveredCounters)/populationSize));
        System.out.print("% of the population recovered, \nand ");
        System.out.printf("%.3f", 100 *(getAverage(susceptibleCounters)/populationSize));
        System.out.print("% of the population was never infected.\n");
        System.out.println(getAverage(finalRuleAbiderCounters) + " of " + getAverage(initialRuleAbiderCounters) + " RuleAbiders, " + getAverage(finalSuperSpreaderCounters)
        + " of " + getAverage(initialSuperSpreaderCounters) + " SuperSpreaders, and " + getAverage(finalMaskAuthorityCounters) + " of "
        + getAverage(initialMaskAuthorityCounters) + " MaskAuthorities survived.");
    }
    
/**
 * This method pauses the execution of the program for pauseTime milliseconds.
 * It keeps things from running so fast our eyes can't see them.  Large pauseTimes
 * cause the animation to run slowly, small pauseTimes cause the animation to run fast.
  */
    public static void pause(int pauseTime)
    {
        try
              {
                    Thread.sleep(pauseTime);
              }
              catch(InterruptedException ex)
              {
               Thread.currentThread().interrupt();
              }
    }
    
    /**
     * This method calculates the average of an ArrayList of doubles
     * @param arrayList
     * @return average of all items in array list
     */
    
    public static double getAverage(ArrayList<Double> arrayList)
    {
        double average = 0;
        for(int ii = 0; ii < NUMBER_OF_TRIALS; ii++)
        {
            average += arrayList.get(ii);
            if(ii == NUMBER_OF_TRIALS - 1 && NUMBER_OF_TRIALS != 0)
            {
                average /= NUMBER_OF_TRIALS;
            }
        }
        return average;
    }
}

/**
 * Conclusions drawn from experimentation:
 * 
 * During my experimentation, I changed the variables of time per experiment, which subclass
 * begun the infection, and how long an infection lasts in an individual before recovering
 * or dieing. Times were varied between 100 and 200, subclasses were between RuleAbiders
 * and SuperSpreaders, and lengths of infection were between 40 and 50 turns. Below is the data collected
 * after 100 trials of each variant:
 * 
 * Time = 200, begun infection with SuperSpreader, infection length 50:
 * After 100 trials with a population size of 129, on average 69.504% of the population was infected at some point:
 * 27.070% of the population is deceased, 5.124% of the population is currently infected, 37.310% of the population recovered, 
 * and 30.496% of the population was never infected.
 * 52.46 of 71.59 RuleAbiders, 33.57 of 46.29 SuperSpreaders, and 7.95 of 11.12 MaskAuthorities survived.
 * 
 * Time = 100, begun infection with SuperSpreader, infection length 50:
 * After 100 trials with a population size of 129, on average 41.295% of the population was infected at some point:
 * 9.264% of the population is deceased, 21.217% of the population is currently infected, 10.814% of the population recovered, 
 * and 58.705% of the population was never infected.
 * 65.65 of 71.81 RuleAbiders, 41.73 of 46.0 SuperSpreaders, and 9.41 of 11.19 MaskAuthorities survived.
 * 
 * Time = 200, begun infection with RuleAbider, infection length 50:
 * After 100 trials with a population size of 129, on average 58.372% of the population was infected at some point:
 * 22.217% of the population is deceased, 5.496% of the population is currently infected, 30.659% of the population recovered, 
 * and 41.628% of the population was never infected.
 * 56.51 of 72.77 RuleAbiders, 35.32 of 45.09 SuperSpreaders, and 8.34 of 11.14 MaskAuthorities survived.
 * 
 * Timer = 100, begun infection with RuleAbider, infection length 50:
 * After 100 trials with a population size of 129, on average 33.550% of the population was infected at some point:
 * 6.473% of the population is deceased, 18.798% of the population is currently infected, 8.279% of the population recovered, 
 * and 66.450% of the population was never infected.
 * 68.42 of 72.98 RuleAbiders, 41.31 of 43.9 SuperSpreaders, and 10.74 of 12.12 MaskAuthorities survived.
 * 
 * Timer = 200, begun infection with SuperSpreader, infection length 40:
 * After 100 trials with a population size of 129, on average 53.264% of the population was infected at some point:
 * 18.388% of the population is deceased, 2.124% of the population is currently infected, 32.752% of the population recovered, 
 * and 46.736% of the population was never infected.
 * 58.41 of 71.6 RuleAbiders, 38.0 of 46.62 SuperSpreaders, and 8.86 of 10.78 MaskAuthorities survived.
 * 
 * Timer = 100, begun infection with SuperSpreader, infection length 40:
 * After 100 trials with a population size of 129, on average 36.822% of the population was infected at some point:
 * 8.977% of the population is deceased, 12.713% of the population is currently infected, 15.132% of the population recovered, 
 * and 63.178% of the population was never infected.
 * 65.31 of 71.25 RuleAbiders, 41.52 of 45.99 SuperSpreaders, and 10.31 of 11.76 MaskAuthorities survived.
 * 
 * Timer = 200, begun infection with RuleAbider, infection length 40:
 * After 100 trials with a population size of 129, on average 44.318% of the population was infected at some point:
 * 15.256% of the population is deceased, 1.341% of the population is currently infected, 27.721% of the population recovered, 
 * and 55.682% of the population was never infected.
 * 61.06 of 72.59 RuleAbiders, 38.93 of 45.42 SuperSpreaders, and 9.3 of 10.99 MaskAuthorities survived.
 * 
 * Timer = 100, begun infection with RuleAbider, infection length 40:
 * After 100 trials with a population size of 129, on average 30.667% of the population was infected at some point:
 * 6.690% of the population is deceased, 13.163% of the population is currently infected, 10.814% of the population recovered, 
 * and 69.333% of the population was never infected.
 * 67.21 of 72.17 RuleAbiders, 41.99 of 44.62 SuperSpreaders, and 10.97 of 12.21 MaskAuthorities survived.
 * 
 * From this, we can draw logical conclusions that the longer the simulation, on average more of the population
 * is infected at one point in time and the ending currently infected numbers are much smaller since the infection has been
 * allowed to run its course through the population, starting the infection off with a SuperSpreader rather than a RuleAbider 
 * makes the infection spread more within the time, and the longer the infection time the more people are infected.
 * These conclusions all make sense, and the data backs up this logic.
 * 
 */