package com.example.demo.Repository;

import com.example.demo.Model.Customer;
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
public class CustomerRepo {
    @Autowired
    JdbcTemplate template;

    public List<Customer> readAll() {
        String sqlQuery = "SELECT customer.customer_id, customer.first_name, customer.last_name, customer.phone_no, customer.email,\n" +
                "customer.licence_no, customer.address_id, licence.licence_date, address.address_details, address.zip,\n" +
                "address.city\n" +
                "FROM customer\n" +
                "INNER JOIN address ON customer.address_id = address.address_id\n" +
                "INNER JOIN licence ON customer.licence_no = licence.licence_no\n" +
                "ORDER BY customer_id";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return template.query(sqlQuery, rowMapper);
    }

    public Customer create(Customer c) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String modelQuery = "INSERT INTO address\n" +
                "(address_details, city, zip)\n" +
                "VALUES(?, ?, ?)";
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(modelQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(c.getAddress_details()));
            ps.setString(2, c.getCity());
            ps.setString(3, c.getZip());
            return ps;
        }, keyHolder);

        String licenceInsert = "INSERT INTO licence\n" +
                "(licence_no, licence_date)\n" +
                "VALUES(?, ?)";
        template.update(licenceInsert, c.getLicence_no(), c.getLicence_date());

        String insert = "INSERT INTO customer\n" +
                "(first_name, last_name, phone_no, email, licence_no, address_id)\n" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        template.update(insert, c.getFirst_name(), c.getLast_name(), c.getPhone_no(), c.getEmail(),
                c.getLicence_no(), keyHolder.getKey());
        return null;
    }

    public Customer findCustomerById(int id){
        String sqlQuery = "SELECT customer.customer_id, customer.first_name, customer.last_name, customer.phone_no, customer.email,\n" +
                "customer.licence_no, customer.address_id, licence.licence_date, address.address_details, address.zip,\n" +
                "address.city\n" +
                "FROM customer\n" +
                "INNER JOIN address ON customer.address_id = address.address_id\n" +
                "INNER JOIN licence ON customer.licence_no = licence.licence_no\n" +
                "WHERE customer_id = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer c = template.queryForObject(sqlQuery, rowMapper, id);
        return c;
    }

    public boolean deleteCustomer(int id){
        Customer c = findCustomerById(id);
        String sqlQuery1 = "DELETE FROM customer WHERE customer_id = ?";
        String sqlQuery2 = "DELETE FROM address WHERE address_id = ?";
        String sqlQuery3 = "DELETE FROM licence WHERE licence_no = ?";
        template.update(sqlQuery1, c.getCustomer_id());
        template.update(sqlQuery2, c.getAddress_id());
        template.update(sqlQuery3, c.getLicence_no());
        return true;
    }

    public Customer updateCustomer(int id, Customer c){
        return null;
    }
}
