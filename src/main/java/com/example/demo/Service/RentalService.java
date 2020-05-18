package com.example.demo.Service;

import com.example.demo.Model.Rental;
import com.example.demo.Model.Rental;
import com.example.demo.Repository.RentalRepo;
import com.example.demo.Repository.RentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
    @Autowired
    RentalRepo rentalRepo;

    public List<Rental> readAll() {
        return rentalRepo.readAll();
    }

    public Rental create(Rental c) {
        return rentalRepo.create(c);
    }

    public Rental findRentalById(int id) {
        return rentalRepo.findRentalById(id);
    }

    public boolean deleteRental(int id) {
        return rentalRepo.deleteRental(id);
    }

    public Rental updateRental(int id, Rental c) {
        return rentalRepo.updateRental(id, c);
    }
}
