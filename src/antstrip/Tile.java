/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antstrip;

import javafx.scene.layout.VBox;

/**
 *
 * @author kenma
 */
public class Tile extends VBox {
    
    private Type type;
    
    private int x;
    private int y;

    public Tile(int x, int y, Type type) {
        this.type = type;
        this.x = x;
        this.y = y;
        
        switch (this.type) {
            case EMPTY:
                this.getStyleClass().add("empty");
                break;
            case SUGARLUMP:
                this.getStyleClass().add("empty");
                break;
            case SUGARLUMPWINE:
                this.getStyleClass().add("empty");
                break;
            case SUGARLUMPPOISON:
                this.getStyleClass().add("empty");
                break;
            case ANT:
                this.getStyleClass().add("ant");
                break;
            case ANTHILL:
                this.getStyleClass().add("anthill");
                break;
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
