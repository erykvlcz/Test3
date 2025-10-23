package pl.kurs.ex1.models;

import java.util.Comparator;

public enum Size {
    L(3), M(2), S(1), XL(4);

    private final int priority;

    Size(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Size{" +
                "priority=" + priority +
                '}';
    }
}
