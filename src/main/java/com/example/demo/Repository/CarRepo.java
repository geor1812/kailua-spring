package com.example.demo.Repository;

import com.example.demo.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepo {
    @Autowired
    JdbcTemplate template;

    public List<Car> readAll() {
        return null;
    }
}
