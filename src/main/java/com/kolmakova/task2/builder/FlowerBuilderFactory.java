package com.kolmakova.task2.builder;

import com.kolmakova.task2.builder.impl.*;
import com.kolmakova.task2.exception.ParseXmlException;
import com.kolmakova.task2.parser.ParserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlowerBuilderFactory {

    private static final Logger LOGGER = LogManager.getLogger();

    private FlowerBuilderFactory() {
    }

    public static AbstractFlowersBuilder createFlowerBuilder(String fileName, String schemaName, ParserType parserType) throws ParseXmlException {
        switch (parserType) {
            case DOM: {
                LOGGER.info("Created new FlowersDomBuilder");
                return new FlowersDomBuilder(fileName, schemaName);
            }
            case SAX: {
                LOGGER.info("Created new FlowersSaxBuilder");
                return new FlowersSaxBuilder(fileName, schemaName);
            }
            case STAX: {
                LOGGER.info("Created new FlowersStaxBuilder");
                return new FlowersStaxBuilder(fileName, schemaName);
            }
            case JAXB: {
                LOGGER.info("Created new FlowersJaxbBuilder");
                return new FlowersJaxBBuilder(fileName, schemaName);
            }
            default: {
                throw new ParseXmlException(String.format("Type parser not found for type %s", parserType));
            }
        }
    }
}
