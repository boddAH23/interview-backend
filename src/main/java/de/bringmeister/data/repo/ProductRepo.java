package de.bringmeister.data.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.bringmeister.data.entity.Product;
import de.bringmeister.data.exception.RepositoryException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepo {

    private static final Logger LOG = LoggerFactory.getLogger(ProductRepo.class);

    private Resource productFile;

    private XmlMapper xmlMapper;

    public ProductRepo(@Qualifier("xmlMapper") XmlMapper xmlMapper, @Value("classpath:products/products.xml") Resource productFile) {
        this.xmlMapper = xmlMapper;
        this.productFile = productFile;
    }

    public List<Product> getProducts() throws RepositoryException {
        try {
            return xmlMapper.readValue(productFile.getInputStream(), new TypeReference<List<Product>>(){});
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new RepositoryException("Error during loading products");
        }
    }

    public Optional<Product> getProduct(String id) throws RepositoryException {
       return getProducts().stream()
                .filter(product -> StringUtils.equals(product.getId(), id))
                .findFirst();
    }
}
