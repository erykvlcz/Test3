package pl.kurs.ex1.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Wardrobe implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "wardrobe.clothes";
    private List<Clothes> clothes = new ArrayList<>();

    public Wardrobe() {
    }

    public Wardrobe(List<Clothes> clothes) {
        this.clothes = clothes;
    }

    public void addClothes(Clothes...newClothes){
        for (Clothes c : newClothes) {
            if(!clothes.contains(c)){
                this.clothes.add(c);
            }
        }
    }

    public void setClothes(List<Clothes> clothes) {
        this.clothes = clothes;
    }

    public List<Clothes> getClothes(){
        return clothes;
    }

    public void saveWardrobeClothesToFile(){
        if(clothes.isEmpty() || clothes == null){
            System.out.println("No clothes to save");
            return;
        }
        try (
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(clothes);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadWardrobeClothesFromFile(){
        List<Clothes> loadedClothes = null;
        try (
                FileInputStream fis = new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            loadedClothes = (List<Clothes>) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();

        }
        addClothes(loadedClothes.toArray(new Clothes[0]));
    }

    @Override
    public String toString() {
        return "Wardrobe{" +
                "clothes=" + clothes +
                '}';
    }


}
