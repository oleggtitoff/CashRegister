package ua.training.cashregister.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.cashregister.repository.CheckRepository;

@Slf4j
@Service
public class CheckServiceImpl implements CheckService {
    private final CheckRepository checkRepository;

    @Autowired
    public CheckServiceImpl(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }

    //TODO: addNewCheck
    //TODO: getAllNotFiscalMemoryChecks
    //TODO: deleteCheck
}
