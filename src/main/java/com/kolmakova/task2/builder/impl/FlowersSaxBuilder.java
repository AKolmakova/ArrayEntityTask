package com.kolmakova.task2.builder.impl;

import com.kolmakova.task2.entity.AbstractFlower;
import com.kolmakova.task2.exception.ParseXmlException;
import com.kolmakova.task2.parser.SaxParseHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class FlowersSaxBuilder extends AbstractFlowersBuilder {

    private static final Logger LOGGER = LogManager.getLogger();
    private final SaxParseHandler saxParseHandler = new SaxParseHandler();
    private final XMLReader reader;

    public FlowersSaxBuilder(String fileName, String schemaName) throws ParseXmlException {
        super(fileName, schemaName);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException exception) {
            throw new ParseXmlException("Flowers SaxBuilder not created", exception);
        }
        reader.setContentHandler(saxParseHandler);
    }

    @Override
    protected Set<AbstractFlower> buildFlowersInternal() throws ParseXmlException {
        try {
            reader.parse(fileName);
        } catch (IOException | SAXException e) {
            throw new ParseXmlException("Exception when build Set of flowers", e);
        }
        Set<AbstractFlower> flowers = saxParseHandler.getFlowers();
        LOGGER.info("Set of Flowers build. " + flowers);

        return flowers;
    }
}
