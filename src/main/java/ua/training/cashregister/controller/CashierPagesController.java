package ua.training.cashregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.cashregister.exceptions.ProductNotFoundException;
import ua.training.cashregister.dto.CheckEntriesDTO;
import ua.training.cashregister.dto.CheckEntryCreationDTO;
import ua.training.cashregister.entity.CheckEntry;
import ua.training.cashregister.entity.Product;
import ua.training.cashregister.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/cashier")
public class CashierPagesController {
    ProductService productService;
    CheckEntriesDTO checkEntriesDTO = new CheckEntriesDTO();

    @Autowired
    public CashierPagesController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/", "/index"})
    public String getIndexPage() {
        return "cashier/index";
    }

    @GetMapping("/new-check")
    public String getNewCheckPage(Model model) {
        model.addAttribute("entry", new CheckEntryCreationDTO());
        model.addAttribute("entries", checkEntriesDTO);
        return "cashier/new-check";
    }

    @RequestMapping(value = "/new-check", params = {"add"})
    public String addEntry(@Valid @ModelAttribute("entry") CheckEntryCreationDTO checkEntryCreationDTO,
                           BindingResult bindingResult,
                           Model model) {
        try {
            Product product = productService
                    .findProductByIdOrName(checkEntryCreationDTO.getSearchBy());

        } catch (ProductNotFoundException ex) {
            //TODO: message to user
        }

        checkEntriesDTO.addCheckEntry(new CheckEntry());
        model.addAttribute("entries", checkEntriesDTO);
        return "cashier/new-check";
    }

    @RequestMapping(value = "/new-check", params = {"submit"})
    public String saveCheck(@ModelAttribute("entries") CheckEntriesDTO checkEntriesDTO) {
        //TODO: save check

        checkEntriesDTO.getCheckEntries().clear();
        return "cashier/index";
    }
}
