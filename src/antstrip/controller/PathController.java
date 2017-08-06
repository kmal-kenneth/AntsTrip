/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antstrip.controller;

import antstrip.Ant;
import antstrip.AntsTrip;
import antstrip.Path;
import antstrip.Tile;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

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
    
    public void ModifyPathGrid(){
        
        RemoveColumnsRows();
        
        addColumnsRows();
        
        pathGrid.getChildren().clear();
        
        path = this.antsTrip.getPath();
        
        Tile[][] map = path.getMap();
                
        //Fill the matrix with empty tiles
        for (int i = 0; i < path.getSize(); i++){
            for (int j = 0; j < path.getSize(); j++){
            
                if ( map[i][j].getType() == "empty"){
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(AntsTrip.class.getResource("view/TileSugarLump.fxml"));
                        VBox path = (VBox) loader.load();
                        
//                        path.setMinSize(470 / this.path.getSize(), 470 / this.path.getSize());
                        
                        pathGrid.add(path, i, j, 1,1);
                    } catch (IOException ex) {
                        Logger.getLogger(PathController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
               
            }        
        }                
    }
    
    public void addColumnsRows(){
    
        for (int i = 0;  i < path.getSize(); i++){
             ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(50);
            
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(50);
            
            pathGrid.getColumnConstraints().addAll(column); // each get 50% of width
            pathGrid.getRowConstraints().addAll(row); // each get 50% of width
        }
    }
    
    public void RemoveColumnsRows(){
    
        pathGrid.getColumnConstraints().clear();
        pathGrid.getRowConstraints().clear();
    }

    /**
     * Initializes the controller clas  s.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
