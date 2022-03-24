package com.kolmakova.task2.builder.impl;

import com.kolmakova.task2.entity.*;
import com.kolmakova.task2.exception.ParseXmlException;
import com.kolmakova.task2.parser.FlowerXmlAttribute;
import com.kolmakova.task2.parser.FlowerXmlTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class FlowersStaxBuilder extends AbstractFlowersBuilder {

    private static final Logger LOGGER = LogManager.getLogger();
    private final XMLInputFactory inputFactory;

    public FlowersStaxBuilder(String fileName, String schemaName) throws ParseXmlException {
        super(fileName, schemaName);
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    protected Set<AbstractFlower> buildFlowersInternal() throws ParseXmlException {
        Set<AbstractFlower> flowers = new HashSet<>();
        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();

                if (type != XMLStreamConstants.START_ELEMENT) {
                    continue;
                }

                String name = reader.getLocalName();
                FlowerXmlTag tag = FlowerXmlTag.getFlowerXmlTag(name);

                if (FlowerXmlTag.FLOWER_PROPAGATING_BY_CUTTINGS == tag) {
                    AbstractFlower flower = buildFlower(new FlowerPropagatingByCuttings(), reader);
                    flowers.add(flower);

                }
                if (FlowerXmlTag.FLOWER_PROPAGATING_BY_LEAVES == tag) {
                    AbstractFlower flower = buildFlower(new FlowerPropagatingByLeaves(), reader);
                    flowers.add(flower);

                }
                if (FlowerXmlTag.FLOWER_PROPAGATING_BY_SEEDS == tag) {
                    AbstractFlower flower = buildFlower(new FlowerPropagatingBySeeds(), reader);
                    flowers.add(flower);
                }
            }
        } catch (XMLStreamException | IOException | ParseXmlException e) {
            throw new ParseXmlException("Exception when build Set of flowers", e);
        }
        LOGGER.info("Set of flowers is build. " + flowers);

        return flowers;
    }

    private AbstractFlower buildFlower(AbstractFlower flower, XMLStreamReader reader) throws XMLStreamException, ParseXmlException {

        flower.setFlowerCode(reader.getAttributeValue(null, FlowerXmlAttribute.FLOWER_CODE.getName()));
        String seedsNameAttrValue = reader.getAttributeValue(null, FlowerXmlAttribute.SEEDS_NAME.getName());

        if (seedsNameAttrValue != null) {
            FlowerPropagatingBySeeds flowerPropagatingBySeeds = (FlowerPropagatingBySeeds) flower;
            flowerPropagatingBySeeds.setSeedsName(seedsNameAttrValue);
        }

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT: {
                    name = reader.getLocalName();
                    FlowerXmlTag tag = FlowerXmlTag.getFlowerXmlTag(name);

                    switch (tag) {
                        case FLOWER_CODE:
                            flower.setFlowerCode(getXMLText(reader));
                            break;
                        case FLOWER_NAME:
                            flower.setName(getXMLText(reader));
                            break;
                        case DATE_OF_PLANTING:
                            flower.setDateOfPlanting(YearMonth.parse(getXMLText(reader)));
                            break;
                        case ORIGIN:
                            flower.setOrigin(getXMLText(reader));
                            break;
                        case AVERAGE_FLOWER_SIZE:
                            flower.setAverageFlowerSize(Integer.parseInt(getXMLText(reader)));
                            break;
                        case FLOWER_GROWING_TIPS:
                            flower.setFlowerGrowingTips(getXmlFlowerGrowingTips(reader));
                            break;
                        case PLANT_FLOWER_COLOR:
                            flower.setPlantFlowerColor(PlantFlowerColor.getPlantFlowerColor(getXMLText(reader)));
                            break;
                        case STEM_COLOR:
                            flower.setStemColor(StemColor.getStemColor(getXMLText(reader)));
                            break;
                        case SOIL_TYPE:
                            flower.setSoilType(SoilType.getSoilType(getXMLText(reader)));
                            break;

                        case TYPE_OF_CUTTING: {
                            FlowerPropagatingByCuttings flowerPropagatingByCuttings = (FlowerPropagatingByCuttings) flower;
                            flowerPropagatingByCuttings.setTypeOfCutting(getXMLText(reader));
                        }
                        case LEAF_PROPAGATION_TYPE: {
                            FlowerPropagatingByLeaves flowerPropagatingByLeaves = (FlowerPropagatingByLeaves) flower;
                            flowerPropagatingByLeaves.setLeafPropagationType(getXMLText(reader));

                        }

                        case TYPE_OF_SEEDS: {
                            FlowerPropagatingBySeeds flowerPropagatingBySeeds = (FlowerPropagatingBySeeds) flower;
                            flowerPropagatingBySeeds.setTypeOfSeeds(getXMLText(reader));
                        }

                    }
                }
                case XMLStreamConstants.END_ELEMENT: {
                    name = reader.getLocalName();
                    FlowerXmlTag tag = FlowerXmlTag.getFlowerXmlTag(name);
                    if (FlowerXmlTag.FLOWER_PROPAGATING_BY_CUTTINGS == tag
                            || FlowerXmlTag.FLOWER_PROPAGATING_BY_LEAVES == tag
                            || FlowerXmlTag.FLOWER_PROPAGATING_BY_SEEDS == tag) {
                        return flower;
                    }

                }
            }
        }
        throw new ParseXmlException(String.format("End tag of '%s' is not found.", flower.getClass().getSimpleName()));
    }

    private FlowerGrowingTips getXmlFlowerGrowingTips(XMLStreamReader reader) throws XMLStreamException, ParseXmlException {
        FlowerGrowingTips flowerGrowingTips = new FlowerGrowingTips();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT: {
                    name = reader.getLocalName();
                    FlowerXmlTag tag = FlowerXmlTag.getFlowerXmlTag(name);

                    switch (tag) {
                        case TEMPERATURE:
                            flowerGrowingTips.setTemperature(Integer.parseInt(getXMLText(reader)));
                            break;
                        case LIGHT_LOVING:
                            flowerGrowingTips.setLightLoving(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case WATERING:
                            flowerGrowingTips.setWatering(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    name = reader.getLocalName();
                    if (FlowerXmlTag.FLOWER_GROWING_TIPS == FlowerXmlTag.getFlowerXmlTag(name)) {
                        return flowerGrowingTips;
                    }
                    break;
                }
            }
        }
        throw new XMLStreamException("End tag is not found");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

}
