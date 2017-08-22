/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antstrip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author kenma
 */
public class Path {
    
    //Matrix representing the map made the anthill
    private Tile map[][];
    private Tile mapTemp[][];
    //The number of steps the ant has made towards the anthill. (Will be displayed at the end of the game)
    private int Steps;
    
    private int size;
    private Ant ant;
    private Tile temp;
    
    ArrayList<Tile> sugarLumpsList;

    public Path() {
        
        sugarLumpsList =  new ArrayList<>();
        Steps = 0;
        size = 0;
        temp = null;
    }

    
    /**
     *Request the necessary data to start a new game.
     * @param ant
     * @param size
     * @param amountSugarLumps
     * @param amountSugarLumpsWine
     * @param amountSugarLumpsPoison
     */
    public void startGame(Ant ant, int size, int amountSugarLumps, int amountSugarLumpsWine, int amountSugarLumpsPoison){
        
        this.ant = ant;
        this.size = size;
        
        map = new Tile[this.size][this.size];
        mapTemp = new Tile[this.size][this.size];
        
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
            
                map[i][j] = new Tile(i, j, Type.EMPTY);
                mapTemp[i][j] = new Tile(i, j, Type.EMPTY);
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
                            
                            Tile newTile = new Tile(x, y, Type.SUGARLUMPPOISON);
                            sugarLumpsList.add(newTile);
                            amountLumpsPoison++;
                        } else if (amountLumpsPoison > amountSugarLumpsPoison && amountLumpsWine <= amountSugarLumpsWine){
                            
                            Tile newTile = new Tile(x, y, Type.SUGARLUMPWINE);
                            sugarLumpsList.add(newTile);
                            amountLumpsWine++;
                        } else {
                            
                            Tile newTile = new Tile(x, y, Type.SUGARLUMP);
                            sugarLumpsList.add(newTile);
                        }
                    }
                }
                    
            }
        }
                
        //Insert the random tile
        for (Tile aTile : sugarLumpsList) {
            map[aTile.getX()][aTile.getY()] = aTile;
            mapTemp[aTile.getX()][aTile.getY()] = aTile;
//            System.out.println("(" + aTile.getX() + "," + aTile.getY() + ")");
        }
        
        sugarLumpsList.clear();
                                
        
        //insert ant and anthill
        map[0][0] = ant;
        mapTemp[0][0] = ant;
        map[size-1][size-1] = new Tile(size-1, size-1, Type.ANTHILL);        
        mapTemp[size-1][size-1] = new Tile(size-1, size-1, Type.ANTHILL);        
        
    }
    
    private boolean isSugarLumpsList(int x, int y){
        
        boolean is = false;
        
        for (Tile aTile : sugarLumpsList){
            
            if(aTile.getX() == x && aTile.getY() == y) {
                is = true;
            }
        }
        
        return is;
    }

    /**
     *Depending on the route the ant is updating the content
     *Of the boxes.
     */
    public Tile refreshBoxes(int oldX,  int oldY, int newX, int newY, State state){
        
         Tile temp2 = null ;
         
        if (!(oldX == newX && oldY == newY)){
            
            switch (map[newX][newY].getType()) {

            case SUGARLUMPPOISON:
                map[newX][newY].getStyleClass().remove("empty");
                map[newX][newY].getStyleClass().add("poison");
                break;
        }
            
            if(state == State.SOBER && map[newX][newY].getType() == Type.SUGARLUMPPOISON){
                
                Tile temp1 =  map[newX][newY];
                temp1.getStyleClass().add("travel");
                temp2 =  map[newX][newY];
                
                map[newX][newY] = map[oldX][oldY];
                map[oldX][oldY] = new Tile(oldX, oldY, Type.EMPTY);
                map[oldX][oldY].getStyleClass().add("travel");
                
                if(temp != null){
            
                   
                    map[temp.getX()][temp.getY()] = temp;
                    temp = null;
                }
                
                temp = temp1;
            } else {
                
                temp2 =  map[newX][newY];
                
                map[newX][newY] = map[oldX][oldY];
                map[oldX][oldY] = new Tile(oldX, oldY, Type.EMPTY);
                map[oldX][oldY].getStyleClass().add("travel");
                
                if(temp != null){
            
                    map[temp.getX()][temp.getY()] = temp;
                    temp = null;
                }
                
            }
        }
        
                return temp2;
    }
    
    public void refreshTravel(ArrayList<Tile> tiles){
        
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
            
                switch (mapTemp[i][j].getType()) {
                    case SUGARLUMP:
                        mapTemp[i][j].getStyleClass().add("sugar");
                        mapTemp[i][j].getStyleClass().remove("empty");
                        break;
                    case SUGARLUMPWINE:
                        mapTemp[i][j].getStyleClass().add("wine");
                        mapTemp[i][j].getStyleClass().remove("empty");
                        break;
                    case SUGARLUMPPOISON:
                        mapTemp[i][j].getStyleClass().add("poison");
                        mapTemp[i][j].getStyleClass().remove("empty");
                        break;
                }
            }        
        }
        
        for(Tile t : tiles){
        
            mapTemp[t.getX()][t.getY()].getStyleClass().add("travel");
//            System.out.println(mapTemp[t.getX()][t.getY()].getStyleClass().toString());
        }
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

    public Ant getAnt() {
        return ant;
    }

    public void setAnt(Ant ant) {
        this.ant = ant;
    }

    public Tile getTemp() {
        return temp;
    }

    public void setTemp(Tile temp) {
        this.temp = temp;
    }

    public Tile[][] getMapTemp() {
        return mapTemp;
    }

    public void setMapTemp(Tile[][] mapTemp) {
        this.mapTemp = mapTemp;
    }
    
}
