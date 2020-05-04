package com.example.demo.Controller;

import com.example.demo.Model.Car;
import com.example.demo.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CarService carService;

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
    public String customerMenu(){
        return "home/customerMenu";
    }

    @GetMapping("/contractsMenu")
    public String contractsMenu(){
        return "home/contractsMenu";
    }

    @GetMapping("/carCreate")
    public String carCreate() {
        return "home/carCreate";
    }

    @PostMapping("/carCreate")
    public String carCreate(@ModelAttribute Car car) {
        carService.create(car);
        return "redirect:/carMenu";
    }

    @GetMapping("/viewDetails/{car_id}")
    public String viewDetails(@PathVariable("car_id") int car_id, Model model){
        model.addAttribute("car", carService.findCarById(car_id));
        return "home/viewDetails";
    }

    @GetMapping("/delete/{car_id}")
    public String delete(@PathVariable("car_id") int car_id){
        boolean deleted = carService.deleteCar(car_id);
        return "redirect:/carMenu";
    }

    @GetMapping("/updateCar/{car_id}")
    public String updateCar(@PathVariable("car_id") int car_id, Model model){
        model.addAttribute("car", carService.findCarById(car_id));
        return "home/updateCar";

    }

    @PostMapping("/updateCar")
    public String updateCar(@ModelAttribute Car car){
        //How to get the car ID from the car in @GetMapping?
        carService.updateCar(car.getCar_id(), car);
        return "redirect:/carMenu";
    }
}
