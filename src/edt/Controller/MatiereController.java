/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edt.Controller;

import edt.Model.Connexion;
import edt.Model.Matiere;
import edt.Model.Prof;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Princy Andriam
 */
public class MatiereController implements Initializable {

    private int id;
    private boolean radio = false;
    @FXML
    private TextField intitule;
    @FXML
    private TextField volume;
    @FXML
    private ComboBox<Prof> prof;
    @FXML
    private TableView matList;
    @FXML 
    private TableColumn<Matiere, Integer> idm;
    @FXML 
    private TableColumn<Matiere, String> matI;
    @FXML 
    private TableColumn<Matiere, Integer> matV;
    @FXML 
    private TableColumn<Matiere, Integer> matP;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        idm.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        matI.setCellValueFactory(cellData -> cellData.getValue().MatProperty());
        matV.setCellValueFactory(cellData -> cellData.getValue().VolumeProperty().asObject());
        matP.setCellValueFactory(cellData -> cellData.getValue().idprofProperty().asObject());
        
        Matiere m = new Matiere();
        
        matList.setItems(m.read());
        
        Prof temp = new Prof();
        prof.setItems(temp.read());
        prof.setConverter(new StringConverter<Prof>() {
            public String toString(Prof p){
                return p != null ? p.getNom():"";
            }

            @Override
            public Prof fromString(String string) {
                return null;
            }
        });
        
        matList.setOnMouseClicked(event -> getSelected());
        } catch(Exception e){
            
        }
    }    
    
    @FXML
    private void insertBtn(ActionEvent event){
        if (radio == false){
        String name = intitule.getText();
        int v = Integer.parseInt(volume.getText());
        int p = prof.getSelectionModel().getSelectedItem().getId();
        Matiere m = new Matiere();
        m.setMat(name);
        m.setVolume(v);
        m.setIdprof(p);
        m.insert(m);
        intitule.setText("");
        volume.setText("");
        prof.getSelectionModel().clearSelection();
        refresh();
        successdialogue("Matiere ajouté");   
        }
    }
    
    @FXML
    private void updateBtn(ActionEvent event){
        if(radio == true){
            Matiere m = (Matiere) matList.getSelectionModel().getSelectedItem();
            
            Matiere temp = new Matiere();
            temp.setId(id);
            temp.setMat(intitule.getText());
            temp.setVolume(Integer.parseInt(volume.getText()));
            temp.setIdprof(prof.getSelectionModel().getSelectedItem().getId());
            m.update(temp);
            intitule.setText("");
            volume.setText("");
            prof.getSelectionModel().clearSelection();
            refresh();
            successdialogue("Matière modifié");
            radio = false;
        }
    }
    
        @FXML
    private void deleteBtn(ActionEvent event){
        if(radio == true){
            Matiere m = (Matiere) matList.getSelectionModel().getSelectedItem();
            
            Matiere temp = new Matiere();
            temp.setId(id);
            temp.setMat(intitule.getText());
            temp.setVolume(Integer.parseInt(volume.getText()));
            temp.setIdprof(prof.getSelectionModel().getSelectedItem().getId());
            m.delete(temp);
            intitule.setText("");
            volume.setText("");
            prof.getSelectionModel().clearSelection();
            refresh();
            successdialogue("Matiere supprimé");
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
        Matiere m = (Matiere) matList.getSelectionModel().getSelectedItem();
        Prof temp = new Prof();
        
        if (m != null){
            id = m.getId();
            intitule.setText(m.getMat());
            volume.setText(m.getVolume()+"");
            for (Prof u : temp.read()){
                if(u.getId() == m.getIdprof()){
                    prof.setValue(u);
                }
            }
            radio = true;
        }
    }
    
    public void refresh (){
        matList.setItems(null);
        matList.layout();
        Matiere m = new Matiere();
        
        matList.setItems(m.read());
        
        matList.setOnMouseClicked(event -> getSelected());
    }
}
