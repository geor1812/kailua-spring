package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car {
    //Fields
    @Id
    private int car_id;
    private String reg_nr;
    private String reg_date;
    private int odometer;
    private int model_id;
    private int model_group;
    private String brand;
    private String model_details;
    private String fuel_type;

    //Constructors
    //If it doesn't work add the extra constructor
    public Car() {}

    //Getters and Setters
    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getReg_nr() {
        return reg_nr;
    }

    public void setReg_nr(String reg_nr) {
        this.reg_nr = reg_nr;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public int getModel_group() {
        return model_group;
    }

    public void setModel_group(int model_group) {
        this.model_group = model_group;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel_details() {
        return model_details;
    }

    public void setModel_details(String model_details) {
        this.model_details = model_details;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    @Override
    public String toString() {
        return "Car{" +
                "car_id=" + car_id +
                ", reg_nr='" + reg_nr + '\'' +
                ", reg_date='" + reg_date + '\'' +
                ", odometer=" + odometer +
                ", model_id=" + model_id +
                ", model_group=" + model_group +
                ", brand='" + brand + '\'' +
                ", model_details='" + model_details + '\'' +
                ", fuel_type='" + fuel_type + '\'' +
                '}';
    }
}
