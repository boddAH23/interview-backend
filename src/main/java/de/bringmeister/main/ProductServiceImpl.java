package de.bringmeister.main;

import de.bringmeister.data.exception.RepositoryException;
import de.bringmeister.data.repo.ProductRepo;
import de.bringmeister.main.dto.ProductDto;
import de.bringmeister.main.dto.factory.Factory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    private Factory factory;

    public ProductServiceImpl(ProductRepo productRepo, Factory factory) {
        this.productRepo = productRepo;
        this.factory = factory;
    }

    public List<ProductDto> getProducts() throws RepositoryException {
        return productRepo.getProducts().stream()
                .map(factory::buildProduct)
                .collect(Collectors.toList());
    }

}
