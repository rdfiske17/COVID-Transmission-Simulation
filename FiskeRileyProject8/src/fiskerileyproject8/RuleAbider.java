package fiskerileyproject8;

import java.awt.Color;
import java.util.Random;

/**
 * Subclass of the Citizen Class that follows the guidelines, keeps their mask on,
 * will move if the MaskAuthority tells them to, and has a chance to quarantine for
 * a turn if infected
 * 
 * @author rfiske
 */
public class RuleAbider extends Citizen 
{
    public static final Color RULEABIDER_HEALTHY_COLOR = new Color(0,204,0); //green    
    
    /**
     * Constructor identical to Citizen Class
     * @param myName
     * @param masked
     * @param startingInfectionStatus
     * @param newCampus
     * @param startRow
     * @param startColumn 
     */
    public RuleAbider(String myName, boolean masked, int startingInfectionStatus, Campus newCampus, int startRow, int startColumn) {
        super(myName, masked, startingInfectionStatus, newCampus, startRow, startColumn);
    }
    
    /**
     * move() method overrides Citizen move() by checking if infected first, moving
     * if not infected, and has a 1/3 chance of not moving (or quarantining) for
     * a turn if infected.
     */
    @Override
    public void move()
    {
        if(getInfectionStatus() != INFECTED)
        {
            super.move();
        }
        else
        {
            Random moveChance = new Random();
            int chanceToMove = moveChance.nextInt(3);
            if(chanceToMove < 1)
            {
                //System.out.println(getID() + " is quarantining for one turn."); //used to print out and show the RuleAbider didn't move this turn
                super.doYourThing();
            }
            else
            {
                super.move();
            }
        }
    }
    
    /**
     * Returns the particular healthy color for RuleAbiders
     */
    @Override
    public Color getHealthyColor()
    {
        return RULEABIDER_HEALTHY_COLOR;
    }
}