package com.kolmakova.task2.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "flower_growing_tips")
public class FlowerGrowingTips {

    private int temperature;
    private boolean lightLoving;
    private int watering;

    public FlowerGrowingTips() {
    }

    public FlowerGrowingTips(int temperature, boolean lightLoving, int watering) {
        this.temperature = temperature;
        this.lightLoving = lightLoving;
        this.watering = watering;
    }

    @XmlElement(name = "temperature")
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @XmlElement(name = "light_loving")
    public boolean isLightLoving() {
        return lightLoving;
    }

    public void setLightLoving(boolean lightLoving) {
        this.lightLoving = lightLoving;
    }

    @XmlElement(name = "watering")
    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlowerGrowingTips that = (FlowerGrowingTips) o;

        if (temperature != that.temperature) return false;
        if (lightLoving != that.lightLoving) return false;
        return watering == that.watering;
    }

    @Override
    public int hashCode() {
        int result = temperature;
        result = 31 * result + (lightLoving ? 1 : 0);
        result = 31 * result + watering;
        return result;
    }

    @Override
    public String toString() {
        String className = this.getClass().getSimpleName();
        return className + "{temperature=" + temperature +
                ", lightLoving=" + lightLoving +
                ", watering=" + watering +
                '}';
    }
}
