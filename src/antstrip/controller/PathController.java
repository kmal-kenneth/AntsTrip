/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antstrip.controller;

import antstrip.Ant;
import antstrip.AntsTrip;
import antstrip.Path;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author kenma
 */
public class PathController implements Initializable {

    @FXML
    private GridPane pathGrid;
    
    private AntsTrip antsTrip;
    private Path path;
    private Ant ant;
    
    public void setAntsTrip(AntsTrip antsTrip) {
        
        this.antsTrip = antsTrip;
        
        path = this.antsTrip.getPath();
        ant =  this.antsTrip.getAnt();
     
     }
    
    private void ModifyPathGid(){
    
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
