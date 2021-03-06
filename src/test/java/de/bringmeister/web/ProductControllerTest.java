package de.bringmeister.web;

import de.bringmeister.main.ProductService;
import de.bringmeister.main.dto.PriceDto;
import de.bringmeister.main.dto.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    ProductService service;

    @Test
    public void getProducts() {
        ProductController controller = new ProductController(service);
        List<ProductDto> list = Arrays.asList(new ProductDto());
        when(service.getProducts()).thenReturn(list);

        ResponseEntity<List<ProductDto>> responseEntity = controller.getProducts();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(list, responseEntity.getBody());
    }

    @Test
    public void getProduct() {
        ProductController controller = new ProductController(service);
        ProductDto productDto = new ProductDto();
        when(service.getProduct("ID")).thenReturn(productDto);

        ResponseEntity<ProductDto> responseEntity = controller.getProduct("ID");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productDto, responseEntity.getBody());
    }

    @Test
    public void getProductPriceForUnit() {
        ProductController controller = new ProductController(service);
        PriceDto priceDto = new PriceDto();
        when(service.getPriceForProductAndUnit("ID", "package")).thenReturn(priceDto);

        ResponseEntity<PriceDto> responseEntity = controller.getProductPriceForUnit("ID", "package");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(priceDto, responseEntity.getBody());
    }
}