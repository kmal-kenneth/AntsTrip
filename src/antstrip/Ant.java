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
public class Ant {
    
    //Ant Name (Player Nickname).
    private String name = "";
    //List containing all the steps performed. There must be a way to observe this route once the ant reaches the goal or dies.
    private int Travel = 0;
    //Indicator from 0 to 100, which starts at 100 when creating the instance.
    private int Health = 100;
    //Indicator from 0 to 50, which is initialized to zero. When the ant reaches a total drunkenness level (50 at the alcohol level or more) it dies
    private int alcoholLevel= 0;
    //Sober, drunk, dead, poisoned
    private String state = "sober";

    public Ant() {
    }
    
    /**
     *It receives an address (up, down, right or left) and the current position of the ant. Move the ant in the requested direction.
     */
    public void walk() {}

    /**
     *Move the ant in a random direction.
     */
    public void hip() {}

    /**
     *In case of boxes occupied by some lump he eats and acts according to a later explanation.
     */
    public void eatSugarLump() {}

    /**
     *It receives a number as argument and increases or decreases the health indicator.
     */
    public void modifyHealth() {}

    /**
     *It receives a number as an argument and increases or decreases the alcohol level as appropriate.
     */
    public void changeAlcoholLevel() {}

    /**
     *It is invoked by the behavior of the game and changes the state of the ant.
     */
    public void changeStatus() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTravel() {
        return Travel;
    }

    public void setTravel(int Travel) {
        this.Travel = Travel;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int Health) {
        this.Health = Health;
    }

    public int getAlcoholLevel() {
        return alcoholLevel;
    }

    public void setAlcoholLevel(int alcoholLevel) {
        this.alcoholLevel = alcoholLevel;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    
    
}
