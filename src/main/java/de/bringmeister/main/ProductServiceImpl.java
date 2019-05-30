package de.bringmeister.main;

import de.bringmeister.data.entity.Product;
import de.bringmeister.data.exception.RepositoryException;
import de.bringmeister.main.dto.factory.Factory;
import de.bringmeister.main.exception.ResourceNotFoundException;
import de.bringmeister.data.repo.PriceInformationRepo;
import de.bringmeister.data.repo.ProductRepo;
import de.bringmeister.main.dto.PriceDto;
import de.bringmeister.main.dto.ProductDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    private PriceInformationRepo priceInformationRepo;

    private Factory factory;

    public ProductServiceImpl(ProductRepo productRepo, PriceInformationRepo priceInformationRepo, Factory factory) {
        this.productRepo = productRepo;
        this.priceInformationRepo = priceInformationRepo;
        this.factory = factory;
    }

    public List<ProductDto> getProducts() throws RepositoryException {
        return productRepo.getProducts().stream()
                .map(factory::buildProduct)
                .collect(Collectors.toList());
    }

    public ProductDto getProduct(String id) throws RepositoryException {
        Product product = productRepo.getProduct(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
        ProductDto dto = factory.buildProduct(product);
        List<PriceDto> priceDtoList = priceInformationRepo.getPriceInformation(product.getSku()).stream()
                .map(factory::buildPrice)
                .collect(Collectors.toList());
        dto.setPriceDto(priceDtoList);
        return dto;
    }

    public PriceDto getPriceForProductAndUnit(String productId, String unit) {
        ProductDto product = getProduct(productId);
        Optional<PriceDto> optionalPriceDto = product.getPriceDto().stream()
                .filter(priceDto -> StringUtils.equals(priceDto.getUnit(), unit))
                .findFirst();
        return optionalPriceDto.orElseThrow(() -> new ResourceNotFoundException("Unit not found: " + unit));
    }

}
