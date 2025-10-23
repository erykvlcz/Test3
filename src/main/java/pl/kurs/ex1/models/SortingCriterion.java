package pl.kurs.ex1.models;

import java.util.Comparator;

public enum SortingCriterion {
    MOST_EXPENSIVE_CLOTHES(Clothing.CLOTHES_PRICE_COMPARATOR),
    BIGGEST_SIZE(Clothing.CLOTHES_SIZE_COMPARATOR);

    private Comparator<Clothing> comparator;

    SortingCriterion(Comparator<Clothing> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Clothing> getComparator() {
        return comparator;
    }
}
