package com.kolmakova.task2.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
public class Flowers {

    private Set<AbstractFlower> flowers;

    public Flowers() {
        this.flowers = new HashSet<>();
    }

    public Set<AbstractFlower> getFlowers() {
        return flowers;
    }

    @XmlElements({
            @XmlElement(type = FlowerPropagatingByCuttings.class, name = "flower_propagating_by_cuttings"),
            @XmlElement(type = FlowerPropagatingByLeaves.class, name = "flower_propagating_by_leaves"),
            @XmlElement(type = FlowerPropagatingBySeeds.class, name = "flower_propagating_by_seeds")
    })

    public void setFlowers(Set<AbstractFlower> flowers) {
        this.flowers = flowers;
    }

    public void addFlower(AbstractFlower flower) {
        if (this.flowers == null) {
            this.flowers = new HashSet<>();
        }
        this.flowers.add(flower);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flowers flowers1 = (Flowers) o;

        return flowers.equals(flowers1.flowers);
    }

    @Override
    public int hashCode() {
        return flowers.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{flowers=" + flowers + '}';
    }
}
