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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    
    int x;
    int y;
    
    public void setAntsTrip(AntsTrip antsTrip) {
        
        this.antsTrip = antsTrip;
        
        path = this.antsTrip.getPath();
        ant =  this.antsTrip.getAnt();
     
     }
    
    public void ModifyPathGrid(){
        
        x = 0;
        y = 0;
        
        path = this.antsTrip.getPath();
        ant =  this.antsTrip.getAnt();
        
        RemoveColumnsRows();
        
        addColumnsRows();
        
        pathGrid.getChildren().clear();
                
        Tile[][] map = path.getMap();
                        
        //Fill the matrix with empty tiles
        for (int i = 0; i < path.getSize(); i++){
            for (int j = 0; j < path.getSize(); j++){
                
                try {                    
                    switch (map[i][j].getType()) {
                        case "sugarLumpsPoison":
                           FXMLLoader loader2 = new FXMLLoader();
                            loader2.setLocation(AntsTrip.class.getResource("view/TileSugarPoisonLump.fxml"));
                            VBox tileSugarPoisonLump = (VBox) loader2.load();
                            
                            pathGrid.add(tileSugarPoisonLump, i, j, 1,1);
                            break;
                        case "sugarLump":
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(AntsTrip.class.getResource("view/TileSugarLump.fxml"));
                            VBox tileSugarLump = (VBox) loader.load();
                            
                            pathGrid.add(tileSugarLump, i, j, 1,1);
                            break;
                        case "sugarLumpsWine":
                            FXMLLoader loader1 = new FXMLLoader();
                            loader1.setLocation(AntsTrip.class.getResource("view/TileSugarWineLump.fxml"));
                            VBox tileSugarWineLump = (VBox) loader1.load();
                            
                            pathGrid.add(tileSugarWineLump, i, j, 1,1);
                            break;
                        default:
                            FXMLLoader loader3 = new FXMLLoader();
                            loader3.setLocation(AntsTrip.class.getResource("view/Tile.fxml"));
                            VBox tile = (VBox) loader3.load();
                            
                            pathGrid.add(tile, i, j, 1,1);
                            break;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(PathController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }        
        }
        
        addAntAntHill();
        
      pathGrid.requestFocus();
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
    
    private void addAntAntHill(){
    
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(AntsTrip.class.getResource("view/TileAnt.fxml"));
            VBox tileAnt = (VBox) loader2.load();
            
            pathGrid.add(tileAnt, 0, 0, 1,1);
            
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(AntsTrip.class.getResource("view/TileAntHill.fxml"));
            VBox antHill = (VBox) loader1.load();
            
            pathGrid.add(antHill, path.getSize() -1,  path.getSize() -1, 1,1);
        } catch (IOException ex) {
            Logger.getLogger(PathController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void moveAnt(){
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(AntsTrip.class.getResource("view/TileAnt.fxml"));
            VBox tileAnt = (VBox) loader2.load();
            
            pathGrid.add(tileAnt, x, y, 1,1);
        } catch (IOException ex) {
            Logger.getLogger(PathController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    private void moveUP(){
        
        moveAnt();
        
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(AntsTrip.class.getResource("view/Tile.fxml"));
            VBox tile = (VBox) loader2.load();
            
            pathGrid.add(tile, x, y +1, 1,1);
        } catch (IOException ex) {
            Logger.getLogger(PathController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    private void moveDOWN(){
    
        moveAnt();
        
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(AntsTrip.class.getResource("view/Tile.fxml"));
            VBox tile = (VBox) loader2.load();
            
            pathGrid.add(tile, x, y -1, 1,1);
        } catch (IOException ex) {
            Logger.getLogger(PathController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    private void moveLEFT(){
    
        moveAnt();
        
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(AntsTrip.class.getResource("view/Tile.fxml"));
            VBox tile = (VBox) loader2.load();
            
            pathGrid.add(tile, x +1, y, 1,1);
        } catch (IOException ex) {
            Logger.getLogger(PathController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void moveRIGHT(){
    
        moveAnt();
        
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(AntsTrip.class.getResource("view/Tile.fxml"));
            VBox tile = (VBox) loader2.load();
            
            pathGrid.add(tile, x -1, y, 1,1);
        } catch (IOException ex) {
            Logger.getLogger(PathController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
     * Initializes the controller clas  s.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void focus(MouseEvent event) {
        if (path != null){
        
            pathGrid.requestFocus();
        }
    }

    @FXML
    private void refreshBoxes(KeyEvent event) {
        
        switch (event.getCode()) {
            case UP:
                if (y > 0) {
                    y--;
                    moveUP();
                }
                break;
            case DOWN:
                if (y < path.getSize() - 1) {
                    y++;
                    moveDOWN();
                }
                break;
            case LEFT:
                if (x > 0) {
                    x--;     
                    moveLEFT();
                }
                break;
            case RIGHT:
                if (x < path.getSize() -1) {
                    x++;     
                    moveRIGHT();
                }
                break;
        }
        System.out.println("(" + x + "," + y + ")");
    }
    
}
