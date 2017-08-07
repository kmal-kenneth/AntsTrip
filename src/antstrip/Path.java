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
    private Tile map[][];
    //The number of steps the ant has made towards the anthill. (Will be displayed at the end of the game)
    private int Steps;
    
    private int size;
    
    ArrayList<Tile> sugarLumpsList;

    public Path() {
        
        sugarLumpsList =  new ArrayList<>();
        Steps = 0;
        size = 0;
    }

    
    /**
     *Request the necessary data to start a new game.
     * @param size
     * @param amountSugarLumps
     * @param amountSugarLumpsWine
     * @param amountSugarLumpsPoison
     */
    public void startGame(int size, int amountSugarLumps, int amountSugarLumpsWine, int amountSugarLumpsPoison){
        
        this.size = size;
        
        map = new Tile[this.size][this.size];
        
        generateBoxes(amountSugarLumps, amountSugarLumpsWine, amountSugarLumpsPoison);
            
    }

    /**
     *Updates the matrix by randomly assigning the distribution of the
     *Quantity of lumps (only one per box) of each type defined in
     *Configuration.
     * @param amountSugarLumps
     * @param amountSugarLumpsWine
     * @param amountSugarLumpsPoison
     */
    public void generateBoxes(int amountSugarLumps, int amountSugarLumpsWine, int amountSugarLumpsPoison){
    
        //Fill the matrix with empty tiles
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
            
                map[i][j] = new Tile(i, j, "empty");
//                System.out.println(map[i][j]);
            }        
        }
        
        //Calculates the size of the list
        int amountLumps = amountSugarLumps + amountSugarLumpsPoison + amountSugarLumpsWine;
        int amountLumpsPoison = 1;
        int amountLumpsWine = 1;
        
        //Fill list with random tiles
        for (int i = 0; sugarLumpsList.size() < amountLumps; i++){
            
            Random rn = new Random();
            int x = rn.nextInt(size);
            int y = rn.nextInt(size);
            
            if (!isSugarLumpsList(x, y)){
                
                if (x + y != 0) {
                
                    if(x + y + 2 != size * 2){
                        
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
    public void refreshBoxes(int x, int y){
//        if (map[x][y].getType().equals(Steps))
    }

    public Tile[][] getMap() {
        return map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }

    public int getSteps() {
        return Steps;
    }

    public void setSteps(int Steps) {
        this.Steps = Steps;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    
}
