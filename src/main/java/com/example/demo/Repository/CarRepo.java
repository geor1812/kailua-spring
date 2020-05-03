package com.example.demo.Repository;

import com.example.demo.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepo {
    @Autowired
    JdbcTemplate template;

    public List<Car> readAll() {
        String sqlQuery = "SELECT car.car_id, car.reg_nr, car.reg_date, car.odometer, car.model_id, model.model_group, model.brand, model.model_details, model.fuel_type \n" +
                "FROM car\n" +
                "INNER JOIN model ON car.model_id = model.model_id\n" +
                "ORDER BY car_id";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.query(sqlQuery, rowMapper);
    }
}
