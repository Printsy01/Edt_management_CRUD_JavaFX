/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edt.Controller;

import edt.Model.Connexion;
import edt.Model.Classe;
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
public class ClasseController implements Initializable {

    private int id;
    private boolean radio = false;
    @FXML
    private TextField classe;
    @FXML
    private TextField effectif;
    @FXML
    private TableView classeList;
    @FXML 
    private TableColumn<Classe, Integer> idc;
    @FXML 
    private TableColumn<Classe, String> lClasse;
    @FXML 
    private TableColumn<Classe, Integer> lEffectif;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
        idc.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        lClasse.setCellValueFactory(cellData -> cellData.getValue().classeProperty());
        lEffectif.setCellValueFactory(cellData -> cellData.getValue().effectifProperty().asObject());
        
        Classe c = new Classe();
        
        classeList.setItems(c.read());
        
        System.out.print(c.read());
        
        classeList.setOnMouseClicked(event -> getSelected());
    
        } catch(Exception e){
            
        }
    }
    
    @FXML
    private void insertBtn(ActionEvent event){
        if (radio == false){
        String c = classe.getText();
        int e = Integer.parseInt(effectif.getText());
        Classe c1 = new Classe();
        c1.setClasse(c);
        c1.setEffectif(e);
        c1.insert(c1);
        classe.setText("");
        effectif.setText("");
        refresh();
        successdialogue("Classe ajouté");   
        }
    }
    
    @FXML
    private void updateBtn(ActionEvent event){
        if(radio == true){
            Classe c = (Classe) classeList.getSelectionModel().getSelectedItem();
            
            Classe temp = new Classe();
            temp.setId(id);
            temp.setClasse(classe.getText());
            temp.setEffectif(Integer.parseInt(effectif.getText()));
            c.update(temp);
            classe.setText("");
            effectif.setText("");
            refresh();
            successdialogue("Classe modifié");
            radio = false;
            id = 0;
        }
    }
    
        @FXML
    private void deleteBtn(ActionEvent event){
        if(radio == true){
            Classe c = (Classe) classeList.getSelectionModel().getSelectedItem();
            
            Classe temp = new Classe();
            temp.setId(id);
            temp.setClasse(classe.getText());
            temp.setEffectif(Integer.parseInt(effectif.getText()));
            c.delete(temp);
            classe.setText("");
            effectif.setText("");
            refresh();
            successdialogue("Classe supprimé");
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
        Classe c = (Classe) classeList.getSelectionModel().getSelectedItem();
        
        if (c != null){
            id = c.getId();
            classe.setText(c.getClasse());
            effectif.setText(c.getEffectif() + "");
            radio = true;
        }
    }
    
    public void refresh (){
        try {
        classeList.setItems(null);
        classeList.layout();
        Classe c = new Classe();
        
        classeList.setItems(c.read());
        
        classeList.setOnMouseClicked(event -> getSelected());
    
        } catch (Exception e){
            throw(e);
        }
    }
        
}
