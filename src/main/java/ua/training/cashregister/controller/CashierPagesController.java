package ua.training.cashregister.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cashier")
public class CashierPagesController {
    @GetMapping({"/", "/index"})
    public String getIndexPage() {
        return "cashier/index";
    }

    @GetMapping("/new-check")
    public String getNewCheckPage() {
        return "cashier/new-check";
    }
}
