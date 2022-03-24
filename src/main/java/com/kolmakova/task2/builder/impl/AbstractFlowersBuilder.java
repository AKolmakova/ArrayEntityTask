package com.kolmakova.task2.builder.impl;

import com.kolmakova.task2.builder.FlowerParser;
import com.kolmakova.task2.entity.AbstractFlower;
import com.kolmakova.task2.exception.ParseXmlException;
import com.kolmakova.task2.util.ResourcePathUtil;
import com.kolmakova.task2.validator.XmlFileValidator;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFlowersBuilder implements FlowerParser {
    protected final String schemaName;
    protected final String fileName;
    private Set<AbstractFlower> flowers;

    public AbstractFlowersBuilder(String fileName, String schemaName) throws ParseXmlException {
        flowers = new HashSet<>();
        this.schemaName = ResourcePathUtil.getResourcePath(schemaName);
        this.fileName = ResourcePathUtil.getResourcePath(fileName);
    }

    @Override
    public Set<AbstractFlower> buildFlowers() throws ParseXmlException {
        if (schemaNotValid()) {
            throw new ParseXmlException(String.format("File '%s' doesn't match schema '%s'", fileName, schemaName));
        }

        flowers = buildFlowersInternal();
        return flowers;
    }

    @Override
    public Set<AbstractFlower> getFlowers() {
        return flowers;
    }

    protected abstract Set<AbstractFlower> buildFlowersInternal() throws ParseXmlException;

    private boolean schemaNotValid() throws ParseXmlException {
        return !XmlFileValidator.isCorrect(fileName, schemaName);
    }
}
