/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edt.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Princy Andriam
 */
public class Seance {
        private SimpleIntegerProperty idseance = new SimpleIntegerProperty();
        private SimpleIntegerProperty idm = new SimpleIntegerProperty();
        private SimpleIntegerProperty ids = new SimpleIntegerProperty();
        private SimpleIntegerProperty idc = new SimpleIntegerProperty();
        private SimpleStringProperty date = new SimpleStringProperty();
        private SimpleIntegerProperty heureDebut = new SimpleIntegerProperty();
        private SimpleIntegerProperty heureFin = new SimpleIntegerProperty();
        
        public Seance(){
            
        }
        
        public int getIdSeance(){
            return this.idm.get();
        }
        
        public int getIdm(){
            return this.idm.get();
        }
        
        public int getIds(){
            return this.ids.get();
        }
        public int getIdc(){
            return this.idc.get();
        }
        public String getDate(){
            return this.date.get();
        }
        public int gethd(){
            return this.heureDebut.get();
        }
        public int gethf(){
            return this.heureFin.get();
        }
        
        public SimpleIntegerProperty idProperty(){
            return this.idseance;
        }
        
        public SimpleIntegerProperty idmProperty(){
            return this.idm;
        }
        
        public SimpleIntegerProperty idsProperty(){
            return this.ids;
        }
                
        public SimpleIntegerProperty idcProperty(){
            return this.idc;
        }
        public SimpleStringProperty dateProperty(){
            return this.date;
        }
        public SimpleIntegerProperty hdebutProperty(){
            return this.heureDebut;
        }
        public SimpleIntegerProperty hfinProperty(){
            return this.heureFin;
        }
        public void setId(int n){
            this.idseance.set(n);
        }        
        public void setIdm(int n){
            this.idm.set(n);
        }
        public void setIds(int n){
            this.ids.set(n);
        }
        public void setIdc(int i){
            this.idc.set(i);
        }
        public void setHdebut(int i){
            this.heureDebut.set(i);
        }
        public void setHfin(int i){
            this.heureFin.set(i);
        }
        public void setDate(String i){
            this.date.set(i);
        }
        
        public void insert(Seance s){
            Connection connexion;   
            try {
                connexion = Connexion.connect();
                                        
                String sql = "INSERT INTO seance (idm, ids, idc, date, heure_debut, heure_fin) VALUES (?,?,?,?,?,?)";
                PreparedStatement prepare = connexion.prepareStatement(sql);

                prepare.setInt(1, s.getIdm());
                prepare.setInt(2, s.getIds());
                prepare.setInt(3, s.getIdc());
                prepare.setString(4, s.getDate());
                prepare.setInt(5, s.gethd());
                prepare.setInt(6, s.gethf());
                
                prepare.executeUpdate();

                prepare.close();
                connexion.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
