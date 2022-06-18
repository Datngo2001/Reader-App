package hcmute.edu.vn.reader.model;

import java.util.List;

public class User {
    private float id; // user's id
    private String username; // user name
    private String email; // user email
    private String password; // user password
    private String fname; // user first name
    private String lname; // user last name
    private String createdAt; // time create user account
    private String updatedAt;  // time update user account
    private String isActive; // status of user account is active or not
    private int[] permissionCodes; // permission of user
    private List<BorrowRegister> borrowRegister; // list of book user has registered
    private List<BorrowBill> borrowBills; // list of book user has returned

    public List<BorrowRegister> getBorrowRegister() {
        return borrowRegister;
    } // return list of book user register

    public void setBorrowRegister(List<BorrowRegister> borrowRegister) {
        this.borrowRegister = borrowRegister;
    } // set list of book user register

    public List<BorrowBill> getBorrowBills() {
        return borrowBills;
    } // return list of book user return

    public void setBorrowBills(List<BorrowBill> borrowBills) {
        this.borrowBills = borrowBills;
    } //set list of book user return

    public float getId() {
        return id;
    } // return user id

    public void setId(float id) {
        this.id = id;
    } // set user id

    public String getUsername() {
        return username;
    } // return user name

    public void setUsername(String username) {
        this.username = username;
    } // set user name

    public String getEmail() {
        return email;
    } // return user email

    public void setEmail(String email) {
        this.email = email;
    } // set user email

    public String getPassword() {
        return password;
    } // return user password

    public void setPassword(String password) {
        this.password = password;
    } //set user password

    public String getFname() {
        return fname;
    } // return user first name

    public void setFname(String fname) {
        this.fname = fname;
    } // set user first name

    public String getLname() {
        return lname;
    } // return user last name

    public void setLname(String lname) {
        this.lname = lname;
    } // set user last name

    public String getCreatedAt() {
        return createdAt;
    } // return time create account

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    } //set time create account

    public String getUpdatedAt() {
        return updatedAt;
    } // return time update account

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    } // set time update account

    public String getIsActive() {
        return isActive;
    } // return status of account is active or not

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    } // set status of account is active or not

    public int[] getPermissionCodes() {
        return permissionCodes;
    } // return the permission of user can do in the app

    public void setPermissionCodes(int[] permissionCodes) {
        this.permissionCodes = permissionCodes;
    } // set the permission of user in the app
}
