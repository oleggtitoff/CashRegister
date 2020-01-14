package ua.training.cashregister.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.cashregister.dto.CheckEntriesDTO;
import ua.training.cashregister.dto.CheckEntryCreationDTO;
import ua.training.cashregister.entity.CheckEntry;

import javax.validation.Valid;

@Controller
@RequestMapping("/cashier")
public class CashierPagesController {
    CheckEntriesDTO checkEntriesDTO = new CheckEntriesDTO();

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

    @RequestMapping(value = "/new-check", params = {"validate"})
    public String validateProduct(@Valid @ModelAttribute("entry") CheckEntryCreationDTO checkEntryCreationDTO,
                                  BindingResult bindingResult,
                                  Model model) {
        //TODO: validate and add

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
