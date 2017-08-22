/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antstrip.controller;

import antstrip.Ant;
import antstrip.AntsTrip;
import antstrip.Path;
import antstrip.State;
import antstrip.Tile;
import antstrip.Type;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kenma
 */
public class GameController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label state;
    @FXML
    private ProgressBar health;
    @FXML
    private ProgressBar alcohol;
    @FXML
    private GridPane grid;
    
    private AntsTrip antsTrip;
    private Ant ant;
    private Path path;
    @FXML
    private Label numHealth;
    @FXML
    private Label numAlcohol;
    @FXML
    private Button back;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox notifyPane;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
//        grid.getStyleClass().add("path");
        
    }    

    /**
    * Catch the key was pressed in the keyboard.
    */
    @FXML
    private void catchKey(KeyEvent event) throws IOException {
        
        if((ant.getState() != State.DEAD) && ((ant.getX() != path.getSize()-1) || (ant.getY() != path.getSize()-1))){
            
            ant.walk(event, path.getSize());
            evaluate();
        }        
        
    }
    
    /**
    * Evaluate the actions on the game.
    */
    private void evaluate() throws IOException{
    
        boolean repeat = false;
        Tile[][] map = path.getMap();
        
        if(map[ant.getX()][ ant.getY()].getType() != Type.ANT && map[ant.getX()][ ant.getY()].getType() != Type.ANTHILL){
            
            ant.eatSugarLump(map[ant.getX()][ ant.getY()]);
               
        }
        
        notify(map[ant.getX()][ ant.getY()]);
        
        Tile t = path.refreshBoxes(ant.getOldX(),ant.getOldY(), ant.getX(), ant.getY(), ant.getState());
        
        if(t != null){
        
            ant.addTravelStep(t);
        }
        
        if(ant.isHip()){
        
            ant.hip(path.getSize());
            ant.setHip(false);
            
//            path.refreshBoxes(ant.getOldX(),ant.getOldY(), ant.getX(), ant.getY());
            repeat = true;
        }
        
//        System.out.println("(" + tempx + " , " + tempy + ") (" + ant.getX() + " , " + ant.getY() + ")");
        
        updateGrid();
        
        path.setSteps(path.getSteps() +1);
        
        if(repeat){
            evaluate();
        }        
        
    }

    /**
    * Catch the focus when the mouse clicking the grid. Grid get the focus.
    */
    @FXML
    private void getFocus(MouseEvent event) {
        
        grid.requestFocus();
        
    }
    
    /**
    * Initialize the grid. Clear the grid of childrens, columns and rows.
    * Add columns and rows.
    */
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
        
        updateGrid();
    }
    
    /**
    * Update the grid, clear the childrens and add the news tiles.
    */
    public void updateGrid(){
        
        //Clear the grid
        grid.getChildren().clear();
        
        Tile[][] map = path.getMap();
                        
        //Fill the matrix with empty tiles
        for (int i = 0; i < path.getSize(); i++){
            for (int j = 0; j < path.getSize(); j++){
                
                grid.add(map[i][j], i, j, 1,1);
//                System.out.println(map[i][j].getType());
            }        
        }
        
        updateData();
        
    }
    
    public void updateData(){
    
        name.setText(ant.getName());
        state.setText(ant.getState().getName());
        numHealth.setText(String.valueOf(ant.getHealth()));
        numAlcohol.setText(String.valueOf(ant.getAlcoholLevel()));
        
        final Timeline timeline = new Timeline();
        final KeyValue kv = new KeyValue(health.progressProperty(),(ant.getHealth() / 100));
        final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        
        final Timeline timeline1 = new Timeline();
        final KeyValue kv1 = new KeyValue(alcohol.progressProperty(),(ant.getAlcoholLevel()/ 50));
        final KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
        timeline1.getKeyFrames().add(kf1);
        timeline1.play();
        
        if(!((ant.getState() != State.DEAD) && ((ant.getX() != path.getSize()-1) || (ant.getY() != path.getSize()-1)))){
        
            gameOver();
        }
    }
    
    private void gameOver(){
        
        back.setVisible(true);
//        System.out.println(path.getSteps());
    }
    
    private void notify(Tile tile) throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AntsTrip.class.getResource("view/Notify.fxml"));
        BorderPane notify = (BorderPane) loader.load();
        notifyPane.getChildren().add(notify);
        
        NotifyController notifyController = loader.getController();
        notifyController.init(tile);
        
        final Timeline timeline = new Timeline();
        final KeyValue kv = new KeyValue(scroll.vvalueProperty(), 1.0);
        final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        
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
    private void back(ActionEvent event) {
        try {
            antsTrip.initStartUI();
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
