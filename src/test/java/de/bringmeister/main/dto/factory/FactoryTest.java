package de.bringmeister.main.dto.factory;

import de.bringmeister.data.entity.Price;
import de.bringmeister.data.entity.PriceInformation;
import de.bringmeister.data.entity.Product;
import de.bringmeister.data.entity.Unit;
import de.bringmeister.main.dto.PriceDto;
import de.bringmeister.main.dto.ProductDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class FactoryTest {

    @Test
    public void buildProduct() {
        Factory factory = new Factory();
        ProductDto productDto = factory.buildProduct(getProduct());
        assertEquals("Name", productDto.getName());
        assertEquals("ID", productDto.getId());
        assertEquals("Description", productDto.getDescription());
    }

    private Product getProduct() {
        Product product = new Product();
        product.setName("Name");
        product.setId("ID");
        product.setDescription("Description");
        product.setSku("SKU");
        return product;
    }

    @Test
    public void buildPrice() {
        Factory factory = new Factory();
        PriceDto priceDto = factory.buildPrice(getPriceInformation());
        assertEquals("5.0 EUR", priceDto.getPrice());
        assertEquals("package", priceDto.getUnit());
    }

    private PriceInformation getPriceInformation() {
        PriceInformation priceInformation = new PriceInformation();
        Price price = new Price();
        price.setValue(5.0);
        price.setCurrency("EUR");
        priceInformation.setPrice(price);
        priceInformation.setId("ID");
        priceInformation.setUnit(Unit.PACKAGE);
        return priceInformation;
    }
}