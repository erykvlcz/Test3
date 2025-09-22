package pl.kurs.ex1.models.extendsClothes;

import pl.kurs.ex1.models.Clothes;
import pl.kurs.ex1.models.Size;

import java.math.BigDecimal;

public class Shirt extends Clothes {
    public Shirt(String name, Size size, BigDecimal price) {
        super(name, size, price);
    }
}
