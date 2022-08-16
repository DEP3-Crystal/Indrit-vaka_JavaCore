package com.crystal.ramdom_person.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataFromJson {
    public static String[] loadChosen(){
        //check if we have some data
        File file = new File("data.txt");
        //clean the chooses
        List<String> people = new ArrayList<>();
        if (file.exists())
        {
            try
            {
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine())
                {
                    people.add(reader.nextLine());
                }
            } catch (Exception e)
            {
                System.out.println("There was a problem while we try to read : " + e.getMessage());
            }
        }
        return people.toArray(new String[0]);
    }
    public static String[] loadPeople(){
        Path path = Path.of("RandomPerson/src/main/resources/people.txt");
        List<String> people = new ArrayList<>();
        try(Stream<String> lines = Files.lines(path)){
            people = lines.collect(Collectors.toList());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return people.toArray(new String[0]);
    }
    public static void saveChosen(List<String> chosen)
    {
        try(FileWriter file = new FileWriter("RandomPerson/src/main/resources/data.txt");)
        {
            String content;
            content = chosen.stream().map(s-> s+"\n").collect(Collectors.joining());
            file.write(content);
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("There was a problem during saving the file: " + e.getMessage());
        }
    }
}
