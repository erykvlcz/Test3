package pl.kurs.ex1.models;

import java.io.*;
import java.util.*;

public class Wardrobe implements Serializable, Iterable<Clothing> {
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "wardrobe.clothes";
    private Node firstNode;
    private Node lastNode;
    private int size;

    public void addClothes(Clothing... newClothes) {
        for (Clothing c : newClothes) {
            Node newNode = new Node(c);
            if(firstNode == null) {
                firstNode = newNode;
                lastNode = newNode;
            } else {
                lastNode.next = newNode;
                lastNode = newNode;
            }
            size++;
        }
    }

    public List<Clothing> getClothes() {
        if(firstNode == null) throw new EmptyListException("Wardrobe is empty!");
        List<Clothing> list = new ArrayList<>();
        Iterator<Clothing> wardrobeIterator = this.iterator();
        while (wardrobeIterator.hasNext()) {
            list.add(wardrobeIterator.next());
        }
        return list;
    }

    public void saveWardrobeClothesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendClothesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Wardrobe loadedWardrobe = (Wardrobe) ois.readObject();
            for (Clothing loadedClothing : loadedWardrobe) {
                if(!isClothingAlreadyInWardrobe(loadedClothing)) {
                    addClothes(loadedClothing);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean isClothingAlreadyInWardrobe(Clothing loadedClothing) {
        Iterator<Clothing> wardrobeIterator = this.iterator();
        while (wardrobeIterator.hasNext()) {
            if(wardrobeIterator.next().equals(loadedClothing)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Wardrobe{" + '}';
    }

    @Override
    public Iterator<Clothing> iterator() {
        return new WardrobeIterator();
    }

    private class WardrobeIterator implements Iterator<Clothing> {
        private Node current = firstNode;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Clothing next() {
            if(current == null) {
                throw new NoSuchElementException();
            }
            Clothing value = current.item;
            current = current.next;
            return value;
        }
    }

    private static class Node implements Serializable {
        private static final long serialVersionUID = 1L;
        private Clothing item;
        private Node next;

        public Node(Clothing item) {
            this.item = item;
        }
    }
}
