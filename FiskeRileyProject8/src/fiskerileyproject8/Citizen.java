package fiskerileyproject8;

import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;

/**
 * This is a general superclass for people on the campus.
 * 
 * It governs how they move, what they do when they move, 
 * and how they can infect one another.
 * 
 * Updated: added infectedTurnCounter, infectedDuration, RECOVERED, DECEASED, 
 * CITIZEN_RECOVERED_COLOR, getRecoveredColor(),changeMaskStatus(); modified Citizen constructor 
 * move(), doYourThing().
 */


public class Citizen {

    private String name;
    public final int ID;
    protected Cell currentCell;
    private boolean isWearingMask;
    protected int infectionStatus;
    protected Campus campus;
    private int infectedTurnCounter; //increases every turn until infectionDuration is reached
    private int infectionDuration = 50; //duration of infection

    public static final int SUSCEPTIBLE = 0;
    public static final int INFECTED = 1;
    public static final int RECOVERED = 2; //Citizen recovers from infection
    public static final int DECEASED = 3; //Citizen dies from infection

    public static final double MASKED_TRANSMISSION_PROBABILITY = 0.10;//this is a totally made up number.
    public static final double UNMASKED_TRANSMISSION_PROBABILITY = 0.80;//this is a totally made up number.
    public static final double MASKED_PROTECTION_FACTOR = 0.50;//this is a totally made up number.
   
    public static final Color CITIZEN_HEALTHY_COLOR = new Color(0,220,200);
    public static final Color CITIZEN_RECOVERED_COLOR = new Color(255,255,255); //color of recovered Citizens

    private static int idCount = 1; 

    private static final int[][] possibleMoves = {
        {-1,0},
        {-1,1},
        {-1,-1},
        {1,-1},
        {1,0},
        {1,1},
        {0,1},
        {0,-1}  
    };

    public static final Random randomNumberGenerator = new Random();

    /*
    * Constructor
    * Modified: added infectedTurnCounter starting at 0
    */

    public Citizen(String myName, boolean masked, int startingInfectionStatus, Campus newCampus, int startRow, int startColumn )
    {
        name = myName;
        currentCell = null;
        isWearingMask = masked;
        infectionStatus = startingInfectionStatus;
        campus = newCampus;
        campus.addCitizen(this,startRow,startColumn);
        infectedTurnCounter = 0;
        ID = idCount;
        idCount ++;

    }

    /**
    * Describes how a Citizen should choose their next cell, and moves them to it.
    * Such a move also initiates doYourThing()
    * Modified: checks to make sure citizen is not deceased before moving
    */

    public void move()
    {
        //get list of place you could go.
        if(getInfectionStatus() != DECEASED)
        {
            ArrayList<Cell> availableCells = new ArrayList<Cell>();
            availableCells.add(currentCell);
            int row = currentCell.getRow();
            int col = currentCell.getCol();

            for(int[] possibleMove : possibleMoves)
            {
                int newRow = row + possibleMove[0];
                int newCol = col + possibleMove[1];

                if(newRow >= 0 && newRow < Campus.NUMBER_OF_ROWS && newCol >= 0 && newCol < Campus.NUMBER_OF_COLUMNS)
                {
                    Cell potentialCell = campus.getCell(newRow, newCol);
                    if(potentialCell.getAvailability() && !potentialCell.isWallCell())
                    {
                        availableCells.add(potentialCell);
                    }
                }
            }

        // now pick one and move there.
            int nn = availableCells.size();
            int randomIndex = randomNumberGenerator.nextInt(nn);
            Cell oldCell = currentCell;
            Cell newCell = availableCells.get(randomIndex);
            setCell(newCell);
            oldCell.removeCitizen();
            newCell.addCitizen(this);
            doYourThing();

            Tester.pause(Tester.sleepTime);
        }
    }

    /**
     * A general method of what to do when arriving at a cell.
     * Should be overloaded or overridden in subclasses
     * depending on the intended behavior of instances of that
     * subclass.
     * 
     * Updated: if infected, Citizen's infectedTurnCounter increases. If this turnCounter
     * reaches the infectionDuration, the citizen recovers, in the last 20% of the infection
     * time the citizen has a 5% of dieing every turn. If the citizen dies, they are removed from
     * the campus and will no longer move.
     */
    public void doYourThing()
    {
        //if you're infected, cough to spread it
        if(infectionStatus == INFECTED)
        {
            infectedTurnCounter++;
            if(infectedTurnCounter < infectionDuration)
            {
                ArrayList<Citizen> neighbors = this.getListOfNeighbors();
                Random deathChance = new Random();
                if(neighbors.size() > 0)
                {
                    this.getCell().brieflyYellow();
                    cough();
                }
                if(infectedTurnCounter >= infectionDuration*0.8 && deathChance.nextInt(100) < 5)
                {
                infectionStatus = DECEASED;
                currentCell.removeCitizen();
                }
            }
            else
            {
                changeInfectionStatus(RECOVERED);
            }
        }
    }

    /**
     * Opens up options to spread disease to people 
     * in neighboring cells.
     */
    public void cough()
    {
        ArrayList<Citizen> neighbors = getListOfNeighbors();
        for(Citizen cc : neighbors)
        {
            this.coughAt(cc);
            cc.react();
        }
    }

    /**
     * This is an empty method as of right now
     * It is intended to be overridden by subclasses
     * so that subclasses can do their own unique thing
     * as a reaction to someone around them coughing.     * 
     */

     public void react()
     {
        
     }

    /**
     * Uses random numbers and the current states of SIR for 
     * both participants to determine whether infection spreads.
     */

    public void coughAt(Citizen potentialVictim)
    {
        if(this.infectionStatus == Citizen.INFECTED && potentialVictim.getInfectionStatus() == Citizen.SUSCEPTIBLE)
        {
            double threshold;
            //adjust for whether cougher is wearing mask
            if(this.isWearingMask)
            {
                threshold = MASKED_TRANSMISSION_PROBABILITY;
            }
            else
            {
                threshold = UNMASKED_TRANSMISSION_PROBABILITY;
            }
            //now adjust for whether potential victim is masked
            if(potentialVictim.isWearingMask)
            {
                threshold *= MASKED_PROTECTION_FACTOR;
            }

            double rand = randomNumberGenerator.nextDouble();

            if(rand < threshold)
            {
                potentialVictim.changeInfectionStatus(Citizen.INFECTED);
            }
        }
    }

    /**
     * Obtains a list of all Citizens in the 8 neighboring cells.
     */

    public ArrayList<Citizen> getListOfNeighbors()
    {
        ArrayList<Citizen> neighbors = new ArrayList<Citizen>();
        int row = currentCell.getRow();
        int col = currentCell.getCol();

        for(int[] possibleMove : possibleMoves)
        {
            int newRow = row + possibleMove[0];
            int newCol = col + possibleMove[1];

            if(newRow >= 0 && newRow < Campus.NUMBER_OF_ROWS && newCol >= 0 && newCol < Campus.NUMBER_OF_COLUMNS)
            {
                Cell potentialCell = campus.getCell(newRow, newCol);
                if(!potentialCell.getAvailability() && !potentialCell.isWallCell())
                {
                    neighbors.add(potentialCell.getCitizen());
                }
            }
            
        }

        return neighbors;
    }

    /*
    * Accessor
    */
    public Color getHealthyColor()
    {
        return CITIZEN_HEALTHY_COLOR;
    }
    
    /*
    * Method returns the recovered color
    */
    public Color getRecoveredColor()
    {
        return CITIZEN_RECOVERED_COLOR;
    }
    
    /*
    * Accessor
    */
    public int getID()
    {
        return ID;
    }

    /*
    * Accessor
    */
    public String getName()
    {
        return name;
    }

    /*
    * Accessor
    */
    public Cell getCell()
    {
        return currentCell;
    }

    /*
    * Accessor
    */
    public boolean isWearingMask()
    {
        return isWearingMask;
    }

    /*
    * Accessor
    */
    public int getInfectionStatus()
    {
        return infectionStatus;
    }

    /*
    * Changes from one cell to another.
    */
    public void setCell(Cell newCell)
    {
        currentCell = newCell;
    }

    /*
    * Changes the mask status of the Citizen using a boolean
    */
    public void changeMaskStatus(boolean masked)
    {
        isWearingMask = masked;
    }
    
    /*
    * Changes infection status.
    */
    public void changeInfectionStatus(int newStatus)
    {
        infectionStatus = newStatus;
    }
}