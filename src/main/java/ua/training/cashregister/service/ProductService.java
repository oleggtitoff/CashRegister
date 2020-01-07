package ua.training.cashregister.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.cashregister.entity.Product;
import ua.training.cashregister.repository.ProductRepository;

@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveNewProduct(Product product) {
        try {
            productRepository.save(product);
        } catch (Exception ex) {
            //TODO: exception to endpoint
            log.warn("{Product with this name is already exists!}");
        }
    }
}