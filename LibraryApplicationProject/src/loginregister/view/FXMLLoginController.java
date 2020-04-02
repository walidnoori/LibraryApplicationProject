/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginregister.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import libraryapplicationproject.DataBase;
import loginregister.model.UserInfo;

/**
 * FXML Controller class
 *
 * @author mertonat
 */
public class FXMLLoginController implements Initializable {
    
    
    
    @FXML
    private void registerButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/loginregister/view/FXMLRegister.fxml"));
        Scene register_scene = new Scene(root);
       
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Register");
        stage.setScene(register_scene);
        stage.show();
    }
    @FXML 
    private TextField userEmailField;
    @FXML 
    private PasswordField userPasswordField;
    
    @FXML
    private Label loginFailed;
   
    @FXML
    private void loginbutton(ActionEvent event) throws IOException{
    DataBase db = new DataBase();
    UserInfo userinfo = new  UserInfo();
    
    
    userinfo.setEmail(userEmailField.getText().toString());
    userinfo.setPassword(userPasswordField.getText().toString());
    System.out.println(userinfo.getEmail()+" "+userinfo.getPassword());
    
    userinfo.setEmail("Naziribrar@gmail.com");
    userinfo.setPassword("Hey");
    db.Login(userinfo.getEmail(),userinfo.getPassword());
        
        if(db.isSucceeded()){
             Parent root = FXMLLoader.load(getClass().getResource("/loginregister/view/FXMLHomePage.fxml"));
             Scene sc = new Scene(root);
             Stage Stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
             Stage.setTitle("HomePage");
             Stage.setScene(sc);
             Stage.show();
        }else{
            loginFailed.setText("Login failed!!Your username or password was entered incorrectly");
        }
    }

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
