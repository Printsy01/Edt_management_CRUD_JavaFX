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
public class Prof {
        private SimpleIntegerProperty id = new SimpleIntegerProperty();
        private SimpleStringProperty nom = new SimpleStringProperty();
        private SimpleStringProperty contact = new SimpleStringProperty();
        
        public Prof(String n, String c){
            setNom(n);
            setContact(c);
        }
        public Prof(){
            
        }
        
        public int getId(){
            return this.id.get();
        }
        
        public String getNom(){
            return this.nom.get();
        }
        
        public String getContact(){
            return this.contact.get();
        }
        
        public SimpleIntegerProperty idProperty(){
            return this.id;
        }
        
        public SimpleStringProperty contactProperty(){
            return this.contact;
        }
        
        public SimpleStringProperty nomProperty(){
            return this.nom;
        }

        public void setId(int n){
            this.id.set(n);
        }        
        public void setNom(String n){
            this.nom.set(n);
        }
        public void setContact(String c){
            this.contact.set(c);
        }
        
        public void insert(Prof p){
            Connection connexion;   
            try {
                connexion = Connexion.connect();
                                        
                String sql = "INSERT INTO prof (nom, contact) VALUES (?,?)";
                PreparedStatement prepare = connexion.prepareStatement(sql);

                prepare.setString(1, p.getNom());
                prepare.setString(2, p.getContact());

                prepare.executeUpdate();

                prepare.close();
                connexion.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public ObservableList<Prof> read(){
            ObservableList<Prof> profList = FXCollections.observableArrayList();
            
            try {
                Connection connection = Connexion.connect();
                
                String sql = "SELECT * FROM prof";
                PreparedStatement prepare = connection.prepareStatement(sql);
                
                ResultSet result = prepare.executeQuery();
                while (result.next()){
                    Prof temp = new Prof();
                    temp.setId(result.getInt("idprof"));
                    temp.setNom(result.getString("nom"));
                    temp.setContact(result.getString("contact"));
                    profList.add(temp);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return profList;
        }     
        
        public void update(Prof p){
            Connection connexion;   
            try {
                connexion = Connexion.connect();
                                        
                String sql = "UPDATE prof SET nom = ?, contact = ? WHERE idprof = ?";
                PreparedStatement prepare = connexion.prepareStatement(sql);

                prepare.setString(1, p.getNom());
                prepare.setString(2, p.getContact());
                prepare.setInt(3, p.getId());

                prepare.executeUpdate();

                prepare.close();
                connexion.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void delete(Prof p){
            Connection connexion;   
            try {
                connexion = Connexion.connect();
                                        
                String sql = "DELETE FROM prof WHERE idprof = ?";
                PreparedStatement prepare = connexion.prepareStatement(sql);

                prepare.setInt(1, p.getId());

                prepare.executeUpdate();

                prepare.close();
                connexion.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        
        public String toString(){
            return nom.get();
        }
}
