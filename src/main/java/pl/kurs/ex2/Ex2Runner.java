package pl.kurs.ex2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.*;
import java.util.*;

public class Ex2Runner {
    public static void main(String[] args) throws IOException {
        List<File> myJavaFiles = new ArrayList<>();
        String myDirectory = "C:\\Users\\kowal\\Desktop\\projekty";
        String yourDirectory = "";
        findJavaFiles(new File(myDirectory),myJavaFiles);
        sortFilesByDayOfWeek(myJavaFiles);

        System.out.println("Files number = " + myJavaFiles.size());

//MONDAY -> 90
//TUESDAY -> 106
//WEDNESDAY -> 203 //<- zajęcia ze słoniem
//THURSDAY -> 110
//FRIDAY -> 85
//SATURDAY -> 173
//SUNDAY -> 209
//Files number = 976

    }

    private static void sortFilesByDayOfWeek(List<File> myJavaFiles) {
        Map<DayOfWeek, Integer> dayCounterMap = new LinkedHashMap<>();
        for (DayOfWeek value : DayOfWeek.values()) {
            dayCounterMap.put(value, 0);
        }

        groupAndAddDaysToMap(myJavaFiles, dayCounterMap);

        Set<Map.Entry<DayOfWeek, Integer>> entrySet = dayCounterMap.entrySet();
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter("summary.txt"));
        ) {
        for (Map.Entry<DayOfWeek, Integer> e : entrySet) {
            String line = e.getKey() + " -> " + e.getValue();
            System.out.println(line);
            bw.write(line);
            bw.newLine();
        }
        bw.write("Files number = " + myJavaFiles.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void groupAndAddDaysToMap(List<File> myJavaFiles, Map<DayOfWeek, Integer> dayCounterMap) {
        for (File myFile : myJavaFiles) {
            Path path = myFile.toPath();
            BasicFileAttributes basicFileAttributes = null;
            try {
                basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            FileTime creationTime = basicFileAttributes.creationTime();
            LocalDate date = creationTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            dayCounterMap.put(dayOfWeek,dayCounterMap.get(dayOfWeek) + 1);
        }
    }

    static void findJavaFiles(File directory, List<File> files){
        if(!directory.isDirectory()){
            throw new RuntimeException("Wskazany plik nie jest folderem!");
        }

        File[] array = directory.listFiles();
        for (File f : array) {
            if (f.isDirectory()) {
                findJavaFiles(f, files);
            }else if(f.getName().endsWith(".java")){
                files.add(f);
            }
        }
    }
}
