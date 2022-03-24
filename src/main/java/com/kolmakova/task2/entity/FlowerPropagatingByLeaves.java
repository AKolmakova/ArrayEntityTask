package com.kolmakova.task2.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.YearMonth;

@XmlType(name = "flower_propagating_by_leaves")
@XmlRootElement
public class FlowerPropagatingByLeaves extends AbstractFlower {

    private String leafPropagationType;

    @XmlElement(name = "leaf_propagation_type")
    public String getLeafPropagationType() {
        return leafPropagationType;
    }

    public void setLeafPropagationType(String leafPropagationType) {
        this.leafPropagationType = leafPropagationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FlowerPropagatingByLeaves that = (FlowerPropagatingByLeaves) o;

        return leafPropagationType.equals(that.leafPropagationType);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + leafPropagationType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        String className = this.getClass().getSimpleName();
        return className + '{' + super.toString() +
                ", leafPropagationType=" + leafPropagationType + '\'' +
                '}';
    }

    public static class Builder {

        private final FlowerPropagatingByLeaves flower;

        public Builder(String flowerCode, String name) {
            flower = new FlowerPropagatingByLeaves();
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

        public Builder leafPropagationType(String leafPropagationType) {
            flower.setLeafPropagationType(leafPropagationType);
            return this;
        }

        public FlowerPropagatingByLeaves build() {
            return flower;
        }
    }

}
