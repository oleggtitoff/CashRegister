package ua.training.cashregister.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    @GetMapping({"/", "/index"})
    public String getStartPage() {
        return "index";
    }
}
