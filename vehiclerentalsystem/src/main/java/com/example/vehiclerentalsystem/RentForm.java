package com.example.vehiclerentalsystem;

import java.util.Date;

public class RentForm extends RentalSystem {

    private String ID;
    private String name;
    private Date date;

    private int phoneNumber;

    private String Address;

    public RentForm(String ID, String name, Date date, int phoneNumber, String address) {
        this.ID = ID;
        this.name = name;
        this.date = date;
        this.phoneNumber = phoneNumber;
        Address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
