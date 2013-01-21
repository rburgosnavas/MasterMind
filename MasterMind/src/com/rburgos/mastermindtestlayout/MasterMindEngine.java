package com.rburgos.mastermindtestlayout;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * MasterMindEngine class contains static methods that will return the correct
 * results of the MasterMind game.
 * @author Roberto Burgos
 * @version 0.1
 */
public class MasterMindEngine
{
    /**
     * Compares the colors of the "pegs" the user entered (<code>guess</code>) 
     * against the colors of the "pegs" the user needs to guess 
     * (<code>ans</code>). This method returns the number of colors that were 
     * correctly guessed but which positions in the array do not match. If two 
     * pegs match color and position in the arrays, they will skipped.
     * <br /><br />
     * For example, two arrays to be passed contain:
     * <ul>
     * <li>[1,2,3,4,5]</li>
     * <li>[1,1,2,5,0]</li>
     * </ul>
     * After comparing these two arrays, the elements in the first positions
     * ([1] and [1]) will be skipped. The following [1] from the second array
     * will not be compared to the [1] in the first array's first position as
     * it will be deemed "skipped". After going through the rest of the arrays'
     * elements, the next [2]'s and [5]'s will be match, meaning that two
     * colors were found but not in the right position. <br />
     * @param ans The array with the "pegs" to guess.
     * @param guess The array with the "pegs" guessed by the users.
     * @return The number of colors found.
     */
    public static int getTotalNum(ArrayList<? super JComponent> ans,
            ArrayList<? super JComponent> guess)
    {
        ColorPeg ansPeg, guessPeg;
        int count = 0;
        ArrayList<Color> skipColors = new ArrayList<>();
        
        // This loop makes the one-to-one comparison. For example, is the color
        // in first element of guessPeg is the same as the color in the first
        // element in ansPeg, then that color is added to skipColor, and so on.
        for (int i = 0; i < guess.size(); i++)
        {
            guessPeg = (ColorPeg) guess.get(i);
            ansPeg = (ColorPeg) ans.get(i);
            
            if (guessPeg.getColor().equals(ansPeg.getColor()))
                skipColors.add(guessPeg.getColor());
        }
        
        // Once skipColor is populated with colors to skip, we move on to the 
        // next loop which will look for colors in andPeg that are also in 
        // guessPeg. If a match is found between these two, and if that match
        // is not in skipColor, then we raise the counter (count) by 1.
        for (int i = 0; i < guess.size(); i++)
        {
            for (int j = 0; j < ans.size(); j++)
            {
                guessPeg = (ColorPeg) guess.get(i);
                ansPeg = (ColorPeg) ans.get(j);

                if (guessPeg.getColor().equals(ansPeg.getColor()) && 
                        !skipColors.contains(guessPeg.getColor()))
                {
                    count++;
                }
            } 
        }        
        return count;
    }
    
    /**
     * Compares the colors of the "pegs" the user entered (guesses) against
     * the colors of the "pegs" the user needs to guess (answer). This is a
     * one-to-one comparison. If first the element in the <code>ans</code> 
     * array matches the color of the first element in the <code>guess</code> 
     * array, that will be counted as one find.<br /><br />
     * For example, two arrays to be passed contain:
     * <ul>
     * <li>[1,2,3,4,5]</li>
     * <li>[1,1,2,5,0]</li>
     * </ul> 
     * Since the first elements in both arrays match ([1] = [1]), then that
     * gets counted as 1. Since there are no more matches, 1 is returned.<br />
     * @param ans The array with the "pegs" to guess.
     * @param guess The array with the "pegs" guessed by the users.
     * @return The number of colors found.
     */
    public static int getTotalNumPos(ArrayList<? super JComponent> ans,
            ArrayList<? super JComponent> guess)
    {
        ColorPeg ansPeg, guessPeg;
        int count = 0;
        
        for (int i = 0; i < guess.size(); i++)
        {
            guessPeg = (ColorPeg) guess.get(i);
            ansPeg = (ColorPeg) ans.get(i);
            if (guessPeg.getColor().equals(ansPeg.getColor())) 
                count++;
        }
        return count;
    }
    
    /**
     * Makes a one-to-one match and for each match, "yes" is added to an array,
     * "no" if there is no match.
     * @param ans The array with the "pegs" to guess.
     * @param guess The array with the "pegs" guessed by the users.
     * @return a String array of five (5) elements with "yes" or "no"
     */
    public static String[] getYesNoHints(ArrayList<? super JComponent> ans,
            ArrayList<? super JComponent> guess)
    {
        ColorPeg ansPeg, guessPeg;
        String[] hints = new String[5];
        
        for (int i = 0; i < guess.size(); i++)
        {
            guessPeg = (ColorPeg) guess.get(i);
            ansPeg = (ColorPeg) ans.get(i);
            
            if (guessPeg.getColor().equals(ansPeg.getColor())) 
                hints[i] = "Yes";
            else 
                hints[i] = "No ";
        }        
        return hints;
    }
}
