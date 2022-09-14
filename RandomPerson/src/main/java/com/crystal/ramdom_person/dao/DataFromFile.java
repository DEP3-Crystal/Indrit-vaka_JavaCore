package com.crystal.ramdom_person.dao;

import com.crystal.ramdom_person.io.OutputManager;
import com.crystal.ramdom_person.model.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataFromFile implements DataSource {

    public LinkedList<Person> loadChosen() {
        //check if we have some data
        //clean the chooses
        LinkedList<Person> people = new LinkedList<>();
        int line = 1;
        try (Stream<String> lines = Files.lines(Path.of("RandomPerson/src/main/resources/chosen.txt"))) {
            people = lines
                    .map(Person::new)
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (Exception e) {
            OutputManager.showErrMessage("There was a problem while we try to load emails at line: " + line + " " + e.getMessage());
        }
        return people;
    }

    public List<Person> loadPeople() {
        Path path = Path.of("RandomPerson/src/main/resources/people.txt");
        List<Person> people = new ArrayList<>();
        try (Stream<String> lines = Files.lines(path)) {
            people = lines.map(Person::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return people;
    }

    public void saveChosen(List<Person> chosen) {
        try (FileWriter file = new FileWriter("RandomPerson/src/main/resources/chosen.txt")) {
            String content;
            content = chosen.stream().map(p -> p.getEmail() + "\n").collect(Collectors.joining());
            file.write(content);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was a problem during saving the file: " + e.getMessage());
        }
    }

    public void savePeople(List<Person> people) {
        try (FileWriter file = new FileWriter("RandomPerson/src/main/resources/people.txt")) {
            String content = people.stream()
                    .map(s -> s.getEmail() + "\n")
                    .collect(Collectors.joining());
            file.write(content);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was a problem during saving the file: " + e.getMessage());
        }
    }
}
