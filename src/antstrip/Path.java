/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antstrip;

/**
 *
 * @author kenma
 */
public class Path {
    
    //Matrix representing the map made the anthill
    int map[][] = new int[3][3];
    //The number of steps the ant has made towards the anthill. (Will be displayed at the end of the game)
    int Steps = 0;
    
    /**
     *Request the necessary data to start a new game.
     */
    public void startGame(){}

    /**
     *Updates the matrix by randomly assigning the distribution of the
     *Quantity of lumps (only one per box) of each type defined in
     *Configuration.
     */
    public void generateBoxes(){}

    /**
     *Depending on the route the ant is updating the content
     *Of the boxes.
     */
    public void refreshBoxes(){}
    
}