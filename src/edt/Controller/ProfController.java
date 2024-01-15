/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edt.Controller;

import edt.AnotherScene;
import edt.Model.Connexion;
import edt.Model.Prof;
import edt.Edt;
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
public class ProfController implements Initializable {

    private int id;
    private boolean radio = false;

    @FXML
    private TextField nom;
    @FXML
    private TextField contact;
    @FXML
    private TableView profList;
    @FXML 
    private TableColumn<Prof, Integer> idp;
    @FXML 
    private TableColumn<Prof, String> nomp;
    @FXML 
    private TableColumn<Prof, String> contactp;

    @FXML
    public void toProf(ActionEvent event){
        AnotherScene.anotherScene("Prof.fxml");
    }
    @FXML
    public void toMatiere(ActionEvent event){
        AnotherScene.anotherScene("Matiere.fxml");
    }
    @FXML
    public void toClasse(){
        AnotherScene.anotherScene("Classe.fxml");
    }
    @FXML
    public void toSalle(){
        AnotherScene.anotherScene("Salle.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        idp.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomp.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        contactp.setCellValueFactory(cellData -> cellData.getValue().contactProperty());
        
        Prof p = new Prof();
        
        profList.setItems(p.read());
        
        profList.setOnMouseClicked(event -> getSelected());
        } catch(Exception e){
            
        }
    }    
    
    @FXML
    private void insertBtn(ActionEvent event){
        if (radio == false){
        String name = nom.getText();
        String number = contact.getText();
        Prof p = new Prof();
        p.setNom(name);
        p.setContact(number);
        p.insert(p);
        nom.setText("");
        contact.setText("");
        refresh();
        successdialogue("Prof ajouté");   
        }
    }
    
    @FXML
    private void updateBtn(ActionEvent event){
        if(radio == true){
            Prof p = (Prof) profList.getSelectionModel().getSelectedItem();
            
            Prof temp = new Prof();
            temp.setId(id);
            temp.setNom(nom.getText());
            temp.setContact(contact.getText());
            p.update(temp);
            nom.setText("");
            contact.setText("");
            refresh();
            successdialogue("Prof modifié");
            radio = false;
        }
    }
    
        @FXML
    private void deleteBtn(ActionEvent event){
        if(radio == true){
            Prof p = (Prof) profList.getSelectionModel().getSelectedItem();
            
            Prof temp = new Prof();
            temp.setId(id);
            temp.setNom(nom.getText());
            temp.setContact(contact.getText());
            p.delete(temp);
            nom.setText("");
            contact.setText("");
            refresh();
            successdialogue("Prof supprimé");
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
        Prof p = (Prof) profList.getSelectionModel().getSelectedItem();
        
        if (p != null){
            id = p.getId();
            nom.setText(p.getNom());
            contact.setText(p.getContact());
            radio = true;
        }
    }
    
    public void refresh (){
        profList.setItems(null);
        profList.layout();
        Prof p = new Prof();
        
        profList.setItems(p.read());
        
        profList.setOnMouseClicked(event -> getSelected());
    }
}
