/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antstrip;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import antstrip.controller.AsideController;

/**
 *
 * @author kenma
 */
public class AntsTrip extends Application {
    
    private Stage primaryStage;
    private BorderPane root;
    
    private Path path;
    private Ant ant;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Ant's Trip");
        this.primaryStage.setResizable(false);
        
        initRoot();
        initAside();
        initPath();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(AntsTrip.class.getResource("view/myTheme.css").toExternalForm());
        scene.getStylesheets().add(AntsTrip.class.getResource("view/bootstrap3.css").toExternalForm());
        
        stage.setScene(scene);
        stage.show();
    }

    private void initRoot() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AntsTrip.class.getResource("view/AntsTrip.fxml"));
        root = (BorderPane) loader.load();
    }
    
    private void initAside() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AntsTrip.class.getResource("view/Aside.fxml"));
        VBox aside = (VBox) loader.load();
        
        root.setLeft(aside);
        
        AsideController asideController = loader.getController();
        asideController.setAntsTrip(this);
    }
    
    private void initPath() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AntsTrip.class.getResource("view/Path.fxml"));
        VBox path = (VBox) loader.load();
        
        root.setCenter(path);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Ant getAnt() {
        return ant;
    }

    public void setAnt(Ant ant) {
        this.ant = ant;
    }
    
    
    
}
