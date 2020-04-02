/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginregister.view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import libraryapplicationproject.DataBase;
import loginregister.model.UserInfo;

/*
 * FXML Controller class
 *
 * @author mertonat
 */
public class FXMLRegisterController implements Initializable {

    
    
    @FXML
    private void backToLogin(ActionEvent event) throws IOException{
     Parent root =FXMLLoader.load(getClass().getResource("/loginregister/view/FXMLLogin.fxml"));
     Scene Login_scene = new Scene(root);
     Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
     stage.setScene(Login_scene);
     stage.show();
    }
    @FXML
    private TextField userFirstName;
    @FXML
    private TextField userLastName;
    @FXML
    private TextField userEmail;
    @FXML
    private PasswordField userPassword;   
    
public class FXMLOKController {

    @FXML
    private Label okLable;

    @FXML
    void okLable(MouseEvent event) {

    }

}
    
    @FXML
    private void RegisterButton(ActionEvent event) throws SQLException, IOException{
       DataBase dat = new DataBase();
       UserInfo info = new UserInfo();
       String name = null,lastname=null,Email=userEmail.getText(),password=null;
       if(userFirstName.getText().isEmpty()){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Name field is Empty");
            alert1.setHeaderText("Please fill name field!!!");
            alert1.showAndWait();
                }else if(userLastName.getText().isEmpty()){
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Last name field is Empty");
                    alert2.setHeaderText("Please fill last name field!!!");
                    alert2.showAndWait();
                }else if(userEmail.getText().isEmpty()){
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("Email field is Empty");
                    alert3.setHeaderText("Please fill email field!!!");
                    alert3.showAndWait();
                }else if(userPassword.getText().isEmpty()){
                    Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                    alert4.setTitle("Password field is Empty");
                    alert4.setHeaderText("Please fill password field!!!");
                    alert4.showAndWait();
                }else if(dat.isMailExists(Email)){
                    name=userFirstName.getText().toString();
                    lastname=userLastName.getText().toString();
                    Email=userEmail.getText().toString();
                    info.setEmail(Email);
                    password=userPassword.getText().toString();
                    dat.Register(name, lastname, Email, password);
                    Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                    alert4.setTitle("You have been Register");
                    alert4.setHeaderText("You have been Register please login");
                    alert4.showAndWait();
                    backToLogin(event);
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
