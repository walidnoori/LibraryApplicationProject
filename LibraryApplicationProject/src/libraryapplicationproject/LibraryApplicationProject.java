/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapplicationproject;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author mertonat
 */

public class LibraryApplicationProject extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/loginregister/view/FXMLLogin.fxml"));
        Scene sc =new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.setScene(sc);
        primaryStage.show();
    }
  public static void main(String[] args) {
       
        launch(args);
    }
}
