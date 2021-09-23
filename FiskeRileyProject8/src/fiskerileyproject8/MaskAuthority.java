package fiskerileyproject8;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Subclass of the Citizen Class that enforces the mask laws. If there are any SuperSpreaders
 * as their neighbors they will make them put their mask on, and if someone coughs near them,
 * they will force all their neighbors to move and then move twice afterwards themselves.
 * 
 * @author rfiske
 */
public class MaskAuthority extends Citizen 
{
    public static final Color MASKAUTHORITY_HEALTHY_COLOR = new Color(51,51,255); //blue
    
    /**
     * Constructor identical to Citizen Class
     * @param myName
     * @param masked
     * @param startingInfectionStatus
     * @param newCampus
     * @param startRow
     * @param startColumn 
     */
    public MaskAuthority(String myName, boolean masked, int startingInfectionStatus, Campus newCampus, int startRow, int startColumn)
    {
        super(myName, masked, startingInfectionStatus, newCampus, startRow, startColumn);
    }
    
    /**
     * In addition to doing Citizen doYourThing(), MaskAuthority checks if any of their
     * neighbors are SuperSpreaders not wearing their mask, and if that is true they
     * force them to put their mask on. 
     */
    @Override
    public void doYourThing()
    {
        super.doYourThing();
        ArrayList<Citizen> maskAuthorityNeighbors = getListOfNeighbors();
        for(Citizen cc : maskAuthorityNeighbors)
        {
            if(cc instanceof SuperSpreader && cc.isWearingMask() == false)
            {
                cc.changeMaskStatus(true);
                //System.out.println("Mask Authority " + getID() + " made SuperSpreader " + cc.getID() + " put their mask on."); //used to show who the MaskAuthority is making put on their mask
            }
        }
    }
    
    /**
     * Method is called if MaskAuthority is coughedAt. MaskAuthority gets a list of its
     * neighbors and forces them to move since someone coughed, and then the MaskAuthority
     * moves themselves twice
     */
    @Override
    public void react()
    {
        //System.out.println("SOMEONE COUGHED AHAHAHAHAAHHAHAHAHAHAAHAHAHAH"); //used to show MaskAuthorities can react
        ArrayList<Citizen> neighbors = this.getListOfNeighbors();
        for(Citizen cc : neighbors)
        {
            cc.move();
            //System.out.println(cc.getID() + " moved by order of MaskAuthority " + getID()); //used to show who the MaskAuthority is moving
        }
        move();
        move();
    }
    
    /**
     * Returns the particular healthy color for MaskAuthority
     */
    @Override
    public Color getHealthyColor()
    {
        return MASKAUTHORITY_HEALTHY_COLOR;
    }
}