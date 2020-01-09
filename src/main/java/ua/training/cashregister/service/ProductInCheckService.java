package ua.training.cashregister.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.cashregister.dto.ProductsInCheckDTO;
import ua.training.cashregister.entity.Check;
import ua.training.cashregister.entity.ProductInCheck;
import ua.training.cashregister.repository.ProductInCheckRepository;

@Slf4j
@Service
public class ProductInCheckService {
    private final ProductInCheckRepository productInCheckRepository;

    @Autowired
    public ProductInCheckService(ProductInCheckRepository productInCheckRepository) {
        this.productInCheckRepository = productInCheckRepository;
    }

    public void saveNewProductInCheck(ProductInCheck productInCheck) {
        try {
            productInCheckRepository.save(productInCheck);
        } catch (Exception ex) {
            //TODO: exception to endpoint
            log.warn("{ProductInCheck is already exists!}");
        }
    }

    public ProductsInCheckDTO findProductsByCheck(Check check) {
        //TODO: checking for an empty list
        return new ProductsInCheckDTO(productInCheckRepository.findByCheck(check));
    }

    //TODO: deleteProductInCheck
}
