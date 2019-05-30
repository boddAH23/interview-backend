package de.bringmeister.web;

import de.bringmeister.main.ProductService;
import de.bringmeister.main.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {


        return ResponseEntity.ok(productService.getProducts());
    }
}
