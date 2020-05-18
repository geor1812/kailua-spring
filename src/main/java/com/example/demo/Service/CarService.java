package com.example.demo.Service;

import com.example.demo.Model.Car;
import com.example.demo.Repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    CarRepo carRepo;
    private int workingID;

    public int getWorkingID() {
        return workingID;
    }

    public void setWorkingID(int workingID) {
        this.workingID = workingID;
    }

    public List<Car> readAll() {
        return carRepo.readAll();
    }

    public Car create(Car c) {
        return carRepo.create(c);
    }

    public Car findCarById(int id){
        return carRepo.findCarById(id);
    }

    public boolean deleteCar(int id){
        return carRepo.deleteCar(id);
    }

    public Car updateCar(int id, Car c){
        return carRepo.updateCar(id, c);
    }
}
