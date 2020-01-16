package ua.training.cashregister.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.cashregister.dto.CheckEntriesDTO;
import ua.training.cashregister.entity.Check;
import ua.training.cashregister.repository.CheckEntryRepository;
import ua.training.cashregister.repository.CheckRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
public class CheckServiceImpl implements CheckService {
    private final CheckRepository checkRepository;
    private final CheckEntryRepository checkEntryRepository;

    @Autowired
    public CheckServiceImpl(CheckRepository checkRepository,
                            CheckEntryRepository checkEntryRepository) {
        this.checkRepository = checkRepository;
        this.checkEntryRepository = checkEntryRepository;
    }

    //TODO: test method
    public void saveNewCheck(CheckEntriesDTO checkEntriesDTO) {
        Check check = Check.builder()
                .created(LocalDateTime.now())
                .build();

        checkEntriesDTO.getCheckEntries()
                .forEach(check::addCheckEntry);

        try {
            checkRepository.save(check);
            checkEntryRepository.saveAll(checkEntriesDTO.getCheckEntries());
        } catch (Exception ex) {
            //TODO: exception to endpoint
            log.warn("{Cannot save!}");
        }
    }

    //TODO: getAllNotFiscalMemoryChecks
    //TODO: deleteCheck
}
