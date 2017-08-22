/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antstrip.controller;

import antstrip.Tile;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author kenma
 */
public class NotifyController implements Initializable {

    @FXML
    private Pane tile;
    @FXML
    private Label txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void init(Tile tile) {
        
        switch (tile.getType()) {
            case EMPTY:
                txt.setText("The road is clear.");
                this.tile.getStyleClass().add("path");
                break;
            case SUGARLUMP:
                txt.setText("Sugar gives me strength.");
                this.tile.getStyleClass().add("sugar");
                break;
            case SUGARLUMPWINE:
                txt.setText("For the queen. Hip.");
                this.tile.getStyleClass().add("wine");
                break;
            case SUGARLUMPPOISON:
                txt.setText("Was that poison?");
                this.tile.getStyleClass().add("poison");
                break;
            case ANTHILL:
                txt.setText("My queen, I've come home.");
                this.tile.getStyleClass().add("anthill");
                break;
        }
                this.tile.getStyleClass().add("tile");
    }    
}
