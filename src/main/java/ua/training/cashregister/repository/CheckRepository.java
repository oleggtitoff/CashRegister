package ua.training.cashregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.training.cashregister.entity.Check;

@Repository
public interface CheckRepository extends JpaRepository<Check, Long> {
}
