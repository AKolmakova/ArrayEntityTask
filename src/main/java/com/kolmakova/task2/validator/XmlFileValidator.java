package com.kolmakova.task2.validator;

import com.kolmakova.task2.exception.ParseXmlException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class XmlFileValidator {

    private static final Logger LOGGER = LogManager.getLogger();

    public static boolean isCorrect(String xmlFileName, String schemaFileName) throws ParseXmlException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaFile = new File(schemaFileName);

        try {
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(new FileInputStream(xmlFileName));
            validator.validate(source);
            LOGGER.info(String.format("File '%s' matches schema '%s'", xmlFileName, schemaFileName));
        } catch (SAXException e) {
            LOGGER.info(String.format("File '%s' doesn't match schema '%s'", xmlFileName, schemaFileName), e);
            return false;
        } catch (IOException e) {
            throw new ParseXmlException(String.format("Exception when validate file '%s' with schema '%s'", xmlFileName, schemaFileName), e);
        }

        return true;
    }
}
