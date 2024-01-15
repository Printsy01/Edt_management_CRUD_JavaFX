/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edt.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Princy Andriam
 */
public class Matiere {
        private SimpleIntegerProperty idm = new SimpleIntegerProperty();
        private SimpleStringProperty intitule = new SimpleStringProperty();
        private SimpleIntegerProperty volume = new SimpleIntegerProperty();
        private SimpleIntegerProperty idprof = new SimpleIntegerProperty();
        
        public Matiere(){
            
        }
        
        public int getId(){
            return this.idm.get();
        }
        
        public String getMat(){
            return this.intitule.get();
        }
        
        public int getVolume(){
            return this.volume.get();
        }
       
        public int getIdprof(){
            return this.idprof.get();
        }
        
        public SimpleIntegerProperty idProperty(){
            return this.idm;
        }
        
        public SimpleStringProperty MatProperty(){
            return this.intitule;
        }
        
        public SimpleIntegerProperty VolumeProperty(){
            return this.volume;
        }
                
        public SimpleIntegerProperty idprofProperty(){
            return this.idprof;
        }
        public void setId(int n){
            this.idm.set(n);
        }        
        public void setMat(String n){
            this.intitule.set(n);
        }
        public void setVolume(int v){
            this.volume.set(v);
        }
        public void setIdprof(int i){
            this.idprof.set(i);
        }
        
        public void insert(Matiere m){
            Connection connexion;   
            try {
                connexion = Connexion.connect();
                                        
                String sql = "INSERT INTO matiere (intitule, volume, idprof) VALUES (?,?,?)";
                PreparedStatement prepare = connexion.prepareStatement(sql);

                prepare.setString(1, m.getMat());
                prepare.setInt(2, m.getVolume());
                prepare.setInt(3, m.getIdprof());
                
                prepare.executeUpdate();

                prepare.close();
                connexion.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public ObservableList<Matiere> read(){
            ObservableList<Matiere> matList = FXCollections.observableArrayList();
            
            try {
                Connection connection = Connexion.connect();
                
                String sql = "SELECT * FROM matiere";
                PreparedStatement prepare = connection.prepareStatement(sql);
                
                ResultSet result = prepare.executeQuery();
                while (result.next()){
                    Matiere temp = new Matiere();
                    temp.setId(result.getInt("idm"));
                    temp.setMat(result.getString("intitule"));
                    temp.setVolume(result.getInt("volume"));
                    temp.setIdprof(result.getInt("idprof"));
                    matList.add(temp);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return matList;
        }     
        
        public void update(Matiere m){
            Connection connexion;   
            try {
                connexion = Connexion.connect();
                                        
                String sql = "UPDATE matiere SET intitule = ?, volume = ?, idprof = ? WHERE idm = ?";
                PreparedStatement prepare = connexion.prepareStatement(sql);

                prepare.setString(1, m.getMat());
                prepare.setInt(2, m.getVolume());
                prepare.setInt(3, m.getIdprof());
                prepare.setInt(4, m.getId());

                prepare.executeUpdate();

                prepare.close();
                connexion.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void delete(Matiere m){
            Connection connexion;   
            try {
                connexion = Connexion.connect();
                                        
                String sql = "DELETE FROM matiere WHERE idm = ?";
                PreparedStatement prepare = connexion.prepareStatement(sql);

                prepare.setInt(1, m.getId());

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
