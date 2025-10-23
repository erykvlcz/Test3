package pl.kurs.ex1.models.extendsClothes;

import pl.kurs.ex1.models.Clothing;
import pl.kurs.ex1.models.Size;

import java.math.BigDecimal;

public class TShirt extends Clothing {
    public TShirt(String name, Size size, BigDecimal price) {
        super(name, size, price);
    }
}
