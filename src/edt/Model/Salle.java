/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edt.Model;

import com.mysql.cj.conf.IntegerProperty;
import com.mysql.cj.conf.StringProperty;
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
public class Salle {
        private SimpleIntegerProperty capacite = new SimpleIntegerProperty();
        private SimpleIntegerProperty ids = new SimpleIntegerProperty();
        private SimpleStringProperty salle = new SimpleStringProperty();
        
        public Salle(String s, int c){
            setSalle(s);
            setCapacite(c);
        }
        public Salle(){
            
        }
        
        public int getId(){
            return this.ids.get();
        }
        
        public int getCapacite(){
            return this.capacite.get();
        }
        
        public String getSalle(){
            return this.salle.get();
        }
        
        public SimpleIntegerProperty idProperty(){
            return this.ids;
        }
        
        public SimpleIntegerProperty capaciteProperty(){
            return this.capacite;
        }
        
        public SimpleStringProperty salleProperty(){
            return this.salle;
        }
        
        public void setId(int i){
            this.ids.set(i);
        }        
        public void setCapacite(int n){
            this.capacite.set(n);
        }
        public void setSalle(String s){
            this.salle.set(s);
        }
        
        public void insert(Salle s){
            Connection connexion;   
            try {
                connexion = Connexion.connect();
                                        
                String sql = "INSERT INTO salle (salle, capacite) VALUES (?,?)";
                PreparedStatement prepare = connexion.prepareStatement(sql);

                prepare.setString(1, s.getSalle());
                prepare.setInt(2, s.getCapacite());

                prepare.executeUpdate();

                prepare.close();
                connexion.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public ObservableList<Salle> read(){
            ObservableList<Salle> salleList = FXCollections.observableArrayList();
            
            try {
                Connection connection = Connexion.connect();
                
                String sql = "SELECT * FROM salle";
                PreparedStatement prepare = connection.prepareStatement(sql);
                
                ResultSet result = prepare.executeQuery();
                while (result.next()){
                    Salle temp = new Salle();
                    temp.setId(result.getInt("ids"));
                    temp.setSalle(result.getString("salle"));
                    temp.setCapacite(result.getInt("capacite"));
                    salleList.add(temp);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return salleList;
        }     
        
        public void update(Salle s){
            Connection connexion;   
            try {
                connexion = Connexion.connect();
                                        
                String sql = "UPDATE salle SET salle = ?, capacite = ? WHERE ids = ?";
                PreparedStatement prepare = connexion.prepareStatement(sql);

                prepare.setString(1, s.getSalle());
                prepare.setInt(2, s.getCapacite());
                prepare.setInt(3, s.getId());

                prepare.executeUpdate();

                prepare.close();
                connexion.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void delete(Salle s){
            Connection connexion;   
            try {
                connexion = Connexion.connect();
                                        
                String sql = "DELETE FROM salle WHERE ids = ?";
                PreparedStatement prepare = connexion.prepareStatement(sql);

                prepare.setInt(1, s.getId());

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
