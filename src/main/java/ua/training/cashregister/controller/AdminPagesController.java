package ua.training.cashregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.cashregister.entity.Product;
import ua.training.cashregister.service.CheckService;
import ua.training.cashregister.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminPagesController {
    private ProductService productService;
    private CheckService checkService;

    @Autowired
    public AdminPagesController(ProductService productService,
                                CheckService checkService) {
        this.productService = productService;
        this.checkService = checkService;
    }

    @GetMapping({"/", "/index"})
    public String getIndexPage() {
        return "admin/index";
    }

    @GetMapping("/products")
    public String getProductsPage(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/products";
    }

    @GetMapping("/new-product")
    public String getNewProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "admin/new-product";
    }

    @PostMapping("/new-product")
    public String addNewProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                Model model) {
        //TODO: check product
        productService.saveNewProduct(product);
        return "admin/new-product";
    }

    @GetMapping({"/statistics", "/statistics/index"})
    public String getStatisticsIndexPage(Model model) {
        model.addAttribute("checks", checkService.getReportX());
        return "admin/statistics/index";
    }

    @PostMapping(value = "/statistics/index", params = {"delete"})
    public String deleteCheck(@Valid @ModelAttribute("id") Long id, Model model) {
        model.addAttribute("checks", checkService.deleteCheckByIdAndGetReportX(id));
        return "admin/statistics/index";
    }

    @PostMapping(value = "/statistics/index", params = {"open"})
    public String openCheck(@Valid @ModelAttribute("id") Long id, Model model) {
        model.addAttribute("check", checkService.findCheckById(id));
        return "admin/statistics/check";
    }

    @GetMapping("/statistics/report/x")
    public String getStatisticsReportPageX(Model model) {
        model.addAttribute("checks", checkService.getReportX());
        return "admin/statistics/report";
    }

    @GetMapping("/statistics/report/z")
    public String getStatisticsReportPageZ(Model model) {
        model.addAttribute("checks", checkService.getReportZ());
        return "admin/statistics/report";
    }

    @GetMapping("/statistics/check")
    public String getStatisticsCheckPage() {
        return "admin/statistics/check";
    }
}
