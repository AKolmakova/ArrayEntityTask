package com.kolmakova.task2.builder.impl;

import com.kolmakova.task2.entity.*;
import com.kolmakova.task2.exception.ParseXmlException;
import com.kolmakova.task2.parser.FlowerXmlAttribute;
import com.kolmakova.task2.parser.FlowerXmlTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class FlowersDomBuilder extends AbstractFlowersBuilder {

    private static final Logger LOGGER = LogManager.getLogger();

    private final DocumentBuilder documentBuilder;

    public FlowersDomBuilder(String fileName, String schemaLocation) throws ParseXmlException {
        super(fileName, schemaLocation);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ParseXmlException("Flowers DomBuilder not created", e);
        }
    }

    @Override
    protected Set<AbstractFlower> buildFlowersInternal() throws ParseXmlException {
        Set<AbstractFlower> flowers = new HashSet<>();
        try {
            Document document = documentBuilder.parse(new FileInputStream(fileName));
            Element root = document.getDocumentElement();

            NodeList flowerPropagatingByCuttingsList = root.getElementsByTagName(FlowerXmlTag.FLOWER_PROPAGATING_BY_CUTTINGS.getName());
            NodeList flowerPropagatingByLeavesList = root.getElementsByTagName(FlowerXmlTag.FLOWER_PROPAGATING_BY_LEAVES.getName());
            NodeList flowerPropagatingBySeedsList = root.getElementsByTagName(FlowerXmlTag.FLOWER_PROPAGATING_BY_SEEDS.getName());


            for (int i = 0; i < flowerPropagatingByCuttingsList.getLength(); i++) {
                Element flowerElement = (Element) flowerPropagatingByCuttingsList.item(i);
                buildFlowerPropagatingByCuttings(flowerElement, flowers);
            }

            for (int i = 0; i < flowerPropagatingByLeavesList.getLength(); i++) {
                Element flowerElement = (Element) flowerPropagatingByLeavesList.item(i);
                buildFlowerPropagatingByLeaves(flowerElement, flowers);
            }

            for (int i = 0; i < flowerPropagatingBySeedsList.getLength(); i++) {
                Element flowerElement = (Element) flowerPropagatingBySeedsList.item(i);
                buildFlowerPropagatingBySeeds(flowerElement, flowers);
            }

            LOGGER.info("Set of flowers is build. " + flowers);
        } catch (IOException | SAXException | ParseXmlException e) {
            throw new ParseXmlException("Exception when build Set of flowers", e);
        }

        return flowers;
    }

    private void buildFlowerPropagatingByCuttings(Element flowerElement, Set<AbstractFlower> flowers) throws ParseXmlException {
        FlowerPropagatingByCuttings flower = new FlowerPropagatingByCuttings();
        buildFlower(flower, flowerElement);
        flower.setTypeOfCutting(getElementTextContent(flowerElement, FlowerXmlTag.TYPE_OF_CUTTING.getName()));
        flowers.add(flower);
    }

    private void buildFlowerPropagatingByLeaves(Element flowerElement, Set<AbstractFlower> flowers) throws ParseXmlException {
        FlowerPropagatingByLeaves flower = new FlowerPropagatingByLeaves();
        buildFlower(flower, flowerElement);
        flower.setLeafPropagationType(getElementTextContent(flowerElement, FlowerXmlTag.LEAF_PROPAGATION_TYPE.getName()));
        flowers.add(flower);
    }

    private void buildFlowerPropagatingBySeeds(Element flowerElement, Set<AbstractFlower> flowers) throws ParseXmlException {
        FlowerPropagatingBySeeds flower = new FlowerPropagatingBySeeds();
        buildFlower(flower, flowerElement);
        String seedsNameAttr = FlowerXmlAttribute.SEEDS_NAME.getName();
        if (flowerElement.hasAttribute(seedsNameAttr)) {
            flower.setSeedsName(flowerElement.getAttribute(seedsNameAttr));
        }
        flower.setTypeOfSeeds(getElementTextContent(flowerElement, FlowerXmlTag.TYPE_OF_SEEDS.getName()));
        flowers.add(flower);
    }

    private void buildFlower(AbstractFlower flower, Element flowerElement) throws ParseXmlException {
        flower.setFlowerCode(flowerElement.getAttribute(FlowerXmlAttribute.FLOWER_CODE.getName()));
        flower.setName(flowerElement.getAttribute(FlowerXmlAttribute.FLOWER_CODE.getName()));
        flower.setName(getElementTextContent(flowerElement, FlowerXmlTag.FLOWER_NAME.getName()));
        flower.setDateOfPlanting(getElementYearMonthContent(flowerElement, FlowerXmlTag.DATE_OF_PLANTING.getName()));
        flower.setOrigin(getElementTextContent(flowerElement, FlowerXmlTag.ORIGIN.getName()));
        flower.setAverageFlowerSize(getElementIntContent(flowerElement, FlowerXmlTag.AVERAGE_FLOWER_SIZE.getName()));
        flower.setFlowerGrowingTips(buildFlowerGrowingTips(flowerElement));
        flower.setPlantFlowerColor(getElementPlantFlowerColor(flowerElement));
        flower.setStemColor(getElementStemColor(flowerElement));
        flower.setSoilType(getElementSoilType(flowerElement));

    }

    private FlowerGrowingTips buildFlowerGrowingTips(Element flowerElement) {
        NodeList flowerGrowingTipsList = flowerElement.getElementsByTagName(FlowerXmlTag.FLOWER_GROWING_TIPS.getName());
        Element flowerGrowingTipsElement = (Element) flowerGrowingTipsList.item(0);
        FlowerGrowingTips flowerGrowingTips = new FlowerGrowingTips();
        flowerGrowingTips.setTemperature(getElementIntContent(flowerGrowingTipsElement, FlowerXmlTag.TEMPERATURE.getName()));
        flowerGrowingTips.setLightLoving(getElementBooleanContent(flowerGrowingTipsElement, FlowerXmlTag.LIGHT_LOVING.getName()));
        flowerGrowingTips.setWatering(getElementIntContent(flowerGrowingTipsElement, FlowerXmlTag.WATERING.getName()));
        return flowerGrowingTips;
    }

    private int getElementIntContent(Element element, String tagName) {
        String stringInt = getElementTextContent(element, tagName);
        return Integer.parseInt(stringInt);
    }


    private String getElementTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }

    private PlantFlowerColor getElementPlantFlowerColor(Element element) throws ParseXmlException {
        String plantFlowerColor = getElementTextContent(element, FlowerXmlTag.PLANT_FLOWER_COLOR.getName());
        return PlantFlowerColor.getPlantFlowerColor(plantFlowerColor);
    }

    private StemColor getElementStemColor(Element element) throws ParseXmlException {
        String stemColor = getElementTextContent(element, FlowerXmlTag.STEM_COLOR.getName());
        return StemColor.getStemColor(stemColor);
    }

    private SoilType getElementSoilType(Element element) throws ParseXmlException {
        String soilType = getElementTextContent(element, FlowerXmlTag.SOIL_TYPE.getName());
        return SoilType.getSoilType(soilType);
    }

    private YearMonth getElementYearMonthContent(Element element, String tagName) {
        String yearMonthString = getElementTextContent(element, tagName);
        return YearMonth.parse(yearMonthString);
    }

    private boolean getElementBooleanContent(Element candyElement, String tagName) {
        String booleanString = getElementTextContent(candyElement, tagName);
        return Boolean.parseBoolean(booleanString);
    }
}
