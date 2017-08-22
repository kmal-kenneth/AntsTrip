/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antstrip;

import antstrip.controller.GameController;
import antstrip.controller.StartController;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author kenma
 */
public class AntsTrip extends Application {
    
    private Stage primaryStage;
    private BorderPane root;
    
    private Ant ant;
    private Path path;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Ant's Trip");
        this.primaryStage.setResizable(true);
        this.primaryStage.getIcons().add(new Image("antstrip/img/ant.png"));
        
        root = new BorderPane();
        
        initStartUI();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(AntsTrip.class.getResource("view/myTheme.css").toExternalForm());
        scene.getStylesheets().add(AntsTrip.class.getResource("view/bootstrap3.css").toExternalForm());
        
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }
    
    public void initStartUI() throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AntsTrip.class.getResource("view/Start.fxml"));
        BorderPane start = (BorderPane) loader.load();
        root.setCenter(start);
        
        StartController startController = loader.getController();
        startController.setAntsTrip(this);
        startController.update(); 
//        startController.setAnt(ant);
//        startController.setPath(path);
    }
    
    public void initGameUI() throws IOException{
    
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AntsTrip.class.getResource("view/Game.fxml"));
        BorderPane game = (BorderPane) loader.load();
        root.setCenter(game);
        
        GameController gameController = loader.getController();
        gameController.setAntsTrip(this);
        
        gameController.setAnt(ant);
        gameController.setPath(path);
        gameController.initGrid();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
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
    
}
