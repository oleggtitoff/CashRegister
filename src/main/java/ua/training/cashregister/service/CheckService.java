package ua.training.cashregister.service;

import ua.training.cashregister.dto.CheckEntriesDTO;

public interface CheckService {
    void saveNewCheck(CheckEntriesDTO checkEntriesDTO);
}
