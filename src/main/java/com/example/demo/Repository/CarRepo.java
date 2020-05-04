package com.example.demo.Repository;

import com.example.demo.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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

    public Car create(Car c) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String modelQuery = "INSERT INTO model\n" +
                "(model_group, brand, model_details, fuel_type)\n" +
                "VALUES(?, ?, ?, ?)";
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(modelQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(c.getModel_group()));
            ps.setString(2, c.getBrand());
            ps.setString(3, c.getModel_details());
            ps.setString(4, c.getFuel_type());
            return ps;
        }, keyHolder);

        System.out.println("Model ID:" + keyHolder.getKey());

        String carQuery = "INSERT INTO car\n" +
                "(reg_nr, reg_date, odometer, model_id)\n" +
                "VALUES(?, ?, ?, ?)";
        template.update(carQuery, c.getReg_nr(), c.getReg_date(), c.getOdometer(), keyHolder.getKey());
        return null;
    }

    public Car findCarById(int id){
        String sql = "SELECT * FROM car WHERE car_id = ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        Car c = template.queryForObject(sql, rowMapper, id);
        return c;
    }

    public boolean deleteCar(int id){
        String sql = "DELETE FROM car WHERE car_id = ?";
        return template.update(sql, id) < 0;
    }

    public Car updateCar(int id, Car c){
        String sql = "UPDATE car SET odometer = ? WHERE car_id = ?";
        template.update(sql, c.getOdometer(), c.getCar_id());
        return null;
    }
}
