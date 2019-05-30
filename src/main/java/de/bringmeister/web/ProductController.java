package de.bringmeister.web;

import de.bringmeister.main.ProductService;
import de.bringmeister.main.dto.PriceDto;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable(value="id") String id) {

        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping(path = "/{id}/price")
    public ResponseEntity<PriceDto> getProductPriceForUnit(@PathVariable(value="id") String id, @RequestParam String unit) {

        return ResponseEntity.ok(productService.getPriceForProductAndUnit(id, unit));
    }
}
