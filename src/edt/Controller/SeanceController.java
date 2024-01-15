/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edt.Controller;

import edt.Model.Classe;
import edt.Model.Connexion;
import edt.Model.Matiere;
import edt.Model.Prof;
import edt.Model.Salle;
import edt.Model.Seance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class SeanceController implements Initializable {

    private int id;
    private boolean radio = false;

    @FXML
    private ComboBox<String> jour;

    @FXML
    private ComboBox<Matiere> idm;
    @FXML
    private ComboBox<Salle> ids;
    @FXML
    private ComboBox<Classe> idc;
    @FXML
    private TextField date;
    @FXML
    private ComboBox<String> heureDebut;
    @FXML
    private ComboBox<String> heureFin;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

           comboSeance();
           comboClasse();
           comboMatiere();
           comboSalle();
    }    
    
    @FXML
    private void insertBtn(ActionEvent event){
        if (radio == false){
        String date = jour.getSelectionModel().getSelectedItem().toString();
        int m = idm.getSelectionModel().getSelectedItem().getId();
        int c = idc.getSelectionModel().getSelectedItem().getId();
        int s = ids.getSelectionModel().getSelectedItem().getId();
        int hd = (Character.getNumericValue(heureDebut.getSelectionModel().getSelectedItem().toString().charAt(0))) * 10 
                + (Character.getNumericValue(heureDebut.getSelectionModel().getSelectedItem().toString().charAt(1)));
        int hf = (Character.getNumericValue(heureFin.getSelectionModel().getSelectedItem().toString().charAt(0))) * 10 
                + (Character.getNumericValue(heureFin.getSelectionModel().getSelectedItem().toString().charAt(1)));
        Seance seanc = new Seance();
        seanc.setIdm(m);
        seanc.setIdc(c);
        seanc.setIds(s);
        seanc.setDate(date);
        seanc.setHdebut(hd);
        seanc.setHfin(hf);
        seanc.insert(seanc);
        idm.getSelectionModel().clearSelection();
        idc.getSelectionModel().clearSelection();
        ids.getSelectionModel().clearSelection();
        heureDebut.getSelectionModel().clearSelection();
        heureFin.getSelectionModel().clearSelection();
        jour.getSelectionModel().clearSelection();
        }
    }

    public void comboMatiere(){
        Matiere temp = new Matiere();
        idm.setItems(temp.read());
        idm.setConverter(new StringConverter<Matiere>() {
            public String toString(Matiere m){
                return m != null ? m.getMat():"";
            }

            @Override
            public Matiere fromString(String string) {
                return null;
            }
        });
    }
    public void comboSeance(){
        ObservableList<String> items = FXCollections.observableArrayList("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi","Samedi");
        this.jour.setItems(items);
        jour.setConverter(new StringConverter<String>() {
            public String toString(String s){
                return s != null ? s:"";
            }

            @Override
            public String fromString(String string) {
                return null;
            }
        });
        
        ObservableList<String> heureD = FXCollections.observableArrayList("08:00 - 09:00","09:00 - 10:00","10:00 - 11:00",
                "11:00 - 12:00","12:00 - 13:00","13:00 - 14:00",       
                "14:00 - 15:00","15:00 - 16:00","16:00 - 17:00"
        ); 
        this.heureDebut.setItems(heureD);
        heureDebut.setConverter(new StringConverter<String>() {
            public String toString(String s){
                return s != null ? s:"";
            }

            @Override
            public String fromString(String string) {
                return null;
            }
        });
        
        ObservableList<String> heureF = FXCollections.observableArrayList("08:00","09:00","10:00","11:00","12:00","13:00",       
                "14:00","15:00","16:00"
        ); 
        this.heureFin.setItems(heureF);
        heureFin.setConverter(new StringConverter<String>() {
            public String toString(String s){
                return s != null ? s:"";
            }

            @Override
            public String fromString(String string) {
                return null;
            }
        });
       
    }
    public void comboSalle(){
        Salle temp = new Salle();
        ids.setItems(temp.read());
        ids.setConverter(new StringConverter<Salle>() {
            public String toString(Salle s){
                return s != null ? s.getSalle():"";
            }

            @Override
            public Salle fromString(String string) {
                return null;
            }
        });
    }
    public void comboClasse(){
        Classe temp = new Classe();
        idc.setItems(temp.read());
        idc.setConverter(new StringConverter<Classe>() {
            public String toString(Classe c){
                return c != null ? c.getClasse():"";
            }

            @Override
            public Classe fromString(String string) {
                return null;
            }
        });
    }
}
