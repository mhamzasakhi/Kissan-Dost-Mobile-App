package com.my.kissandost;

public class User {
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNumber;
    private String password;
    private String Address;

    public User(String firstName,String lastName,String emailId,String phoneNumber,String password, String Address) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmailId(emailId);
        this.setPhoneNumber(phoneNumber);
        this.setPassword(password);
        this.setAddress(Address);
    }

    public User(){

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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
