package pl.kurs.ex1;

import pl.kurs.ex1.models.*;
import pl.kurs.ex1.models.extendsClothes.*;

import java.math.BigDecimal;
import java.util.Collections;

public class Runner {
    public static void main(String[] args) {
        Wardrobe wardrobe = new Wardrobe();
        wardrobe.addClothes(
                new Shirt("Elegancka koszula", Size.L, BigDecimal.valueOf(340.99)),
                new Jacket("Czarny płascz", Size.XL, BigDecimal.valueOf(290.99)),
                new Trousers("Wygodne spodnie", Size.M, BigDecimal.valueOf(125.50)),
                new Jeans("New Yorker jeansy", Size.L, BigDecimal.valueOf(156.70)),
                new TShirt("Nike biała koszulka", Size.XL, BigDecimal.valueOf(549.10)),
                new TShirt("Adidas 3paski", Size.L, BigDecimal.valueOf(286.99))
        );

        wardrobe.addClothes(new Socks("Różowe skarpetki", Size.M, BigDecimal.valueOf(14.99)));
        wardrobe.addClothes(
                new Socks("Czarne bokserki", Size.M, BigDecimal.valueOf(9.99)),
                new Skirt("Góralska spódnica", Size.S, BigDecimal.valueOf(78.80))
        );

        Clothes biggestClothes = Collections.max(wardrobe.getClothes());
        Clothes.setClothesComparator(Clothes.CLOTHES_PRICE_COMPARATOR);
        Clothes mostExpensiveClothes = Collections.max(wardrobe.getClothes());

//        wardrobe.saveWardrobeClothesToFile();
//        wardrobe.loadWardrobeClothesFromFile("wardrobe.clothes");
    }
}
