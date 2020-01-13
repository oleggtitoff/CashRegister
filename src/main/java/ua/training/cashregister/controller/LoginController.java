package ua.training.cashregister.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.cashregister.entity.Role;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String getLogin(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return "login";
    }

    @RequestMapping("/default-success")
    public String getSuccessPage() {
        if (SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().contains(Role.ADMIN)) {
            return "redirect:/admin/index";
        }
        return "redirect:/cashier/index";
    }
}
