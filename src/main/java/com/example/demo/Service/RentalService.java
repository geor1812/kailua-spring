package com.example.demo.Service;

import com.example.demo.Model.Rental;
import com.example.demo.Repository.RentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
    @Autowired
    RentalRepo rentalRepo;
    private int workingID;

    public int getWorkingID() {
        return workingID;
    }

    public void setWorkingID(int workingID) {
        this.workingID = workingID;
    }

    public List<Rental> readAll() {
        return rentalRepo.readAll();
    }

    public Rental create(Rental r) {
        return rentalRepo.create(r);
    }

    public Rental findRentalById(int id) {
        return rentalRepo.findRentalById(id);
    }

    public boolean deleteRental(int id) {
        return rentalRepo.deleteRental(id);
    }

    public Rental updateRental(int id, Rental r) {
        return rentalRepo.updateRental(id, r);
    }
}
