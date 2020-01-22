package ua.training.cashregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.training.cashregister.entity.Check;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckRepository extends JpaRepository<Check, Long> {
    Optional<Check> findById(Long id);
    List<Check> findAllByIsInFiscalMemoryFalse();
}
