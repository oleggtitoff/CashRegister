package ua.training.cashregister.service;

import ua.training.cashregister.dto.ProductsDTO;
import ua.training.cashregister.entity.Product;

public interface ProductService {
    void saveNewProduct(Product product);
    ProductsDTO getAllProducts();
    Product findProductById(Long id);
    Product findProductByName(String name);
}
