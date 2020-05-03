package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "home/index";
    }


    @GetMapping("/carMenu")
    public String carMenu(Model model) {
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
}
