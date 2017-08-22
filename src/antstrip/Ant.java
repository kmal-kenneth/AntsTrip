/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antstrip;

import java.util.ArrayList;
import java.util.Random;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author kenma
 */
public class Ant extends Tile {
    
    //Ant Name (Player Nickname).
    private String name = "";
    //List containing all the steps performed. There must be a way to observe this route once the ant reaches the goal or dies.
    private ArrayList<Tile> tiles;
    //Indicator from 0 to 100, which starts at 100 when creating the instance.
    private float Health = 100;
    //Indicator from 0 to 50, which is initialized to zero. When the ant reaches a total drunkenness level (50 at the alcohol level or more) it dies
    private float alcoholLevel= 0;
    //Sober, drunk, dead, poisoned
    private State state = State.SOBER;
    
    private int oldX;
    private int oldY;
    private boolean hip;
    private int Shifts;

    public Ant(String name) {
        super(0, 0, Type.ANT);
        
        this.name = name;
        Shifts = 0;
        tiles =  new ArrayList<>();
    }
    
    /**
     *It receives an address (up, down, right or left) and the current position of the ant. Move the ant in the requested direction.
     * @param event
     * @param size
     */
    public void walk(KeyEvent event, int size) {
        
        oldX = getX();
        oldY = getY();
    
        switch (event.getCode()) {
            case UP:
                if (getY() > 0) {
                    setY(getY()-1);
                }
                else {
                    if(state == State.DRUNK){
                        hip = true;
                    } else if(state == State.POISONED){
                    
                        modifyHealth(-20);
                    }
                }
                break;
            case DOWN:
                if (getY() < size - 1) {
                    setY(getY()+1);
                }
                else {
                    if(state == State.DRUNK){
                        hip = true;
                    } else if(state == State.POISONED){
                    
                        modifyHealth(-20);
                    }
                }
                break;
            case LEFT:
                if (getX() > 0) {
                    setX(getX()-1);     
                }
                else {
                    if(state == State.DRUNK){
                        hip = true;
                    } else if(state == State.POISONED){
                    
                        modifyHealth(-20);
                    }
                }
                break;
            case RIGHT:
                if (getX() < size -1) {
                    setX(getX()+1);     
                }
                else {
                    if(state == State.DRUNK){
                        hip = true;
                    } else if(state == State.POISONED){
                    
                        modifyHealth(-20);
                    }
                }
                break;
        }
        
        if(state == State.POISONED){
        
            Shifts++;
            if(Shifts > 3){
            
                changeStatus(State.SOBER);
                Shifts = 0;
            }
        }
        
//        System.out.println("(" + getX() + "," + getY() + ")");
    }

    /**
     *Move the ant in a random direction.
     * @param size
     */
    public void hip(int size) {
    
        KeyEvent key = new KeyEvent(KeyEvent.KEY_PRESSED, name, name, KeyCode.UP, true, true, true, true);
        
        Random rn = new Random();
        int direction = rn.nextInt(4);
        
        switch (direction) {
            case 1:
                key = new KeyEvent(KeyEvent.KEY_PRESSED, name, name, KeyCode.RIGHT, true, true, true, true);
                break;
            case 2:
                key = new KeyEvent(KeyEvent.KEY_PRESSED, name, name, KeyCode.DOWN, true, true, true, true);
                break;
            case 3:
                key = new KeyEvent(KeyEvent.KEY_PRESSED, name, name, KeyCode.LEFT, true, true, true, true);
                break;
        }
        
        walk(key, size);
//        System.out.println(direction);
    }

    /**
     *In case of boxes occupied by some lump he eats and acts according to a later explanation.
     * @param lump
     */
    public void eatSugarLump(Tile lump) {
    
        if(state == State.SOBER){
        
           if(lump.getType() == Type.SUGARLUMP){
           
               modifyHealth(+10);
           } else if(lump.getType() == Type.SUGARLUMPWINE){
           
               changeStatus(State.DRUNK);
               modifyHealth(-10);
               changeAlcoholLevel(+20);
               hip = true;
           }
        } else if(state == State.DRUNK){
        
            if(lump.getType() == Type.EMPTY){
            
                changeAlcoholLevel(-10);
            } else if(lump.getType() == Type.SUGARLUMP){
            
                modifyHealth(+10);
                changeAlcoholLevel(-10);
            } else if(lump.getType() == Type.SUGARLUMPPOISON){
            
                changeStatus(State.POISONED);
                modifyHealth(-50);
            } else if(lump.getType() == Type.SUGARLUMPWINE){
           
               modifyHealth(-20);
               changeAlcoholLevel(+20);
               hip = true;
           }
        } else if(state == State.POISONED){
        
            if(lump.getType() == Type.EMPTY){
            
                modifyHealth(+10);                
            } else if(lump.getType() == Type.SUGARLUMP){
            
                modifyHealth(+20);                     
            } else if(lump.getType() == Type.SUGARLUMPWINE || lump.getType() == Type.SUGARLUMPPOISON){
                changeStatus(State.DEAD);
            }
        }
        
    }

    /**
     *It receives a number as argument and increases or decreases the health indicator.
     * @param value
     */
    public void modifyHealth(int value) {
    
        Health += value;
        
        if(Health > 100){
            
            Health = 100;
        } else if(Health <= 0){
        
            Health = 0;
            changeStatus(State.DEAD);
        }
        
//        System.out.println("Health: " + Health);
    }

    /**
     *It receives a number as an argument and increases or decreases the alcohol level as appropriate.
     * @param value
     */
    public void changeAlcoholLevel(int value) {
    
        alcoholLevel += value;
        
        if(alcoholLevel > 50){
            
            Health = 50;
        } else if(alcoholLevel < 0){
        
            alcoholLevel = 0;
        }
        
//        System.out.println("Alcohol Level: " + alcoholLevel);
    }

    /**
     *It is invoked by the behavior of the game and changes the state of the ant.
     * @param state
     */
    public void changeStatus(State state) {
    
        this.state = state;
    }
    
    public void addTravelStep(Tile tile) {
    
        tiles.add(tile);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHealth() {
        return Health;
    }

    public void setHealth(float Health) {
        this.Health = Health;
    }

    public float getAlcoholLevel() {
        return alcoholLevel;
    }

    public void setAlcoholLevel(float alcoholLevel) {
        this.alcoholLevel = alcoholLevel;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getOldX() {
        return oldX;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }

    public boolean isHip() {
        return hip;
    }

    public void setHip(boolean hip) {
        this.hip = hip;
    }

    public int getShifts() {
        return Shifts;
    }

    public void setShifts(int Shifts) {
        this.Shifts = Shifts;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }
    
}
