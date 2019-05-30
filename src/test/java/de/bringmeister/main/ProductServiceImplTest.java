package de.bringmeister.main;

import de.bringmeister.data.entity.PriceInformation;
import de.bringmeister.data.entity.Product;
import de.bringmeister.data.repo.PriceInformationRepo;
import de.bringmeister.data.repo.ProductRepo;
import de.bringmeister.main.dto.PriceDto;
import de.bringmeister.main.dto.ProductDto;
import de.bringmeister.main.dto.factory.Factory;
import de.bringmeister.main.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepo productRepo;

    @Mock
    private PriceInformationRepo priceRepo;

    @Mock
    private Factory factory;

    @Test
    public void getProducts() {
        ProductServiceImpl service = new ProductServiceImpl(productRepo, priceRepo, factory);
        Product product = new Product();
        when(productRepo.getProducts()).thenReturn(Arrays.asList(product));
        ProductDto productDto = new ProductDto();
        when(factory.buildProduct(product)).thenReturn(productDto);

        List<ProductDto> products = service.getProducts();
        verify(factory).buildProduct(any());
        assertEquals(1, products.size());
        assertTrue(products.contains(productDto));
    }

    @Test
    public void getProduct() {
        ProductServiceImpl service = new ProductServiceImpl(productRepo, priceRepo, factory);
        Product product = new Product();
        product.setSku("SKU_ID");
        when(productRepo.getProduct("ID")).thenReturn(Optional.of(product));
        PriceInformation priceInformation = new PriceInformation();
        when(priceRepo.getPriceInformation("SKU_ID")).thenReturn(Arrays.asList(priceInformation));
        PriceDto priceDto = new PriceDto();
        when(factory.buildPrice(priceInformation)).thenReturn(priceDto);
        ProductDto productDto = new ProductDto();
        when(factory.buildProduct(product)).thenReturn(productDto);

        ProductDto actualDto = service.getProduct("ID");
        verify(factory).buildPrice(priceInformation);
        assertEquals(1, actualDto.getPriceDto().size());
        assertTrue(actualDto.getPriceDto().contains(priceDto));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getProductWithException() {
        ProductServiceImpl service = new ProductServiceImpl(productRepo, priceRepo, factory);
        when(productRepo.getProduct("ID")).thenReturn(Optional.empty());
        service.getProduct("ID");
    }

    @Test
    public void getPriceForProductAndUnit() {
        ProductServiceImpl service = new ProductServiceImpl(productRepo, priceRepo, factory);
        Product product = new Product();
        product.setSku("SKU_ID");
        when(productRepo.getProduct("ID")).thenReturn(Optional.of(product));
        PriceInformation priceInformation = new PriceInformation();
        when(priceRepo.getPriceInformation("SKU_ID")).thenReturn(Arrays.asList(priceInformation));
        PriceDto priceDto = new PriceDto();
        priceDto.setUnit("package");
        when(factory.buildPrice(priceInformation)).thenReturn(priceDto);
        when(factory.buildProduct(product)).thenReturn(new ProductDto());

        PriceDto actualDto = service.getPriceForProductAndUnit("ID", "package");
        assertEquals(priceDto, actualDto);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getPriceForProductAndUnitWithException() {
        ProductServiceImpl service = new ProductServiceImpl(productRepo, priceRepo, factory);
        Product product = new Product();
        product.setSku("SKU_ID");
        when(productRepo.getProduct("ID")).thenReturn(Optional.of(product));
        PriceInformation priceInformation = new PriceInformation();
        when(priceRepo.getPriceInformation("SKU_ID")).thenReturn(Arrays.asList(priceInformation));
        when(factory.buildPrice(priceInformation)).thenReturn(new PriceDto());
        when(factory.buildProduct(product)).thenReturn(new ProductDto());

        service.getPriceForProductAndUnit("ID", "package");
    }
}