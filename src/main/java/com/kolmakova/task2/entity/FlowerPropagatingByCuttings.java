package com.kolmakova.task2.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.YearMonth;

@XmlType(name = "flower_propagating_by_cuttings")
@XmlRootElement
public class FlowerPropagatingByCuttings extends AbstractFlower {

    private String typeOfCutting;

    @XmlElement(name = "type_of_cutting")
    public String getTypeOfCutting() {
        return typeOfCutting;
    }

    public void setTypeOfCutting(String typeOfCutting) {
        this.typeOfCutting = typeOfCutting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FlowerPropagatingByCuttings that = (FlowerPropagatingByCuttings) o;

        return typeOfCutting.equals(that.typeOfCutting);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + typeOfCutting.hashCode();
        return result;
    }

    @Override
    public String toString() {
        String className = this.getClass().getSimpleName();
        return className + '{' + super.toString() +
                ", typeOfCutting=" + typeOfCutting + '\'' +
                '}';
    }

    public static class Builder {

        private final FlowerPropagatingByCuttings flower;

        public Builder(String flowerCode, String name) {
            flower = new FlowerPropagatingByCuttings();
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

        public Builder typeOfCutting(String typeOfCutting) {
            flower.setTypeOfCutting(typeOfCutting);
            return this;
        }

        public FlowerPropagatingByCuttings build() {
            return flower;
        }
    }
}
