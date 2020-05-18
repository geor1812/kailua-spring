package com.example.demo.Repository;

import com.example.demo.Model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentalRepo {
    @Autowired
    JdbcTemplate template;

    public List<Rental> readAll() {
        String sqlQuery = "SELECT * FROM rental ORDER BY rental_id";
        RowMapper<Rental> rowMapper = new BeanPropertyRowMapper<>(Rental.class);
        return template.query(sqlQuery, rowMapper);
    }

    public Rental create(Rental c) {
        return null;
    }

    public Rental findRentalById(int id) {
        return null;
    }

    public boolean deleteRental(int id) {
        return false;
    }

    public Rental updateRental(int id, Rental c) {
        return null;
    }
}
