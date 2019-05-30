package de.bringmeister.main.dto.factory;

import de.bringmeister.data.entity.Price;
import de.bringmeister.data.entity.PriceInformation;
import de.bringmeister.data.entity.Product;
import de.bringmeister.main.dto.PriceDto;
import de.bringmeister.main.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class Factory {

    public ProductDto buildProduct(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setDescription(product.getDescription());
        dto.setName(product.getName());
        return dto;
    }

    public PriceDto buildPrice(PriceInformation priceInformation) {
        PriceDto dto = new PriceDto();
        Price price = priceInformation.getPrice();
        if (price != null) {
            dto.setPrice(price.getValue() + " " + price.getCurrency());
        }
        dto.setUnit(priceInformation.getUnitDescription());
        return dto;
    }
}
