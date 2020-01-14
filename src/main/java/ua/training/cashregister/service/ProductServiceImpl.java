package ua.training.cashregister.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.cashregister.ProductNotFoundException;
import ua.training.cashregister.entity.Product;
import ua.training.cashregister.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveNewProduct(Product product) {
        try {
            productRepository.save(product);
        } catch (Exception ex) {
            //TODO: exception to endpoint
            log.warn("{Product is already exists!}");
        }
    }

    public List<Product> getAllProducts() {
        //TODO: checking for an empty products list
        return productRepository.findAll();
    }

    public Product findProductByIdOrName(String searchBy) {
        long id = -1L;

        try {
            id = Long.parseLong(searchBy);
        } catch (Exception ex) {
            //TODO
        }

        Optional<Product> found = productRepository.findById(id);

        return found.orElseGet(() -> productRepository.findByName(searchBy)
                .orElseThrow(() -> new ProductNotFoundException(searchBy)));
    }

    public Product findProductById(Long id) {
        //TODO: throw exception if empty
        return productRepository.findById(id).get();
    }

    public Product findProductByName(String name) {
        //TODO: throw exception if empty
        return productRepository.findByName(name).get();
    }
}
