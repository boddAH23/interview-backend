package de.bringmeister.main;

import de.bringmeister.data.entity.Product;
import de.bringmeister.data.repo.ProductRepo;
import de.bringmeister.main.dto.ProductDto;
import de.bringmeister.main.dto.factory.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepo productRepo;

    @Mock
    private Factory factory;

    @Test
    public void getProducts() {
        ProductServiceImpl service = new ProductServiceImpl(productRepo, factory);
        Product product = new Product();
        when(productRepo.getProducts()).thenReturn(Arrays.asList(product));
        ProductDto productDto = new ProductDto();
        when(factory.buildProduct(product)).thenReturn(productDto);

        List<ProductDto> products = service.getProducts();
        verify(factory).buildProduct(any());
        assertEquals(1, products.size());
        assertTrue(products.contains(productDto));
    }
}