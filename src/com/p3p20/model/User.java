/* le nom de package ou on a créé notre classe*/
package com.p3p20.model;
 
/* Bibliothèque pour qu’on puisse utiliser le type de données Date*/
import java.util.Date;
 
/**
* Class User
*/
public class User {
 
    private int userid;
    private String firstName;
    private String lastName;
    private Date dob;
    private String email;
 
    /** Les getters /setters pour les attributs 
     *  de cette classe
     */
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    /* Methode qui retourne une phrase 
     * qui contient les infos d’un utilisateur
     */
    public String toString() {
        return "User [userid=" + userid + ", firstName=" + firstName
                + ", lastName=" + lastName + ", dob=" + dob + ", email="
                + email + "]";
    }    
}
