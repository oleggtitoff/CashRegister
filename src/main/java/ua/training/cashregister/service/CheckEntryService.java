package ua.training.cashregister.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.cashregister.dto.CheckEntriesDTO;
import ua.training.cashregister.entity.Check;
import ua.training.cashregister.entity.CheckEntry;
import ua.training.cashregister.repository.CheckEntryRepository;

@Slf4j
@Service
public class CheckEntryService {
    private final CheckEntryRepository checkEntryRepository;

    @Autowired
    public CheckEntryService(CheckEntryRepository checkEntryRepository) {
        this.checkEntryRepository = checkEntryRepository;
    }

    public void saveNewProductInCheck(CheckEntry checkEntry) {
        try {
            checkEntryRepository.save(checkEntry);
        } catch (Exception ex) {
            //TODO: exception to endpoint
            log.warn("{ProductInCheck is already exists!}");
        }
    }

    public CheckEntriesDTO findProductsByCheck(Check check) {
        //TODO: checking for an empty list
        return new CheckEntriesDTO(checkEntryRepository.findByCheck(check));
    }

    public void deleteProductInCheck(CheckEntry checkEntry) {
        try {
            checkEntryRepository.delete(checkEntry);
        } catch (Exception ex) {
            //TODO: exception to endpoint
            log.warn("{ProductInCheck cannot be deleted!}");
        }
    }
}
