package ua.training.cashregister.service;

import ua.training.cashregister.dto.CheckEntriesDTO;
import ua.training.cashregister.entity.Check;

import java.util.List;

public interface CheckService {
    void saveNewCheck(CheckEntriesDTO checkEntriesDTO);
    List<Check> getAllNotFiscalMemoryChecks();
}
