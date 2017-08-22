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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * FXML Controller class
 *
 * @author kenma
 */
public class StartController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private ComboBox<Integer> size;
    @FXML
    private ComboBox<Integer> sugarLumps;
    @FXML
    private ComboBox<Integer> sugarLumpsWine;
    @FXML
    private ComboBox<Integer> sugarLumpsPoison;
    @FXML
    private TextField steps;
    @FXML
    private GridPane grid;
    
    private AntsTrip antsTrip;
    
    private Ant ant;
    private Path path;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                
        for (int i = 7; i <= 20; i++){
        
            size.getItems().add(i);
        }
        
        for (int i = 5; i <= 15; i++){
        
            sugarLumps.getItems().add(i);
            sugarLumpsWine.getItems().add(i);
            sugarLumpsPoison.getItems().add(i);
        }
        
    }    

    @FXML
    private void play(ActionEvent event) throws IOException {
        
        ant = new Ant("Ant"); 
        path = new Path();
        
        int sizeGrid = 7;
        int amountSugarLumps = 5;
        int amountSugarLumpsWine = 5;
        int amountSugarLumpsPoison = 5;
        
        if (!"".equals(name.getText())){
            
            ant.setName(name.getText());
            
        }
        
        if (size.getSelectionModel().getSelectedItem() != null){
            sizeGrid = size.getSelectionModel().getSelectedItem();
        }
        
        if (sugarLumps.getSelectionModel().getSelectedItem() != null){
            amountSugarLumps = sugarLumps.getSelectionModel().getSelectedItem();
        }
        
        if (sugarLumpsWine.getSelectionModel().getSelectedItem() != null){
            amountSugarLumpsWine = sugarLumpsWine.getSelectionModel().getSelectedItem();
        }
        
        if (sugarLumpsPoison.getSelectionModel().getSelectedItem() != null){
            amountSugarLumpsPoison = sugarLumpsPoison.getSelectionModel().getSelectedItem();
        }
        
        antsTrip.setAnt(ant);
        antsTrip.setPath(path);
        
        path.startGame(antsTrip.getAnt(), sizeGrid, amountSugarLumps, amountSugarLumpsWine, amountSugarLumpsPoison);
        
        antsTrip.save();
        
        antsTrip.initGameUI();
    }
    
    public void update(){
        
        path = antsTrip.getPath();
        ant = antsTrip.getAnt();
    
        if(path != null){
        
            steps.setText(String.valueOf(path.getSteps()));
//            System.out.println("test"); 

            path.refreshTravel(ant.getTiles());

            //Clear the grid
            initGrid();

            Tile[][] map = path.getMapTemp();

            //Fill the matrix with empty tiles
            for (int i = 0; i < path.getSize(); i++){
                for (int j = 0; j < path.getSize(); j++){

                    grid.add(map[i][j], i, j, 1,1);
    //                System.out.println(map[i][j].getType());
                }        
            }
        }
        
    }
    
     public void initGrid(){
    
        //Clear the grid
        grid.getChildren().clear();
        
        //Remove columns and rows
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        
        //Add columns and rows
        for (int i = 0;  i < path.getSize(); i++){
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(50);
            
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(50);
            
            grid.getColumnConstraints().addAll(column); // each get 50% of width
            grid.getRowConstraints().addAll(row); // each get 50% of width
        }
        
    }

    public AntsTrip getAntsTrip() {
        return antsTrip;
    }

    public void setAntsTrip(AntsTrip antsTrip) {
        this.antsTrip = antsTrip;
    }

    public Ant getAnt() {
        return ant;
    }

    public void setAnt(Ant ant) {
        this.ant = ant;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    @FXML
    private void updateComboBoxs(ActionEvent event) {
        
        int s = ((size.getSelectionModel().getSelectedItem() * size.getSelectionModel().getSelectedItem()) - 2) / 3;
        
            sugarLumps.getItems().clear();
            sugarLumpsWine.getItems().clear();
            sugarLumpsPoison.getItems().clear();
        
        for (int i = 5; i <= s; i++){
        
            sugarLumps.getItems().add(i);
            sugarLumpsWine.getItems().add(i);
            sugarLumpsPoison.getItems().add(i);
        }        
    }
    
}
