package com.example.yuppi;

public class Contacts {
    private String firstName;
    private String lastName;
    private String cellPhoneNumber;
    private String homePhoneNumber;
    private String email;
    private String notes;

    public Contacts(){

    }

    public Contacts(String firstName, String lastName, String cellPhoneNumber, String homePhoneNumber, String email, String notes){
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellPhoneNumber = cellPhoneNumber;
        this.homePhoneNumber = homePhoneNumber;
        this.email = email;
        this.notes = notes;
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

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
