package de.bringmeister.data.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.bringmeister.data.entity.Product;
import de.bringmeister.data.exception.RepositoryException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductRepoTest {

    @Mock
    Resource file;

    @Mock
    XmlMapper mapper;

    @Test
    public void getProducts() throws Exception {
        ProductRepo repo = new ProductRepo(mapper, file);
        List<Product> list = buildList();
        when(mapper.readValue(any(InputStream.class), any(TypeReference.class))).thenReturn(list);

        List<Product> products = repo.getProducts();

        assertEquals(list, products);
    }

    @Test(expected = RepositoryException.class)
    public void getProductsWithException() throws Exception {
        ProductRepo repo = new ProductRepo(mapper, file);

        when(mapper.readValue(any(InputStream.class), any(TypeReference.class))).thenThrow(new IOException());
        repo.getProducts();
    }

    @Test
    public void getProduct() throws Exception {
        ProductRepo repo = new ProductRepo(mapper, file);
        List<Product> list = buildList();
        when(mapper.readValue(any(InputStream.class), any(TypeReference.class))).thenReturn(list);

        assertFalse(repo.getProduct("ID3").isPresent());
        Optional<Product> product = repo.getProduct("ID1");
        assertTrue(list.contains(product.get()));
    }

    private List<Product> buildList() {
        Product product1 = new Product();
        product1.setId("ID1");
        product1.setDescription("Desc1");

        Product product2 = new Product();
        product2.setId("ID2");
        product2.setDescription("Desc2");

        return Arrays.asList(product1, product2);
    }
}