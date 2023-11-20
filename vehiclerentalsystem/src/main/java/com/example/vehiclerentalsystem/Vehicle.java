package com.example.vehiclerentalsystem;

import java.util.ArrayList;

public abstract class Vehicle {

    ArrayList<Vehicle> vehiclelist=new ArrayList<>();
    private String Name;
    private String Model;

    public Vehicle(String name, String model) {
        Name = name;
        Model = model;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public abstract void Add();
    public abstract void Return();

}
