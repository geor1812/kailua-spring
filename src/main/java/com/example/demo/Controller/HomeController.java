package com.example.demo.Controller;

import com.example.demo.Model.Car;
import com.example.demo.Model.Customer;
import com.example.demo.Model.Rental;
import com.example.demo.Service.CarService;
import com.example.demo.Service.CustomerService;
import com.example.demo.Service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CarService carService;
    @Autowired
    CustomerService customerService;
    @Autowired
    RentalService rentalService;

    @GetMapping("/")
    public String index() {
        return "home/index";
    }


    @GetMapping("/carMenu")
    public String carMenu(Model model) {
        List<Car> carList = carService.readAll();
        model.addAttribute("cars",carList);
        return "home/carMenu";
    }

    @GetMapping("/customerMenu")
    public String customerMenu(Model model){
        List<Customer> customerList =customerService.readAll();
        model.addAttribute("customers", customerList);
        return "home/customerMenu";
    }

    @GetMapping("/chooseCustomer")
    public String chooseCustomer(Model model){
        List<Customer> customerList =customerService.readAll();
        model.addAttribute("customers", customerList);
        return "home/chooseCustomer";
    }

    @GetMapping("/chooseCar/{customer_id}")
    public String chooseCar(@PathVariable("customer_id") int customer_id, Model model)
    {
        customerService.setWorkingID(customer_id);
        List<Car> carList = carService.readAll();
        model.addAttribute("cars", carList);
        return "home/chooseCar";
    }

    @GetMapping("/rentalMenu")
    public String rentalMenu(Model model){
        List<Rental> rentalList = rentalService.readAll();
        model.addAttribute("rentals", rentalList);
        return "home/rentalMenu";
    }

    @GetMapping("/carCreate")
    public String carCreate() {
        return "home/carCreate";
    }

    @GetMapping("/customerCreate")
    public String customerCreate() {
        return "home/customerCreate";
    }

    @GetMapping("/rentalCreate/{car_id}")
    public String rentalCreate(@PathVariable("car_id") int car_id) {
        carService.setWorkingID(car_id);
        return "home/rentalCreate";
    }

    @PostMapping("/carCreate")
    public String carCreate(@ModelAttribute Car car) {
        carService.create(car);
        return "redirect:/carMenu";
    }

    @PostMapping("/customerCreate")
    public String customerCreate(@ModelAttribute Customer customer) {
        customerService.create(customer);
        return "redirect:/customerMenu";
    }

    @PostMapping("/rentalCreate")
    public String rentalCreate(@ModelAttribute Rental rental) {
        rental.setCar_id(carService.getWorkingID());
        rental.setCustomer_id(customerService.getWorkingID());
        rentalService.create(rental);
        return "redirect:/rentalMenu";
    }

    @GetMapping("/viewDetails/{car_id}")
    public String viewDetails(@PathVariable("car_id") int car_id, Model model){
        model.addAttribute("car", carService.findCarById(car_id));
        return "home/viewDetails";
    }

    @GetMapping("/viewCustomerDetails/{customer_id}")
    public String viewCustomerDetails(@PathVariable("customer_id") int customer_id, Model model){
        model.addAttribute("customer", customerService.findCustomerById(customer_id));
        return "home/viewCustomerDetails";
    }

    @GetMapping("/viewRentalDetails/{rental_id}")
    public String viewRentalDetails(@PathVariable("rental_id") int rental_id, Model rentalModel,
                                    Model carModel, Model customerModel) {
        Rental r = rentalService.findRentalById(rental_id);
        rentalModel.addAttribute("rental", r);
        carModel.addAttribute("car", carService.findCarById(r.getCar_id()));
        customerModel.addAttribute("customer", customerService.findCustomerById(r.getCustomer_id()));
        return "home/viewRentalDetails";
    }

    @GetMapping("/delete/{car_id}")
    public String delete(@PathVariable("car_id") int car_id){
        boolean deleted = carService.deleteCar(car_id);
        return "redirect:/carMenu";
    }

    @GetMapping("/deleteCustomer/{customer_id}")
    public String deleteCustomer(@PathVariable("customer_id") int customer_id){
        boolean deleted = customerService.deleteCustomer(customer_id);
        return "redirect:/customerMenu";
    }

    @GetMapping("/deleteRental/{rental_id}")
    public String deleteRental(@PathVariable("rental_id") int rental_id) {
        boolean deleted = rentalService.deleteRental(rental_id);
        return "redirect:/rentalMenu";
    }

    @GetMapping("/updateCar/{car_id}")
    public String updateCar(@PathVariable("car_id") int car_id, Model model){
        carService.setWorkingID(car_id);
        model.addAttribute("car", carService.findCarById(car_id));
        return "home/updateCar";

    }

    @PostMapping("/updateCar")
    public String updateCar(@ModelAttribute Car car){
        int id = carService.getWorkingID();
        carService.updateCar(id, car);
        return "redirect:/carMenu";
    }

    @GetMapping("/updateCustomer/{customer_id}")
    public String updateCustomer(@PathVariable("customer_id") int customer_id, Model model){
        customerService.setWorkingID(customer_id);
        model.addAttribute("customer", customerService.findCustomerById(customer_id));
        return "home/updateCustomer";
    }

    @PostMapping("/updateCustomer")
    public String updateCustomer(@ModelAttribute Customer customer){
        int id = customerService.getWorkingID();
        customerService.updateCustomer(id, customer);
        return "redirect:/customerMenu";
    }

    @GetMapping("/updateRental/{rental_id}")
    public String updateRental(@PathVariable("rental_id") int rental_id, Model model) {
        rentalService.setWorkingID(rental_id);
        model.addAttribute("rental", rentalService.findRentalById(rental_id));
        return "home/updateRental";
    }

    @PostMapping("/updateRental")
    public String updateRental(@ModelAttribute Rental rental) {
        int id = rentalService.getWorkingID();
        rentalService.updateRental(id, rental);
        return "redirect:/rentalMenu";
    }
}
