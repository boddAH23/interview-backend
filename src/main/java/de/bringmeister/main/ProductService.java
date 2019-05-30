package de.bringmeister.main;

import de.bringmeister.main.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProducts();

    ProductDto getProduct(String id);
}
