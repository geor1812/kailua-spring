package com.example.demo.Service;

import com.example.demo.Model.Car;
import com.example.demo.Model.Customer;
import com.example.demo.Repository.CarRepo;
import com.example.demo.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;
    public List<Customer> readAll() {
        return customerRepo.readAll();
    }

    public Customer create(Customer c) {
        return customerRepo.create(c);
    }

    public Customer findCustomerById(int id){
        return customerRepo.findCustomerById(id);
    }

    public boolean deleteCustomer(int id){
        return customerRepo.deleteCustomer(id);
    }

    public Customer updateCustomer(int id, Customer c){
        return customerRepo.updateCustomer(id, c);
    }
}
