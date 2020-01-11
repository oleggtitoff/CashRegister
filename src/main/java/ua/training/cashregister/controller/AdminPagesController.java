package ua.training.cashregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.cashregister.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminPagesController {
    private ProductService productService;

    @Autowired
    public AdminPagesController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/", "/index"})
    public String getIndexPage() {
        return "admin/index";
    }

    @GetMapping("/products")
    public String getProductsPage(Model model) {
        model.addAttribute("products", productService.getAllProducts().getProducts());
        return "admin/products";
    }

    @GetMapping("/new-product")
    public String getNewProductPage() {
        return "admin/new-product";
    }

    @GetMapping({"/statistics", "/statistics/index"})
    public String getStatisticsIndexPage() {
        return "admin/statistics/index";
    }

    @GetMapping("/statistics/report")
    public String getStatisticsReportPage() {
        return "admin/statistics/report";
    }

    @GetMapping("/statistics/check")
    public String getStatisticsCheckPage() {
        return "admin/statistics/check";
    }
}
