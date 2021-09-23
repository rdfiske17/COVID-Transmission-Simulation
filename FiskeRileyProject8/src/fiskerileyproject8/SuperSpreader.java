package fiskerileyproject8;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Subclass of the Citizen Class that is reckless by removing their mask whenever
 * a MaskAuthority is not one of their neighbors.
 * 
 * @author rfiske
 */
public class SuperSpreader extends Citizen 
{
    public static final Color SUPERSPREADER_HEALTHY_COLOR = new Color(170,40,170); //purple
    
    /**
     * Constructor identical to Citizen Class
     * @param myName
     * @param masked
     * @param startingInfectionStatus
     * @param newCampus
     * @param startRow
     * @param startColumn 
     */
    public SuperSpreader(String myName, boolean masked, int startingInfectionStatus, Campus newCampus, int startRow, int startColumn) {
        super(myName, masked, startingInfectionStatus, newCampus, startRow, startColumn);
    }
    
    /**
     * In addition to doing Citizen doYourThing(), SuperSpreader checks if there
     * are no MaskAuthorities as neighbors and if they're currently wearing their
     * mask, and if both things are true they remove their mask and become more susceptible
     * to the infection.
     */
    @Override
    public void doYourThing()
    {
        super.doYourThing();
        ArrayList<Citizen> superSpreaderNeighbors = this.getListOfNeighbors();
        for(Citizen cc : superSpreaderNeighbors)
        {
            if(!(cc instanceof MaskAuthority) && isWearingMask() == true)
            {
                changeMaskStatus(false);
                //System.out.println(getID() + " removed their mask."); //used to print out and show the SuperSpreader removed their mask
            }
        }
    }
    
    /**
     * Returns the particular healthy color for SuperSpreaders
     */
    @Override
    public Color getHealthyColor()
    {
        return SUPERSPREADER_HEALTHY_COLOR;
    }
}