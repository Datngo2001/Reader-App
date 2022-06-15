package hcmute.edu.vn.reader.model;

import java.util.List;

public class User {
    private float id;
    private String username;
    private String email;
    private String password;
    private String fname;
    private String lname;
    private String createdAt;
    private String updatedAt;
    private String isActive;
    private int[] permissionCodes;
    private List<BorrowRegister> borrowRegister;
    private List<BorrowBill> borrowBills;

    public List<BorrowRegister> getBorrowRegister() {
        return borrowRegister;
    }

    public void setBorrowRegister(List<BorrowRegister> borrowRegister) {
        this.borrowRegister = borrowRegister;
    }

    public List<BorrowBill> getBorrowBills() {
        return borrowBills;
    }

    public void setBorrowBills(List<BorrowBill> borrowBills) {
        this.borrowBills = borrowBills;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public int[] getPermissionCodes() {
        return permissionCodes;
    }

    public void setPermissionCodes(int[] permissionCodes) {
        this.permissionCodes = permissionCodes;
    }
}
