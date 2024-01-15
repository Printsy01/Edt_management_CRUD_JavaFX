/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edt;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Princy Andriam
 */
public class AnotherScene {
    private static Stage mainStage;
    
    public static void setCurrentStage(Stage stage){
        mainStage = stage;
    }
    
    public static void anotherScene(String fxml){
        try{
            Parent root = FXMLLoader.load(AnotherScene.class.getResource(fxml));
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.setResizable(false);
            mainStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
