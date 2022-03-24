package com.kolmakova.task2.parser;

import com.kolmakova.task2.entity.*;
import com.kolmakova.task2.exception.ParseXmlException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class SaxParseHandler extends DefaultHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String ELEMENT_FLOWER_PROPAGATING_BY_CUTTINGS = FlowerXmlTag.FLOWER_PROPAGATING_BY_CUTTINGS.getName();
    private static final String ELEMENT_FLOWER_PROPAGATING_BY_LEAVES = FlowerXmlTag.FLOWER_PROPAGATING_BY_LEAVES.getName();
    private static final String ELEMENT_FLOWER_PROPAGATING_BY_SEEDS = FlowerXmlTag.FLOWER_PROPAGATING_BY_SEEDS.getName();

    private final EnumSet<FlowerXmlTag> withText;
    private final Set<AbstractFlower> flowers;
    private AbstractFlower currentFlower;
    private FlowerPropagatingByCuttings currentFlowerPropagatingByCuttings;
    private FlowerPropagatingByLeaves currentFlowerPropagatingByLeaves;
    private FlowerPropagatingBySeeds currentFlowerPropagatingBySeeds;
    private FlowerXmlTag currentXmlTag;

    public SaxParseHandler() {
        flowers = new HashSet<>();
        withText = EnumSet.range(FlowerXmlTag.FLOWER_NAME, FlowerXmlTag.TYPE_OF_SEEDS);
    }

    public Set<AbstractFlower> getFlowers() {
        return flowers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {

        if (ELEMENT_FLOWER_PROPAGATING_BY_SEEDS.equals(qName)) {
            currentFlowerPropagatingBySeeds = new FlowerPropagatingBySeeds();
            String flowerCodeAttributeName = FlowerXmlAttribute.FLOWER_CODE.getName();
            int flowerCodeIndex = attrs.getIndex(flowerCodeAttributeName);
            currentFlowerPropagatingBySeeds.setFlowerCode(attrs.getValue(flowerCodeIndex));
            if (attrs.getLength() == 2) {
                String seedsNameAttributeName = FlowerXmlAttribute.SEEDS_NAME.getName();
                int seedsNameIndex = attrs.getIndex(seedsNameAttributeName);
                currentFlowerPropagatingBySeeds.setSeedsName(attrs.getValue(seedsNameIndex));
            }
            currentFlower = currentFlowerPropagatingBySeeds;

        } else if (ELEMENT_FLOWER_PROPAGATING_BY_CUTTINGS.equals(qName)) {
            currentFlower = new FlowerPropagatingByCuttings();
            currentFlower.setFlowerCode(attrs.getValue(0));
        } else if (ELEMENT_FLOWER_PROPAGATING_BY_LEAVES.equals(qName)) {
            currentFlower = new FlowerPropagatingByLeaves();
            currentFlower.setFlowerCode(attrs.getValue(0));
        } else {
            try {
                FlowerXmlTag temp = FlowerXmlTag.getFlowerXmlTag(qName);
                if (withText.contains(temp)) {
                    currentXmlTag = temp;
                }
            } catch (ParseXmlException exception) {
                LOGGER.warn("Unknown start element '" + qName + "'.", exception);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (ELEMENT_FLOWER_PROPAGATING_BY_CUTTINGS.equals(qName)) {
            flowers.add(currentFlowerPropagatingByCuttings);
        } else if (ELEMENT_FLOWER_PROPAGATING_BY_LEAVES.equals(qName)) {
            flowers.add(currentFlowerPropagatingByLeaves);
        } else if (ELEMENT_FLOWER_PROPAGATING_BY_SEEDS.equals(qName)) {
            flowers.add(currentFlowerPropagatingBySeeds);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case FLOWER_NAME:
                    currentFlower.setName(data);
                case PLANT_FLOWER_COLOR: {
                    try {
                        currentFlower.setPlantFlowerColor(PlantFlowerColor.getPlantFlowerColor(data));
                    } catch (ParseXmlException exception) {
                        LOGGER.warn(exception);
                    }
                }
                case STEM_COLOR: {
                    try {
                        currentFlower.setStemColor(StemColor.getStemColor(data));
                    } catch (ParseXmlException exception) {
                        LOGGER.warn(exception);
                    }
                }
                case SOIL_TYPE: {
                    try {
                        currentFlower.setSoilType(SoilType.getSoilType(data));
                    } catch (ParseXmlException exception) {
                        LOGGER.warn(exception);
                    }
                }
                case ORIGIN:
                    currentFlower.setOrigin(data);
                    break;
                case TYPE_OF_CUTTING: {
                    currentFlowerPropagatingByCuttings = (FlowerPropagatingByCuttings) currentFlower;
                    currentFlowerPropagatingByCuttings.setTypeOfCutting(data);
                    break;
                }
                case LEAF_PROPAGATION_TYPE: {
                    currentFlowerPropagatingByLeaves = (FlowerPropagatingByLeaves) currentFlower;
                    currentFlowerPropagatingByLeaves.setLeafPropagationType(data);
                    break;
                }
                case TYPE_OF_SEEDS: {
                    currentFlowerPropagatingBySeeds = (FlowerPropagatingBySeeds) currentFlower;
                    currentFlowerPropagatingBySeeds.setTypeOfSeeds(data);
                    break;
                }
                case TEMPERATURE: {
                    FlowerGrowingTips flowerGrowingTips = currentFlower.getFlowerGrowingTips();
                    flowerGrowingTips.setTemperature(Integer.parseInt(data));
                    currentFlower.setFlowerGrowingTips(flowerGrowingTips);
                    break;
                }
                case LIGHT_LOVING: {
                    FlowerGrowingTips flowerGrowingTips = currentFlower.getFlowerGrowingTips();
                    flowerGrowingTips.setLightLoving(Boolean.parseBoolean(data));
                    currentFlower.setFlowerGrowingTips(flowerGrowingTips);
                    break;
                }
                case WATERING: {
                    FlowerGrowingTips flowerGrowingTips = currentFlower.getFlowerGrowingTips();
                    flowerGrowingTips.setWatering(Integer.parseInt(data));
                    currentFlower.setFlowerGrowingTips(flowerGrowingTips);
                    break;
                }

                case AVERAGE_FLOWER_SIZE:
                    currentFlower.setAverageFlowerSize(Integer.parseInt(data));
                    break;
                case DATE_OF_PLANTING:
                    currentFlower.setDateOfPlanting(YearMonth.parse(data));
                    break;
                case FLOWER_CODE:
                    currentFlower.setFlowerCode(data);
                    break;
                default: {
                    LOGGER.error("Unknown tag " + currentXmlTag.name());
                    throw new EnumConstantNotPresentException(
                            currentXmlTag.getDeclaringClass(), currentXmlTag.name());
                }
            }
        }
        currentXmlTag = null;

    }
}


