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

    public Check findCheckById(Long id) {
        //TODO: throw exception if empty
        return checkRepository.findById(id).get();
    }

    public List<CheckWithCostDTO> deleteCheckByIdAndGetReportX(Long id) {
        deleteCheckById(id);
        return getReportX();
    }

    private void deleteCheckById(Long id) {
        try {
            checkRepository.deleteById(id);
        } catch (Exception ex) {
            //TODO: exception to endpoint
            log.warn("{Cannot delete!}");
        }
    }

    //TODO: refactor
    //TODO: test method
    public List<CheckWithCostDTO> getReportZ() {
        List<CheckWithCostDTO> checksWithCost = getReportX();

        List<Check> checks = checksWithCost.stream()
                .map(check -> {
                    check.getCheck().setIsInFiscalMemory(true);
                    return check.getCheck();
                }).collect(Collectors.toList());

        checkRepository.saveAll(checks);
        return checksWithCost;
    }

    //TODO: test method
    public List<CheckWithCostDTO> getReportX() {
        return getAllNotFiscalMemoryChecks().stream()
                .map(this::getCheckWithCostDTO)
                .collect(Collectors.toList());
    }

    //TODO: test method
    private CheckWithCostDTO getCheckWithCostDTO(Check check) {
        return CheckWithCostDTO.builder()
                .check(check)
                .cost(countTotalCost(check))
                .build();
    }

    //TODO: test method
    private BigDecimal countTotalCost(Check check) {
        return check.getCheckEntries()
                .stream()
                .map(this::countEntryCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //TODO: refactor
    //TODO: test method
    private BigDecimal countEntryCost(CheckEntry checkEntry) {
        if (checkEntry.getProduct().getProductType() == ProductType.PIECE) {
            return checkEntry.getProduct().getPrice().multiply(new BigDecimal(checkEntry.getQuantity()));
        } else {
            return checkEntry.getProduct().getPrice().multiply(checkEntry.getMass());
        }
    }

    //TODO: test method
    private List<Check> getAllNotFiscalMemoryChecks() {
        return checkRepository.findAllByIsInFiscalMemoryFalse();
    }

    //TODO: alterCheck
}
