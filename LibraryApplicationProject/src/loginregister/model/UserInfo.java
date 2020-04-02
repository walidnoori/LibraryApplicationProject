/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginregister.model;

/**
 *
 * @author mertonat
 */
public class UserInfo {

    /**
     * @return the bookpoints
     */
    public float getBookpoints() {
        return bookpoints;
    }

    /**
     * @param bookpoints the bookpoints to set
     */
    public void setBookpoints(float bookpoints) {
        this.bookpoints = bookpoints;
    }

  
    private String name;
    private String lname;
    private String email;
    private String password;
    private int userId;
    private float bookpoints;
    
    
    public UserInfo(String name,String lastname, String email, String password, int userId,float bookpoints) {
        this.name = name;
        this.lname =lastname;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.bookpoints=bookpoints;
    }

    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" + "name=" + name + ", lname=" + lname + ", email=" + email + ", password=" + password + ", userId=" + userId + ", bookpoints=" + bookpoints + '}';
    }

   

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
  /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }    
}
