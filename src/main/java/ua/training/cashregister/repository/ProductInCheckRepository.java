package ua.training.cashregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.training.cashregister.entity.Check;
import ua.training.cashregister.entity.ProductInCheck;

import java.util.List;

@Repository
public interface ProductInCheckRepository
        extends JpaRepository<ProductInCheck, Long> {
    List<ProductInCheck> findByCheck(Check check);
}
