package ua.training.cashregister.service;

import ua.training.cashregister.entity.Product;

import java.util.List;

public interface ProductService {
    void saveNewProduct(Product product);
    List<Product> getAllProducts();
    Product findProductByIdOrName(String searchBy);
    Product findProductById(Long id);
    Product findProductByName(String name);
}
