package ua.training.cashregister.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuestController {
    @GetMapping({"/", "/index"})
    public String getStartPage() {
        return "index";
    }

    @GetMapping("access-denied")
    public String getAccessDeniedPage() {
        return "access-denied";
    }
}
