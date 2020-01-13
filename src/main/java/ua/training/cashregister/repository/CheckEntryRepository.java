package ua.training.cashregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.training.cashregister.entity.Check;
import ua.training.cashregister.entity.CheckEntry;

import java.util.List;

@Repository
public interface CheckEntryRepository
        extends JpaRepository<CheckEntry, Long> {
    List<CheckEntry> findByCheck(Check check);
}
