package com.kolmakova.task2.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.YearMonth;


@XmlType(name = "flower_propagating_by_seeds")
@XmlRootElement
public class FlowerPropagatingBySeeds extends AbstractFlower {

    private String typeOfSeeds;
    private String seedsName;

    @XmlElement(name = "type_of_seeds")
    public String getTypeOfSeeds() {
        return typeOfSeeds;
    }


    public void setTypeOfSeeds(String typeOfSeeds) {
        this.typeOfSeeds = typeOfSeeds;
    }

    @XmlAttribute(name = "seeds_name")
    public String getSeedsName() {
        return seedsName;
    }

    public void setSeedsName(String seedsName) {
        this.seedsName = seedsName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FlowerPropagatingBySeeds that = (FlowerPropagatingBySeeds) o;

        if (!typeOfSeeds.equals(that.typeOfSeeds)) return false;
        return seedsName.equals(that.seedsName);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + typeOfSeeds.hashCode();
        result = 31 * result + seedsName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        String className = this.getClass().getSimpleName();
        return className + '{' + super.toString() +
                ",typeOfSeeds =" + typeOfSeeds + '\'' +
                ",seedsName =" + seedsName + '\'' +
                '}';
    }

    public static class Builder {

        private final FlowerPropagatingBySeeds flower;

        public Builder(String flowerCode, String name) {
            flower = new FlowerPropagatingBySeeds();
            flower.setFlowerCode(flowerCode);
            flower.setName(name);

        }

        public Builder dateOfPlanting(YearMonth dateOfPlanting) {
            flower.setDateOfPlanting(dateOfPlanting);
            return this;
        }

        public Builder origin(String origin) {
            flower.setOrigin(origin);
            return this;
        }

        public Builder averageFlowerSize(int averageFlowerSize) {
            flower.setAverageFlowerSize(averageFlowerSize);
            return this;
        }

        public Builder flowerGrowingTips(FlowerGrowingTips flowerGrowingTips) {
            flower.setFlowerGrowingTips(flowerGrowingTips);
            return this;
        }

        public Builder plantFlowerColor(PlantFlowerColor plantFlowerColor) {
            flower.setPlantFlowerColor(plantFlowerColor);
            return this;
        }

        public Builder stemColor(StemColor stemColor) {
            flower.setStemColor(stemColor);
            return this;
        }

        public Builder soilType(SoilType soilType) {
            flower.setSoilType(soilType);
            return this;
        }

        public Builder typeOfSeeds(String typeOfSeeds) {
            flower.setTypeOfSeeds(typeOfSeeds);
            return this;
        }

        public Builder seedsName(String seedsName) {
            flower.setSeedsName(seedsName);
            return this;
        }

        public FlowerPropagatingBySeeds build() {
            return flower;
        }
    }

}
