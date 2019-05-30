package de.bringmeister.main.dto;

import java.util.List;

public class ProductDto {

    private String id;

    private String name;

    private String description;

    private List<PriceDto> priceDto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PriceDto> getPriceDto() {
        return priceDto;
    }

    public void setPriceDto(List<PriceDto> priceDto) {
        this.priceDto = priceDto;
    }
}
