/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antstrip;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author kenma
 */
public class Path {
    
    //Matrix representing the map made the anthill
    Tile map[][];
    //The number of steps the ant has made towards the anthill. (Will be displayed at the end of the game)
    int Steps = 0;
    
    ArrayList<Tile> sugarLumpsList;

    public Path() {
        
        sugarLumpsList =  new ArrayList<>();
    }

    
    /**
     *Request the necessary data to start a new game.
     * @param size
     * @param amountSugarLumps
     * @param amountSugarLumpsWine
     * @param amountSugarLumpsPoison
     */
    public void startGame(int size, int amountSugarLumps, int amountSugarLumpsWine, int amountSugarLumpsPoison){
        
        map = new Tile[size][size];
        
        generateBoxes(size, amountSugarLumps, amountSugarLumpsWine, amountSugarLumpsPoison);
    
    }

    /**
     *Updates the matrix by randomly assigning the distribution of the
     *Quantity of lumps (only one per box) of each type defined in
     *Configuration.
     * @param amountSugarLumps
     * @param amountSugarLumpsWine
     * @param amountSugarLumpsPoison
     */
    public void generateBoxes(int size, int amountSugarLumps, int amountSugarLumpsWine, int amountSugarLumpsPoison){
    
        //Fill the matrix with empty tiles
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
            
                map[i][j] = new Tile(i, j, "empty");
//                System.out.println(map[i][j]);
            }        
        }
        
        //Calculates the size of the list
        int amountLumps = amountSugarLumps + amountSugarLumpsPoison + amountSugarLumpsWine;
        int amountLumpsPoison = 0;
        int amountLumpsWine = 0;
        
        //Fill list with random tiles
        for (int i = 0; sugarLumpsList.size() <= amountLumps; i++){
            
            Random rn = new Random();
            int x = rn.nextInt(size -1) + 1;
            int y = rn.nextInt(size -1) + 1;
            
            if (!isSugarLumpsList(x, y)){
                
                if(amountLumpsPoison <= amountSugarLumpsPoison){
                    
                    Tile newTile = new Tile(x, y, "sugarLumpsPoison");
                    sugarLumpsList.add(newTile); 
                    amountLumpsPoison++;
                } else if (amountLumpsPoison > amountSugarLumpsPoison && amountLumpsWine <= amountSugarLumpsWine){
                    
                    Tile newTile = new Tile(x, y, "sugarLumpsWine");
                    sugarLumpsList.add(newTile);
                    amountLumpsWine++;
                } else {
                    
                    Tile newTile = new Tile(x, y, "sugarLump");
                    sugarLumpsList.add(newTile);
                }
            }
        }
        
        for (Tile aTile : sugarLumpsList){
            map[aTile.getPosX()][aTile.getPosY()] = aTile;
        }
        
        sugarLumpsList.clear();
        
//        System.out.println(sugarLumpsList);
        
    }
    
    private boolean isSugarLumpsList(int x, int y){
        
        boolean is = false;
        
        for (Tile aTile : sugarLumpsList){
            
            if(aTile.getPosX() == x && aTile.getPosY() == y) {
                is = true;
            }
        }
        
        return is;
    }

    /**
     *Depending on the route the ant is updating the content
     *Of the boxes.
     */
    public void refreshBoxes(){}
    
}
