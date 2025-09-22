package pl.kurs.ex3;

import java.util.List;

public class Ex3Runner {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(5, 2, 9, 1, 7);
        List<String> words = List.of("Coraz", "blizej", "swieta!", "Jesieniary", "kebs");
        List<String> words2 = List.of();

        MinMax<Integer> numberMinMax = MinMax.createMinMax(numbers);
        MinMax<String> wordsMinMax = MinMax.createMinMax(words);
        MinMax<String> wordsMinMax2 = MinMax.createMinMax(words2);
    }
}
