/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginregister.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import libraryapplicationproject.DataBase;
import loginregister.model.Books;
import loginregister.model.Mybooks;
import loginregister.model.UserInfo;

/**
 * FXML Controller class
 *
 * @author mertonat
 */
public class FXMLHomePageController implements Initializable {
    DataBase db = new DataBase();
    UserInfo info=db.getuserinfo();
    //Store book table 
    @FXML private TableView<Books> tableviewstore= new TableView<Books>();
    
    @FXML private TableColumn bookNameColumn;
    @FXML private TableColumn authorColumn;
    @FXML private TableColumn priceColumn;
    @FXML private TableColumn discountColumn;
    @FXML private TableColumn MybookNameColumn;
    @FXML private TableColumn MyauthorColumn;
    @FXML private TableColumn MypriceColumn;
    @FXML private TableColumn MydiscountColumn;
    //Mybooks table
    @FXML private TableView<Mybooks> tableviewmybooks= new TableView<Mybooks>();
    //panes
    @FXML private Pane StorePane;
    @FXML private Pane MyBooksPane;
    @FXML private Pane ProfilePane;
    @FXML private Pane SellPane;
    @FXML private Pane Pointpane;
    @FXML private Pane BuyBookPane;
    //textes
    @FXML private Label usernametext;
    @FXML private Label userEmailtext;
    @FXML private Label userLastnametext;
    @FXML private Label userCredittext;
    
    @FXML private Text BooknameB;
    @FXML private Text BookPriceB;
    @FXML private Text BookAuthorB;
    @FXML private Text BookDiscountB;
    @FXML private Text BookDiscountedPriceB;
    //field textes
    @FXML private TextField booknamefield;
    @FXML private TextField authorfield;
    @FXML private TextField pricefield;
    @FXML private TextField discountfield;
    @FXML private TextField paymentnamefield;
    @FXML private TextField paymentlastnamefield;
    @FXML private TextField paymentaddressfield;
    @FXML private TextField paymentcardnumberfield;
    @FXML private TextField paymentcvcfield;
    @FXML private TextField paymentamountfield;
    
    
    @FXML private void buyBookButton(){
        
        if(info.getBookpoints()>=tableviewstore.getSelectionModel().getSelectedItem().getPrice()){
            
            db.MyAdd(tableviewstore.getSelectionModel().getSelectedItem().getBookName(), tableviewstore.getSelectionModel().getSelectedItem().getAuthor(), tableviewstore.getSelectionModel().getSelectedItem().getPrice(), tableviewstore.getSelectionModel().getSelectedItem().getDiscount());
            tableviewmybooks.setItems(db.MyBookList());
            userCredittext.setText(String.valueOf(info.getBookpoints()-Float.valueOf(BookDiscountedPriceB.getText())));
            db.bookPaymentUserPointUpdate(Float.valueOf(BookDiscountedPriceB.getText()));
            BuyBookPane.setVisible(false);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Not enough Bookpoints");
            alert.setHeaderText("Please buy Bookpoints");
            alert.showAndWait();
        }
    }
    
    @FXML private void BuycancelButton(){
        BuyBookPane.setVisible(false);
    }
    @FXML private void AddPointsButton(){
      Pointpane.setVisible(true);
    }
    @FXML private void BuyPointButton(){
        if(paymentnamefield.getText().length()!=0&&
           paymentlastnamefield.getText().length()!=0&&
           paymentaddressfield.getText().length()!=0&&
           paymentcardnumberfield.getText().length()==16&&
           paymentcvcfield.getText().length()==3&&
           Integer.valueOf(paymentamountfield.getText())>0
           ){
            
            db.Payment(paymentnamefield.getText(),paymentlastnamefield.getText(),paymentaddressfield.getText(),paymentcardnumberfield.getText(),Integer.valueOf(paymentcvcfield.getText()) ,Integer.valueOf(paymentamountfield.getText()));
            Pointpane.setVisible(false);
            userCredittext.setText(String.valueOf(info.getBookpoints()));
            paymentaddressfield.clear();
            paymentamountfield.clear();
            paymentcardnumberfield.clear();
            paymentcvcfield.clear();
            paymentlastnamefield.clear();
            paymentnamefield.clear();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wrong value setted");
            alert.setHeaderText("Please check all the fieldes!!!");
            alert.showAndWait();
        }
      
    }
    @FXML private void CancelPointsButton(){
       Pointpane.setVisible(false);
    }
    @FXML private void SellBooksButton(){
     SellPane.setVisible(true);
    }
    
    @FXML private void SellButtonok(){
        if(Float.valueOf(pricefield.getText())>=0&&pricefield.getText().length()!=0&&Integer.valueOf(discountfield.getText())>0&&discountfield.getText().length()!=0&&booknamefield.getText().length()!=0&&authorfield.getText().length()!=0){
            db.Add(booknamefield.getText(),authorfield.getText(), Float.valueOf(pricefield.getText()),Integer.valueOf(discountfield.getText()));
            tableviewstore.setItems(db.BookList());
            SellPane.setVisible(false);
            booknamefield.clear();
            authorfield.clear();
            discountfield.clear();
            pricefield.clear();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("field is Empty");
            alert.setHeaderText("Please fill all the fieldes!!!");
            alert.showAndWait();
        }
    }
    @FXML private void SellButtoncancel(){
    SellPane.setVisible(false);
    }
    @FXML private void StoreButton(ActionEvent store){
    ProfilePane.setVisible(false);
    MyBooksPane.setVisible(false);
    StorePane.setVisible(true);
    }
    @FXML private void MyBooksButton(ActionEvent myBook){ 
    ProfilePane.setVisible(false);
    StorePane.setVisible(false);
    MyBooksPane.setVisible(true);    
    }
    @FXML private void ProfileButton(ActionEvent profile){
    
    StorePane.setVisible(false);
    MyBooksPane.setVisible(false);
    ProfilePane.setVisible(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //store
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("bookName"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Books,String>("author"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Books,Float>("price"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<Books,Float>("discount"));
        tableviewstore.setItems(db.BookList());
        tableviewstore.setOnMousePressed(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent event) {
                 BuyBookPane.setVisible(true);
                 BookAuthorB.setText(tableviewstore.getSelectionModel().getSelectedItem().getAuthor());
                 BooknameB.setText(tableviewstore.getSelectionModel().getSelectedItem().getBookName());
                 BookPriceB.setText(String.valueOf(tableviewstore.getSelectionModel().getSelectedItem().getPrice()));
                 BookDiscountB.setText(String.valueOf(tableviewstore.getSelectionModel().getSelectedItem().getDiscount()));
                 BookDiscountedPriceB.setText(String.valueOf(tableviewstore.getSelectionModel().getSelectedItem().getPrice()-((tableviewstore.getSelectionModel().getSelectedItem().getPrice()*tableviewstore.getSelectionModel().getSelectedItem().getDiscount())/100)));
             }
         });  
        //mybook
        MybookNameColumn.setCellValueFactory(new PropertyValueFactory<Mybooks, String>("bookName"));
        MyauthorColumn.setCellValueFactory(new PropertyValueFactory<Mybooks,String>("author"));
        MypriceColumn.setCellValueFactory(new PropertyValueFactory<Mybooks,Float>("price"));
        MydiscountColumn.setCellValueFactory(new PropertyValueFactory<Mybooks,Float>("discount"));
        
        tableviewmybooks.setItems(db.MyBookList());
        
        //setting Userinfo
        usernametext.setText(info.getName());
        userLastnametext.setText(info.getLname());
        userEmailtext.setText(info.getEmail());
        userCredittext.setText(String.valueOf(info.getBookpoints()));
         /**
        paymentcardnumberfield.textProperty().addListener(new ChangeListener<String>() {
         
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("\\d*")) {
                 int value = Integer.parseInt(newValue);
                } else {
                paymentcardnumberfield.setText(oldValue);
                }
            }
        });**/
    }
                  
}
