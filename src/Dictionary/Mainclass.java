/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Rith Record
 */
public class Mainclass extends Application {
    public static Stage Mainstage;
    @Override
        public void start(Stage stage) throws Exception {
             // Create stage
    stage.setTitle("សួរស្ឌី"); // SetTitle
    FXMLLoader l = new FXMLLoader();
        
       l.setLocation(this.getClass().getResource("InputData/InputDataFXML.fxml"));
        
        AnchorPane Mainpane = l.load();
        
        Scene scene = new Scene(Mainpane);
        
        stage.setScene(scene);
    
        stage.show(); // How to show stage
    
    //ShowMain();
    } 
//        public void ShowMain() throws IOException
//    {
//        
//    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
