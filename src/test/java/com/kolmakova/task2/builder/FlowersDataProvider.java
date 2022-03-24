package com.kolmakova.task2.builder;

import com.kolmakova.task2.entity.*;
import org.testng.annotations.DataProvider;

import java.time.YearMonth;

public class FlowersDataProvider {

    @DataProvider(name = "flowers_data")
    public static Object[][] createFlowersData() {
        Object[][] data = new Object[3][1];
        data[0] = new Object[]{buildFirstFlower()};
        data[1] = new Object[]{buildSecondFlower()};
        data[2] = new Object[]{buildThirdFlower()};

        return data;
    }

    private static FlowerPropagatingByCuttings buildFirstFlower() {
        FlowerPropagatingByCuttings.Builder builder = new FlowerPropagatingByCuttings.Builder("A-1111", "Tamarix");
        FlowerGrowingTips flowerGrowingTips = new FlowerGrowingTips(10, true, 40);

        return
                builder.dateOfPlanting(YearMonth.of(2017, 3)).
                        origin("CentralAsia").
                        averageFlowerSize(125).
                        flowerGrowingTips(flowerGrowingTips).
                        plantFlowerColor(PlantFlowerColor.PINK).
                        stemColor(StemColor.BROWN).
                        soilType(SoilType.GROUND).
                        typeOfCutting("Root")
                        .build();

    }

    private static FlowerPropagatingByLeaves buildSecondFlower() {
        FlowerPropagatingByLeaves.Builder builder = new FlowerPropagatingByLeaves.Builder("B-2222", "Begonia");
        FlowerGrowingTips flowerGrowingTips = new FlowerGrowingTips(1, true, 30);

        return builder.dateOfPlanting(YearMonth.of(2005, 5)).
                origin("SouthAmerica").
                averageFlowerSize(300).
                flowerGrowingTips(flowerGrowingTips).
                plantFlowerColor(PlantFlowerColor.RED).
                stemColor(StemColor.LIGHT_GREEN).
                soilType(SoilType.SODDY_PODZOL).
                leafPropagationType("LeafWithWholePetiole")
                .build();

    }

    private static FlowerPropagatingBySeeds buildThirdFlower() {
        FlowerPropagatingBySeeds.Builder builder = new FlowerPropagatingBySeeds.Builder("C-3333", "Delphinium");
        FlowerGrowingTips flowerGrowingTips = new FlowerGrowingTips(7, false, 100);

        return builder.dateOfPlanting(YearMonth.of(2000, 2)).
                origin("South-EastAsia").
                averageFlowerSize(500).
                flowerGrowingTips(flowerGrowingTips).
                plantFlowerColor(PlantFlowerColor.WHITE).
                stemColor(StemColor.GREEN).
                soilType(SoilType.PODZOL).
                typeOfSeeds("BiennialFlowerSeeds").
                seedsName("DelphiniumSeeds")
                .build();

    }

}
