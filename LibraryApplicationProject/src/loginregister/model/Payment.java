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
public class Payment {
    private int UserId;
    private int Cardnumber;
    private String cvcnumber;
    private String Cardname;
    private String Lastname;
    private String address;
    
    /**
     * @return the UserId
     */
    public int getUserId() {
        return UserId;
    }

    /**
     * @param UserId the UserId to set
     */
    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    /**
     * @return the Cardnumber
     */
    public int getCardnumber() {
        return Cardnumber;
    }

    /**
     * @param Cardnumber the Cardnumber to set
     */
    public void setCardnumber(int Cardnumber) {
        this.Cardnumber = Cardnumber;
    }

    /**
     * @return the cvcnumber
     */
    public String getCvcnumber() {
        return cvcnumber;
    }

    /**
     * @param cvcnumber the cvcnumber to set
     */
    public void setCvcnumber(String cvcnumber) {
        this.cvcnumber = cvcnumber;
    }

    /**
     * @return the Cardname
     */
    public String getCardname() {
        return Cardname;
    }

    /**
     * @param Cardname the Cardname to set
     */
    public void setCardname(String Cardname) {
        this.Cardname = Cardname;
    }

    /**
     * @return the Lastname
     */
    public String getLastname() {
        return Lastname;
    }

    /**
     * @param Lastname the Lastname to set
     */
    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
