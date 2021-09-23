package fiskerileyproject8;

import javax.swing.*;
import java.awt.*;

/**
 * The purpose of this class is to create and display
 * the campus.
 * Updated: no changes made
 */
 
public class Campus {
    private Cell[][] cells;
    private JPanel panel;
    boolean[][] wallLocations;

    public static final  int  NUMBER_OF_ROWS = 20;
    public static final  int  NUMBER_OF_COLUMNS = 40;
    public static final  int  CELL_SIZE= 30;

    /*
    * Constructor
    */
    public Campus(boolean[][] start)
    {
        wallLocations = start;
    }
    
    /*
    * Gets the cells set up. 
    */

    public void initializeCampus(){

        panel = new JPanel(new GridLayout(NUMBER_OF_ROWS,NUMBER_OF_COLUMNS));
        cells = new Cell[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        for(int ii = 0; ii< NUMBER_OF_ROWS; ii++){
            for(int jj = 0; jj<NUMBER_OF_COLUMNS; jj++){
                
                cells[ii][jj] = new Cell(ii,jj,wallLocations[ii][jj]);
                //cells[ii][jj].setText(ii + " " + jj);
                panel.add(cells[ii][jj].getButton());
            }
        }
    }

    /**
     * Displays the campus, with all of its occupants.
     */

    public void showCampus(){
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.pack();//gets rid of any extra space not occupied by the cells in the window
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /*
    * Accessor
    */
    public Cell getCell(int row, int col)
    {
        return cells[row][col];
    }

    /*
    * Adds a citizen to the campus.
    */
    public void addCitizen(Citizen citizen, int row, int col)
    {
        Cell cc = cells[row][col];
        cc.addCitizen(citizen);
        citizen.setCell(cc);
    }

}

    