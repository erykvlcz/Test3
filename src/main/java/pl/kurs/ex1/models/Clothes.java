package pl.kurs.ex1.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;

public class Clothes implements Serializable, Comparable<Clothes> {
    private static final long serialVersionUID = 1L;
    public static final Comparator<Clothes> CLOTHES_SIZE_COMPARATOR = new ClothesSizeComparator();
    public static final Comparator<Clothes> CLOTHES_PRICE_COMPARATOR = new ClothesPriceComparator();

    private static Comparator<Clothes> basicComparator = Clothes.CLOTHES_SIZE_COMPARATOR;

    private String name;
    private Size size;
    private BigDecimal price;

    public Clothes(String name, Size size, BigDecimal price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static void setClothesComparator(Comparator<Clothes> newComparator){
        basicComparator = newComparator;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Clothes o) {
        return basicComparator.compare(this, o);
    }

    private static class ClothesSizeComparator implements Comparator<Clothes>{
        @Override
        public int compare(Clothes o1, Clothes o2) {
            return Integer.compare(o1.getSize().getPriority(), o2.getSize().getPriority());
        }
    }

    private static class ClothesPriceComparator implements Comparator<Clothes>{
        @Override
        public int compare(Clothes o1, Clothes o2) {
            return o1.getPrice().compareTo(o2.getPrice());
        }
    }

}
