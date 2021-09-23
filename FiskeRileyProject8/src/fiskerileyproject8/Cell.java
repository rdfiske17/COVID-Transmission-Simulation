package fiskerileyproject8;

import javax.swing.*;//The * is a lazy import:  I'm importing everything in the swing package.
import java.awt.*;//Yep, still being lazy.

/**
 *"The purpose of class is to ensure the proper functionality of the cell and to make it interactive
 * Updated: modified addCitizen method as described in the comments of the method
 */

public class Cell{

    public static final Color WALL_COLOR = Color.BLACK;
    public static final Color EMPTY_COLOR = Color.GRAY;
    public static final Color INFECTED_COLOR = Color.RED;
    public static final Color DO_YOUR_THING_COLOR = Color.YELLOW;
    
    private JButton button;
    private boolean occupied;
    private boolean isWallCell;//indicating whether the cell is a wall or if free for people to be on.
    private final int row;
    private final int col;
    private Citizen currentCitizen;

    /**
     * Constructor
     */
    public Cell(int rr, int cc, boolean wallCell)
    {
        row = rr;
        col = cc;
        occupied = false;
        isWallCell = wallCell;
        button = new JButton();
        button.setPreferredSize(new Dimension(Campus.CELL_SIZE,Campus.CELL_SIZE));
        button.setMargin(new Insets(0,0,0,0));
        if(isWallCell == true)
        {
            button.setBackground(WALL_COLOR);
        }
        else
        {
            button.setBackground(EMPTY_COLOR);
        }
        currentCitizen = null;
    }
    
    /**
     * Accessor method
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Changes button text
     */
    public void setText(String text)
    {
        button.setText(text);
    }

    /**
     * Sets the color of the button
     * 
     */

    public void setColor(Color newColor)
    {
        button.setBackground(newColor);
    }

    /**
    * Checks to see if the cell is empty
    */
    
    public boolean getAvailability(){
        return !occupied;
    }

    /**
     * Accessor method
     */
    public boolean isWallCell() {
        return isWallCell;
    }

    /**
     * Accessor method
     */

    public int getRow() {
        return row;
    }

    /**
     * Accessor method
     */
    public int getCol() {
        return col;
    }

    /**
     * Adds a citizen to occupy this cell
     * Updated: changed display from ID number to masked status and added else if to 
     * color citizen the recovered color if status is recovered.
     */

    public void addCitizen(Citizen citizen)
    {
        currentCitizen= citizen;
        occupied = true;
        if(citizen.isWearingMask())
        {
            button.setText("Y");

        }
        else
        {
            button.setText("N");
        }
        //button.setText(" "+citizen.getID());
        if(citizen.getInfectionStatus() == Citizen.INFECTED)
        {
            button.setBackground(INFECTED_COLOR);
        }
        else if(citizen.getInfectionStatus() == Citizen.RECOVERED)
        {
            button.setBackground(citizen.getRecoveredColor());
        }
        else
        {
            button.setBackground(citizen.getHealthyColor());
        }
    }

    /**
     * Removes a Citizen from the cell.
     */

    public void removeCitizen()
    {
        currentCitizen= null;
        occupied = false;
        button.setBackground(EMPTY_COLOR);
        button.setText("");
    }

    /**
     * Accessor method
     */

    public Citizen getCitizen()
    {
        return currentCitizen;
    }

    /**
     * Briefly turns a cell yellow, then back to its previous color.
     * Used to indicate some sort of action or happening.
     */

    public void brieflyYellow()
    {
        Color oldColor = button.getBackground();
        button.setBackground(DO_YOUR_THING_COLOR);
        Tester.pause(Tester.sleepTime);
        button.setBackground(oldColor);
    }
}
