/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edt.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Princy Andriam
 */
public class Connexion {
    public static Connection connect() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/java";
        String user = "root";
        String mdp = "";
        return DriverManager.getConnection(url, user, mdp);
    }
}
