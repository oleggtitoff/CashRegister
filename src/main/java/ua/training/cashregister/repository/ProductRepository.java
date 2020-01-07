package ua.training.cashregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.training.cashregister.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
