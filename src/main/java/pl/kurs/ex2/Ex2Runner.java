package pl.kurs.ex2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Ex2Runner {
    public static void main(String[] args) throws IOException {
        String myDirectory = "C:\\Users\\kowal\\Desktop\\projekty";
        String yourDirectory = "";

        List<File> myJavaFiles = new ArrayList<>();
        findJavaFiles(new File(myDirectory),myJavaFiles);
        myJavaFiles.stream()
                .map(f -> getCreationDayOfWeekFromFile(f))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
        System.out.println("Files number = " + myJavaFiles.size());

//        MONDAY=92
//        TUESDAY=114
//        WEDNESDAY=210 <- zajęcia ze Słoniem
//        THURSDAY=119
//        FRIDAY=88
//        SATURDAY=183
//        SUNDAY=223
//        Files number = 1029

    }



    private static DayOfWeek getCreationDayOfWeekFromFile(File file){
        DayOfWeek dayOfWeek = null;
        try {
            BasicFileAttributes attr = Files.readAttributes(Paths.get(file.getPath()), BasicFileAttributes.class);
            LocalDateTime convertedFileTime = LocalDateTime.ofInstant(attr.creationTime().toInstant(), ZoneId.systemDefault());
            dayOfWeek = convertedFileTime.getDayOfWeek();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return dayOfWeek;

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
