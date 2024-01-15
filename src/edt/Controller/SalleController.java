/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edt.Controller;

import edt.Model.Connexion;
import edt.Model.Salle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Princy Andriam
 */
public class SalleController implements Initializable {

    private int id;
    private boolean radio = false;
    private String[] T = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"}; 
    
    @FXML
    private TextField salle;
    @FXML
    private TextField capacite;
    @FXML
    private TableView salleList;
    @FXML 
    private TableColumn<Salle, Integer> ids;
    @FXML 
    private TableColumn<Salle, String> lSalle;
    @FXML 
    private TableColumn<Salle, Integer> lCapacite;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
        ids.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        lSalle.setCellValueFactory(cellData -> cellData.getValue().salleProperty());
        lCapacite.setCellValueFactory(cellData -> cellData.getValue().capaciteProperty().asObject());
        
        Salle s = new Salle();
        
        salleList.setItems(s.read());
        
        salleList.setOnMouseClicked(event -> getSelected());
    
        } catch(Exception e){
            
        }
    }
    
    @FXML
    private void insertBtn(ActionEvent event){
        if (radio == false){
        String s = salle.getText();
        int e = Integer.parseInt(capacite.getText());
        Salle c1 = new Salle();
        c1.setSalle(s);
        c1.setCapacite(e);
        c1.insert(c1);
        salle.setText("");
        capacite.setText("");
        refresh();
        successdialogue("Classe ajouté");   
        }
    }
    
    @FXML
    private void updateBtn(ActionEvent event){
        if(radio == true){
            Salle s = (Salle) salleList.getSelectionModel().getSelectedItem();
            
            Salle temp = new Salle();
            temp.setId(id);
            temp.setSalle(salle.getText());
            temp.setCapacite(Integer.parseInt(capacite.getText()));
            s.update(temp);
            salle.setText("");
            capacite.setText("");
            refresh();
            successdialogue("Salle modifié");
            radio = false;
            id = 0;
        }
    }
    
        @FXML
    private void deleteBtn(ActionEvent event){
        if(radio == true){
            Salle s = (Salle) salleList.getSelectionModel().getSelectedItem();
            
            Salle temp = new Salle();
            temp.setId(id);
            temp.setSalle(salle.getText());
            temp.setCapacite(Integer.parseInt(capacite.getText()));
            s.delete(temp);
            salle.setText("");
            capacite.setText("");
            refresh();
            successdialogue("Salle supprimé");
            radio = false;
        }
    }
        private void successdialogue(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private void getSelected(){
        Salle s = (Salle) salleList.getSelectionModel().getSelectedItem();
        
        if (s != null){
            id = s.getId();
            salle.setText(s.getSalle());
            capacite.setText(s.getCapacite() + "");
            radio = true;
        }
    }
    
    public void refresh (){
        try {
        salleList.setItems(null);
        salleList.layout();
        Salle s = new Salle();
        
        salleList.setItems(s.read());
        
        salleList.setOnMouseClicked(event -> getSelected());
    
        } catch (Exception e){
            throw(e);
        }
    }
        
}
