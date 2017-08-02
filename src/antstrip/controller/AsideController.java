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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author kenma
 */
public class AsideController implements Initializable {

    @FXML
    private TextField nameTxt;
    @FXML
    private TextField sizeTxt;
    @FXML
    private TextField amountSugarLumpsTxt;
    @FXML
    private TextField amountSugarLumpsWineTxt;
    @FXML
    private TextField amountSugarLumpsPoisonTxt;
    @FXML
    private TextField stepsTxt;
    
    private AntsTrip antsTrip;
    private Path path;
    private Ant ant;
    
    public void setAntsTrip(AntsTrip antsTrip) {
        
        this.antsTrip = antsTrip;
     
     }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sizeTxt.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            sizeTxt.setText(newValue.replaceAll("[^\\d]", ""));
            }   
        });
        
        amountSugarLumpsTxt.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            amountSugarLumpsTxt.setText(newValue.replaceAll("[^\\d]", ""));
            }   
        });
        
        amountSugarLumpsWineTxt.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            amountSugarLumpsWineTxt.setText(newValue.replaceAll("[^\\d]", ""));
            }   
        });
        
        amountSugarLumpsPoisonTxt.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            amountSugarLumpsPoisonTxt.setText(newValue.replaceAll("[^\\d]", ""));
            }   
        });
    }    

    @FXML
    private void play(ActionEvent event) {

        ant = new Ant();
        path = new Path();
        
        int size = 7;
        int amountSugarLumps = 5;
        int amountSugarLumpsWine = 5;
        int amountSugarLumpsPoison = 5;
        
        if (!"".equals(nameTxt.getText())){
            
            ant.setName(nameTxt.getText());
            
        } else {
            
            ant.setName("Ant");
        }
        
        if (!"".equals(sizeTxt.getText())){
            size = Integer.valueOf(sizeTxt.getText());
        }
        
        if (!"".equals(amountSugarLumpsTxt.getText())){
            amountSugarLumps = Integer.valueOf(amountSugarLumpsTxt.getText());
        }
        
        if (!"".equals(amountSugarLumpsWineTxt.getText())){
            amountSugarLumpsWine = Integer.valueOf(amountSugarLumpsTxt.getText());
        }
        
        if (!"".equals(amountSugarLumpsPoisonTxt.getText())){
            amountSugarLumpsPoison = Integer.valueOf(amountSugarLumpsTxt.getText());
        }
        
        antsTrip.setAnt(ant);
        antsTrip.setPath(path);
        
        path = antsTrip.getPath();
        ant =  antsTrip.getAnt();
        
        path.startGame(size, amountSugarLumps, amountSugarLumpsWine, amountSugarLumpsPoison);
    }
    
}
