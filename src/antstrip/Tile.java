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
public class Tile {
    
    private int posX;
    private int posY;
    
    private String type;

    public Tile(int posX, int podY, String type) {
        this.posX = posX;
        this.posY = podY;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int podY) {
        this.posY = podY;
    }    
}
