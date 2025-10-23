package pl.kurs.ex1.models.extendsClothes;

import pl.kurs.ex1.models.Clothing;
import pl.kurs.ex1.models.Size;

import java.math.BigDecimal;

public class Jeans extends Clothing {
    public Jeans(String name, Size size, BigDecimal price) {
        super(name, size, price);
    }
}
