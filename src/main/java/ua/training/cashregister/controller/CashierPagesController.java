package ua.training.cashregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.cashregister.entity.ProductType;
import ua.training.cashregister.exceptions.ProductNotFoundException;
import ua.training.cashregister.dto.CheckEntriesDTO;
import ua.training.cashregister.dto.CheckEntryCreationDTO;
import ua.training.cashregister.entity.CheckEntry;
import ua.training.cashregister.entity.Product;
import ua.training.cashregister.service.CheckService;
import ua.training.cashregister.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/cashier")
public class CashierPagesController {
    ProductService productService;
    CheckService checkService;
    CheckEntriesDTO checkEntriesDTO = new CheckEntriesDTO();

    @Autowired
    public CashierPagesController(ProductService productService,
                                  CheckService checkService) {
        this.productService = productService;
        this.checkService = checkService;
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

    @PostMapping(value = "/new-check", params = {"add"})
    public String addEntry(@Valid @ModelAttribute("entry") CheckEntryCreationDTO checkEntryCreationDTO,
                           BindingResult bindingResult,
                           Model model) {
        try {
            Product product = productService
                    .findProductByIdOrName(checkEntryCreationDTO.getSearchBy());
            CheckEntry checkEntry = CheckEntry.builder()
                    .product(product).build();

            if (product.getProductType().equals(ProductType.PIECE)) {
                checkEntry.setQuantity(checkEntryCreationDTO.getNumber().toBigInteger());
            } else {
                checkEntry.setMass(checkEntryCreationDTO.getNumber());
            }

            checkEntriesDTO.addCheckEntry(checkEntry);
        } catch (ProductNotFoundException ex) {
            //TODO: message to user
        }

        return getNewCheckPage(model);
    }

    @PostMapping(value = "/new-check", params = {"submit"})
    public String saveCheck(Model model) {
        //TODO: if check is empty
        checkService.saveNewCheck(checkEntriesDTO);
        checkEntriesDTO.clear();
        return getNewCheckPage(model);
    }
}
