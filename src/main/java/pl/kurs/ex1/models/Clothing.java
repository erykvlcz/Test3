package pl.kurs.ex1.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;

public class Clothing implements Serializable, Comparable<Clothing> {
    private static final long serialVersionUID = 1L;
    public static final Comparator<Clothing> CLOTHES_SIZE_COMPARATOR = new ClothesSizeComparator();
    public static final Comparator<Clothing> CLOTHES_PRICE_COMPARATOR = new ClothesPriceComparator();

    protected static Comparator<Clothing> basicComparator = Clothing.CLOTHES_SIZE_COMPARATOR;

    private String name;
    private Size size;
    private BigDecimal price;

    public Clothing(String name, Size size, BigDecimal price) {
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

    public static void setClothesComparator(SortingCriterion sortingCriterion){
        basicComparator = sortingCriterion.getComparator();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Clothing clothing = (Clothing) o;
        return Objects.equals(name, clothing.name) && size == clothing.size && Objects.equals(price, clothing.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, price);
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
    public int compareTo(Clothing o) {
        return basicComparator.compare(this, o);
    }

    private static class ClothesSizeComparator implements Comparator<Clothing>{
        @Override
        public int compare(Clothing o1, Clothing o2) {
            return Integer.compare(o1.getSize().getPriority(), o2.getSize().getPriority());
        }
    }

    private static class ClothesPriceComparator implements Comparator<Clothing>{
        @Override
        public int compare(Clothing o1, Clothing o2) {
            return o1.getPrice().compareTo(o2.getPrice());
        }
    }

}
