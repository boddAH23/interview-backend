package de.bringmeister.data.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.bringmeister.data.entity.PriceInformation;
import de.bringmeister.data.exception.RepositoryException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceInformationRepo {

    private static final  Logger LOG = LoggerFactory.getLogger(PriceInformationRepo.class);

    private ObjectMapper objectMapper;

    private Resource priceFile;

    public PriceInformationRepo(ObjectMapper objectMapper, @Value("classpath:products/prices.json")  Resource priceFile) {
        this.objectMapper = objectMapper;
        this.priceFile = priceFile;
    }

    public List<PriceInformation> getPriceInformation(String id) throws RepositoryException {
        List<PriceInformation> priceList;
        try {
            priceList = objectMapper.readValue(priceFile.getInputStream(), new TypeReference<List<PriceInformation>>(){});
            return priceList.stream()
                    .filter(priceInformation -> StringUtils.equals(priceInformation.getId(), id))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new RepositoryException("Error during loading prices");
        }
    }
}
