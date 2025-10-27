package pl.kurs.ex3;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class  MinMax<T extends Comparable<T>>{
    private T min;
    private T max;

    private MinMax(T min, T max) {
        this.min = min;
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }

    public static <T extends Comparable<T>> MinMax<T> createMinMax(List<T> list){
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }
        if(list.stream().anyMatch(x -> x == null)) {
            throw new IllegalArgumentException("MinMax can not be null");
        }

        return new MinMax<>(getMin(list), getMax(list));
    }

    private static <T extends Comparable<T>> T getMax(List<T> list) {
        return Collections.max(list);
    }

    private static <T extends Comparable<T>> T getMin(List<T> list) {
        return Collections.min(list);
    }

}
