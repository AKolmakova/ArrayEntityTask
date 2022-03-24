package com.kolmakova.task2.entity;

import com.kolmakova.task2.parser.YearMonthAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.YearMonth;

@XmlType(name = "flower")
public abstract class AbstractFlower {

    private String name;
    private String flowerCode;
    private String origin;
    private int averageFlowerSize;
    private PlantFlowerColor plantFlowerColor;
    private StemColor stemColor;
    private SoilType soilType;
    private YearMonth dateOfPlanting;
    private FlowerGrowingTips flowerGrowingTips;


    @XmlJavaTypeAdapter(YearMonthAdapter.class)
    @XmlElement(name = "date_of_planting")
    public YearMonth getDateOfPlanting() {
        return dateOfPlanting;
    }

    public void setDateOfPlanting(YearMonth dateOfPlanting) {
        this.dateOfPlanting = dateOfPlanting;
    }


    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "origin")
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @XmlElement(name = "average_flower_size")
    public int getAverageFlowerSize() {
        return averageFlowerSize;
    }

    public void setAverageFlowerSize(int averageFlowerSize) {
        this.averageFlowerSize = averageFlowerSize;
    }

    @XmlElement(name = "flower_growing_tips")
    public FlowerGrowingTips getFlowerGrowingTips() {
        return flowerGrowingTips;
    }


    @XmlAttribute(name = "flower_code")
    public String getFlowerCode() {
        return flowerCode;
    }

    public void setFlowerCode(String flowerCode) {
        this.flowerCode = flowerCode;
    }

    public void setFlowerGrowingTips(FlowerGrowingTips flowerGrowingTips) {
        this.flowerGrowingTips = flowerGrowingTips;
    }

    @XmlElement(name = "plant_flower_color")
    public PlantFlowerColor getPlantFlowerColor() {
        return plantFlowerColor;
    }

    public void setPlantFlowerColor(PlantFlowerColor plantFlowerColor) {
        this.plantFlowerColor = plantFlowerColor;
    }

    @XmlElement(name = "stem_color")
    public StemColor getStemColor() {
        return stemColor;
    }

    public void setStemColor(StemColor stemColor) {
        this.stemColor = stemColor;
    }

    @XmlElement(name = "soil_type")
    public SoilType getSoilType() {
        return soilType;
    }

    public void setSoilType(SoilType soilType) {
        this.soilType = soilType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractFlower that = (AbstractFlower) o;

        if (averageFlowerSize != that.averageFlowerSize) return false;
        if (!name.equals(that.name)) return false;
        if (!dateOfPlanting.equals(that.dateOfPlanting)) return false;
        if (!origin.equals(that.origin)) return false;
        if (!flowerGrowingTips.equals(that.flowerGrowingTips)) return false;
        if (plantFlowerColor != that.plantFlowerColor) return false;
        if (stemColor != that.stemColor) return false;
        if (soilType != that.soilType) return false;
        return flowerCode.equals(that.flowerCode);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + dateOfPlanting.hashCode();
        result = 31 * result + origin.hashCode();
        result = 31 * result + averageFlowerSize;
        result = 31 * result + flowerGrowingTips.hashCode();
        result = 31 * result + plantFlowerColor.hashCode();
        result = 31 * result + stemColor.hashCode();
        result = 31 * result + soilType.hashCode();
        result = 31 * result + flowerCode.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return " flowerCode='" + flowerCode + '\'' +
                " name='" + name + '\'' +
                ", dateOfPlanting='" + dateOfPlanting + '\'' +
                ", origin=" + origin +
                ", averageFlowerSize=" + averageFlowerSize +
                ", flowerGrowingTips=" + flowerGrowingTips +
                ", plantFlowerColor=" + plantFlowerColor +
                ", stemColor=" + stemColor +
                ", flowerCode=" + flowerCode +
                ", soilType=" + soilType;
    }
}
