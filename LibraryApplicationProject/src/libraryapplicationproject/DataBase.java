/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryapplicationproject;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import loginregister.model.UserInfo;
import javafx.collections.ObservableList;
import loginregister.model.Books;
import loginregister.model.Mybooks;
/**
 *
 * @author mertonat
 */
public class DataBase {
  
  boolean isLogin;
  boolean isRegister;
  static UserInfo userinfo = new UserInfo();
    public static Connection getConnection() throws SQLException{
            String URL="jdbc:oracle:thin:@codd.inf.unideb.hu:1521:ora12c";
            String ID ="U_cmtljf";
            String PW ="hey";
            
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase2 [Walid on WALID]");
        
            return connection;
    }
    
    public  Boolean isMailExists(String Email) throws SQLException{
       boolean isMailExits = false;
       String checker=null;
       
       /* try{
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        }catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }System.out.println("Connecting..");   
       */
       
        Connection connection=null;
        Statement stmt=null;
        try{
            connection= getConnection();
            stmt=connection.createStatement();
            ResultSet rstmt = stmt.executeQuery("SELECT * FROM useres where email like'"+Email+"'");
               while(rstmt.next()){
                   checker=rstmt.getString("Email");
                }
                  if(checker==null||!checker.equals(Email)){
                        isMailExits = true;
                   }else isMailExits = false;   
               System.out.println(isMailExits);
               stmt.close();
               connection.close();
         }
        
        catch (java.sql.SQLException e) {
              if (e.getErrorCode() == 942) { 
                stmt.executeUpdate("CREATE TABLE USERES(userID NUMBER(6) not null PRIMARY KEY, name VARCHAR2(100) not null,lastname VARCHAR2(100) not null, Email VARCHAR2(100) not null,password VARCHAR2(100) not null,BOOKPOINTS FLOAT(10))");
                }
            }
        System.out.println("mail Check...");
        
        return isMailExits;    
    }
    public void Register(String name,String lastname,String Email,String password){
      int userIdcounter = 1000;
      userinfo.setEmail("Naziribrar@gmail.com");
     /* try{
        DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        }catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }*/
       
       Connection connection = null; 
       Statement statement = null;
       PreparedStatement pstmt = null;
       try {
            connection=getConnection();
           try{
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE USERES(userID NUMBER(6) not null PRIMARY KEY, name VARCHAR2(100) not null,lastname VARCHAR2(100) not null, Email VARCHAR2(100) not null,password VARCHAR2(100) not null,BOOKPOINTS FLOAT(10))");
                
           }catch (java.sql.SQLException e) {
              if (e.getErrorCode() != 955) { 
                   throw e;
                }
              System.out.println("Table already exists...");
            } 
           
           ResultSet chekingMail= statement.executeQuery("SELECT * FROM useres where email like'"+Email+"'");
           while (chekingMail.next()) {
             String checkmail =chekingMail.getString("EMAIL");
               System.out.println(checkmail);
           }
           chekingMail.close();
           
           ResultSet rs=statement.executeQuery("SELECT * FROM useres WHERE userid = (SELECT MAX(userid) FROM useres)");
           while(rs.next()){
           userIdcounter =rs.getInt("userID");
           userIdcounter=userIdcounter+1;
           }
           
           rs.close();
           statement.close();
           
           pstmt = connection.prepareStatement("INSERT INTO USERES (USERID,NAME,LASTNAME,EMAIL,PASSWORD,BOOKPOINTS) VALUES (?, ?, ?, ?,?,?)");
           pstmt.setInt(1, userIdcounter);
           pstmt.setString(2, name);
           pstmt.setString(3, lastname);
           pstmt.setString(4, Email);
           pstmt.setString(5, password);
           pstmt.setFloat(6, Float.valueOf(userinfo.getBookpoints()));
           pstmt.executeUpdate();
           System.out.println("Inserted into DB USERES table!");
           
           connection.close();
           statement.close();
        }catch (java.sql.SQLException e) {
        System.out.print(e.getErrorCode() + " ");
        System.out.print(e.getMessage() + " ");
        System.out.println(e.getSQLState());
        }
   }
    public void Login(String email,String password){

      /*  try{
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        }catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }System.out.println("Connecting..toLogin");   */
      
       Connection connection = null; 
       Statement stmt = null;
      
       try {
            connection=getConnection();
            System.out.println("Connected");
          stmt =connection.createStatement();
          ResultSet rstmt = stmt.executeQuery("SELECT * FROM useres where email like'"+email+"' and password like '"+password+"'");
          while(rstmt.next()){
               System.out.println("Gettinlist");
                if(rstmt.getString("EMAIL").contains(email)&&rstmt.getString("Password").contains(password)){      
                    isLogin = true;
                    userinfo.setUserId(rstmt.getInt(1));
                    userinfo.setName(rstmt.getString(2));
                    userinfo.setLname(rstmt.getString(3));
                    userinfo.setEmail(rstmt.getString(4));
                    userinfo.setPassword(rstmt.getString(5));
                    userinfo.setBookpoints(rstmt.getFloat(6));
                }else{
                    isLogin = false;
                }
            }
            stmt.close();
            connection.close();
        }catch (java.sql.SQLException e) {
        System.out.print(e.getErrorCode() + " ");
        System.out.print(e.getMessage() + " ");
        System.out.println(e.getSQLState());
        
        }
       System.out.println("got the list");
   }
    public boolean isSucceeded() {
        return isLogin;
    }
    public static UserInfo getuserinfo(){
        return userinfo;
    }
    public void Payment(String name,String lastname,String adress,String cardnumber,int cvc,int amount){
      /*try{
        DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        }catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }*/
       Connection connection = null; 
       Statement statement = null;
       PreparedStatement pstmt = null;
       try {
            connection=getConnection();
           try{
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE PAYMENT(userID NUMBER(6) not null, name VARCHAR2(100) not null,lastname VARCHAR2(100) not null, ADDRES VARCHAR2(100) not null,CARDNUMBER VARCHAR2(16) not null,CVC NUMBER(3),bookpoint FLOAT(10))");
                 System.out.println("Creatting table");
           }catch (java.sql.SQLException e) {
              if (e.getErrorCode() != 955) { 
                   throw e;
                }
              System.out.println("Table already exists...");
            }
            pstmt = connection.prepareStatement("INSERT INTO PAYMENT(userID,NAME,LASTNAME,ADDRES,CARDNUMBER,CVC,bookpoint) VALUES(?,?,?,?,?,?,?)");
            pstmt.setInt(1,userinfo.getUserId());
            pstmt.setString(2, name);
            pstmt.setString(3,lastname);
            pstmt.setString(4, adress);
            pstmt.setString(5,cardnumber);
            pstmt.setInt(6, cvc);
            pstmt.setFloat(7,amount);
            pstmt.executeUpdate(); 
            pstmt.close();
            float a = userinfo.getBookpoints();
            userinfo.setBookpoints(a+amount);
            System.out.println(userinfo.getUserId());
            System.out.println(userinfo.getBookpoints());
            pstmt = connection.prepareStatement("UPDATE USERES SET BOOKPOINTS=? where userID =?");
            System.out.println("adding to ");
            pstmt.setFloat(1,a);
            pstmt.setInt(2,userinfo.getUserId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
         Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    ObservableList<Books> booklist =FXCollections.observableArrayList();
    public  ObservableList<Books> BookList(){
       /* try {
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        Connection connection = null;
        Statement stmt =null;
        ResultSet pstmt = null;
        try{
            System.out.println("Connected books list");
            connection = getConnection();
            stmt = connection.createStatement();
            pstmt = stmt.executeQuery("select * from STORE");
            while(pstmt.next())
            {
                pstmt.getInt(1);
                booklist.add(new Books(pstmt.getString(2), pstmt.getString(3), pstmt.getFloat(4),pstmt.getInt(5)));
            }
            
            stmt.close();
            pstmt.close();
            connection.close();
        }
        catch(java.sql.SQLException e){
        System.out.print(e.getErrorCode() + " ");
        System.out.print(e.getMessage() + " ");
        System.out.println(e.getSQLState());
        }
        System.out.println(booklist);
        return booklist; 
    }
    ObservableList<Mybooks> mybooksList=FXCollections.observableArrayList();
    public  ObservableList<Mybooks> MyBookList(){
     /*   try {
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        Connection connection = null;
        Statement stmt =null;
        ResultSet pstmt = null;
        try{
            System.out.println("Connected books list");
            connection = getConnection();
            stmt = connection.createStatement();
            pstmt = stmt.executeQuery("select * from Mybooks");
            while(pstmt.next())
            {
                pstmt.getInt(1);
                mybooksList.add(new Mybooks(pstmt.getString(2), pstmt.getString(3), pstmt.getFloat(4),pstmt.getInt(5)));
            }
            
            stmt.close();
            pstmt.close();
            connection.close();
        }
        catch(java.sql.SQLException e){
        System.out.print(e.getErrorCode() + " ");
        System.out.print(e.getMessage() + " ");
        System.out.println(e.getSQLState());
        }
        System.out.println(mybooksList);
        return mybooksList; 
    }
    public void Add(String bookname,String aut,float price,int discount){
       /* try{
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        }catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        System.out.println("adding book Connecting..");
       Connection connection = null; 
       Statement statement = null;
       PreparedStatement pstmt = null;
        try {
            connection=getConnection();
           try{
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE STORE(userID NUMBER(6) not null,bookname VARCHAR2(100) not null,author VARCHAR2(100), price FLOAT(10),discount NUMBER(10))");
           }catch (java.sql.SQLException e) {
              if (e.getErrorCode() != 955) { 
                   throw e;
                }
              System.out.println("Store Table already exists...");
            }
          pstmt = connection.prepareStatement("INSERT INTO STORE(userID,bookname,author,price,discount) VALUES (?,?,?,?,?)");
          pstmt.setInt(1, userinfo.getUserId());
          pstmt.setString(2,bookname);
          pstmt.setString(3,aut);
          pstmt.setFloat(4, price);
          pstmt.setInt(5, discount);
          pstmt.executeUpdate();
       }catch (SQLException ex) {
          Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    public void MyAdd(String bookname,String aut,float price,int discount){
       /* try{
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        }catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        System.out.println("adding mybook Connecting..");
       Connection connection = null; 
       Statement statement = null;
       PreparedStatement pstmt = null;
        try {
            connection=getConnection();
           try{
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE Mybooks(userID NUMBER(6) not null,bookname VARCHAR2(100) not null,author VARCHAR2(100), price FLOAT(10),discount NUMBER(10))");
           }catch (java.sql.SQLException e) {
              if (e.getErrorCode() != 955) { 
                   throw e;
                }
              System.out.println("Store Table already exists...");
            }
          pstmt = connection.prepareStatement("INSERT INTO Mybooks(userID,bookname,author,price,discount) VALUES (?,?,?,?,?)");
          pstmt.setInt(1, userinfo.getUserId());
          pstmt.setString(2,bookname);
          pstmt.setString(3,aut);
          pstmt.setFloat(4, price);
          pstmt.setInt(5, discount);
          pstmt.executeUpdate();
       }catch (SQLException ex) {
          Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    public void bookPaymentUserPointUpdate(float amount){
     /*   try{
        DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
        }catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }*/
     
       Connection connection = null; 
       Statement statement = null;
       PreparedStatement pstmt = null;
       try {
            connection=getConnection();
            pstmt = connection.prepareStatement("UPDATE USERES SET BOOKPOINTS=? where userID =?");
            System.out.println("adding to ");
            pstmt.setFloat(1,amount);
            pstmt.setInt(2,userinfo.getUserId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
         Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
}

