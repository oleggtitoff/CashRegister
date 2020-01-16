package ua.training.cashregister.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.cashregister.dto.CheckEntriesDTO;
import ua.training.cashregister.dto.CheckWithCostDTO;
import ua.training.cashregister.entity.Check;
import ua.training.cashregister.entity.CheckEntry;
import ua.training.cashregister.entity.ProductType;
import ua.training.cashregister.repository.CheckRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CheckServiceImpl implements CheckService {
    private final CheckRepository checkRepository;

    @Autowired
    public CheckServiceImpl(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }

    //TODO: test method
    public void saveNewCheck(CheckEntriesDTO checkEntriesDTO) {
        Check check = Check.builder()
                .created(LocalDateTime.now())
                .checkEntries(checkEntriesDTO.getCheckEntries())
                .build();

        check.getCheckEntries()
                .forEach(e -> e.setCheck(check));

        try {
            checkRepository.save(check);
        } catch (Exception ex) {
            //TODO: exception to endpoint
            log.warn("{Cannot save!}");
        }
    }

    //TODO: test method
    public List<CheckWithCostDTO> getReportX() {
        return getAllNotFiscalMemoryChecks().stream()
                .map(check -> {
                    CheckWithCostDTO obj = new CheckWithCostDTO();
                    obj.setCheck(check);
                    obj.setCost(countTotalCost(check));
                    return obj;
                }).collect(Collectors.toList());
    }

    //TODO: test method
    public BigDecimal countTotalCost(Check check) {
        return check.getCheckEntries()
                .stream()
                .map(this::countEntryCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //TODO: test method
    public BigDecimal countEntryCost(CheckEntry checkEntry) {
        if (checkEntry.getProduct().getProductType() == ProductType.PIECE) {
            return checkEntry.getProduct().getPrice().multiply(new BigDecimal(checkEntry.getQuantity()));
        } else {
            return checkEntry.getProduct().getPrice().multiply(checkEntry.getMass());
        }
    }

    //TODO: test method
    public List<Check> getAllNotFiscalMemoryChecks() {
        return checkRepository.findAllByIsInFiscalMemoryFalse();
    }

    //TODO: alterCheck
    //TODO: deleteCheck
}
