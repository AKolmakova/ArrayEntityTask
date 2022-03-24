package com.kolmakova.task2.builder.impl;

import com.kolmakova.task2.entity.AbstractFlower;
import com.kolmakova.task2.entity.Flowers;
import com.kolmakova.task2.exception.ParseXmlException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class FlowersJaxBBuilder extends AbstractFlowersBuilder {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Unmarshaller unmarshaller;

    public FlowersJaxBBuilder(String fileName, String schemaName) throws ParseXmlException {
        super(fileName, schemaName);
        try {
            JAXBContext context = JAXBContext.newInstance(Flowers.class);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new ParseXmlException("FlowersJaxbBuilder not created. ", e);
        }
    }

    @Override
    protected Set<AbstractFlower> buildFlowersInternal() throws ParseXmlException {
        try (InputStream fis = new FileInputStream(fileName)) {
            Flowers flowersObject = (Flowers) unmarshaller.unmarshal(fis);
            Set<AbstractFlower> flowers = flowersObject.getFlowers();
            LOGGER.info("Set of flowers is build. " + flowers);

            return flowers;
        } catch (IOException | JAXBException exception) {
            throw new ParseXmlException("Exception when build Set of flowers", exception);
        }
    }
}
