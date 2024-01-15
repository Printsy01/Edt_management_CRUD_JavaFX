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
public class Classe {
        private SimpleIntegerProperty effectif = new SimpleIntegerProperty();
        private SimpleIntegerProperty idc = new SimpleIntegerProperty();
        private SimpleStringProperty classe = new SimpleStringProperty();
        
        public Classe(String c, int e){
            setClasse(c);
            setEffectif(e);
        }
        public Classe(){
            
        }
        
        public int getId(){
            return this.idc.get();
        }
        
        public int getEffectif(){
            return this.effectif.get();
        }
        
        public String getClasse(){
            return this.classe.get();
        }
        
        public SimpleIntegerProperty idProperty(){
            return this.idc;
        }
        
        public SimpleIntegerProperty effectifProperty(){
            return this.effectif;
        }
        
        public SimpleStringProperty classeProperty(){
            return this.classe;
        }
        
        public void setId(int i){
            this.idc.set(i);
        }        
        public void setEffectif(int n){
            this.effectif.set(n);
        }
        public void setClasse(String s){
            this.classe.set(s);
        }
        
        public void insert(Classe c){
            Connection connexion;   
            try {
                connexion = Connexion.connect();
                                        
                String sql = "INSERT INTO classe (classe, effectifs) VALUES (?,?)";
                PreparedStatement prepare = connexion.prepareStatement(sql);

                prepare.setString(1, c.getClasse());
                prepare.setInt(2, c.getEffectif());

                prepare.executeUpdate();

                prepare.close();
                connexion.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public ObservableList<Classe> read(){
            ObservableList<Classe> classeList = FXCollections.observableArrayList();
            
            try {
                Connection connection = Connexion.connect();
                
                String sql = "SELECT * FROM classe";
                PreparedStatement prepare = connection.prepareStatement(sql);
                
                ResultSet result = prepare.executeQuery();
                while (result.next()){
                    Classe temp = new Classe();
                    temp.setId(result.getInt("idc"));
                    temp.setClasse(result.getString("classe"));
                    temp.setEffectif(result.getInt("effectifs"));
                    classeList.add(temp);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return classeList;
        }     
        
        public void update(Classe c){
            Connection connexion;   
            try {
                connexion = Connexion.connect();
                                        
                String sql = "UPDATE classe SET classe = ?, effectifs = ? WHERE idc = ?";
                PreparedStatement prepare = connexion.prepareStatement(sql);

                prepare.setString(1, c.getClasse());
                prepare.setInt(2, c.getEffectif());
                prepare.setInt(3, c.getId());

                prepare.executeUpdate();

                prepare.close();
                connexion.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prof.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void delete(Classe c){
            Connection connexion;   
            try {
                connexion = Connexion.connect();
                                        
                String sql = "DELETE FROM classe WHERE idc = ?";
                PreparedStatement prepare = connexion.prepareStatement(sql);

                prepare.setInt(1, c.getId());

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
