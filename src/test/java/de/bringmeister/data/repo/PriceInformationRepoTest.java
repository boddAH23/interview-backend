package de.bringmeister.data.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.bringmeister.data.entity.Price;
import de.bringmeister.data.entity.PriceInformation;
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

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceInformationRepoTest {

    @Mock
    ObjectMapper objectMapper;

    @Mock
    Resource file;

    @Test
    public void getPriceInformation() throws Exception {
        PriceInformationRepo repo = new PriceInformationRepo(objectMapper, file);

        when(objectMapper.readValue(any(InputStream.class), any(TypeReference.class))).thenReturn(buildList());
        List<PriceInformation> priceList = repo.getPriceInformation("Price_ID1");
        verify(file).getInputStream();

        assertEquals(1, priceList.size());
        assertEquals("Price_ID1", priceList.get(0).getId());
        assertEquals("EUR", priceList.get(0).getPrice().getCurrency());
        assertEquals(20, priceList.get(0).getPrice().getValue(), 0.001);
    }

    @Test(expected = RepositoryException.class)
    public void getPriceInformationWithError() throws Exception {
        PriceInformationRepo repo = new PriceInformationRepo(objectMapper, file);

        when(objectMapper.readValue(any(InputStream.class), any(TypeReference.class))).thenThrow(new IOException());
        repo.getPriceInformation("Price_ID1");
    }

    private List<PriceInformation> buildList() {
        PriceInformation information1 = new PriceInformation();
        information1.setId("Price_ID1");
        Price price1 = new Price();
        price1.setCurrency("EUR");
        price1.setValue(20);
        information1.setPrice(price1);

        PriceInformation information2 = new PriceInformation();
        information2.setId("Price_ID2");
        Price price2 = new Price();
        price2.setCurrency("EUR");
        price2.setValue(5);
        information2.setPrice(price2);

        return Arrays.asList(information1, information2);
    }
}