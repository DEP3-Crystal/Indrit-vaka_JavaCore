package com.crystal.ramdom_person.dao;

import com.crystal.ramdom_person.model.Person;
import com.crystal.ramdom_person.utility.PersonUtility;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
public class DataFromFile implements DataSource {

    public LinkedList<Person> loadChosen() {
        return loadChosen(false);
    }

    public LinkedList<Person> loadChosen(boolean loadTimeOfChosen) {
        //TODO Invoke the constructor to load the names
        try (CSVParser csvParser = CSVParser.parse(new FileReader(PersonUtility.choosesPath),
                CSVFormat.Builder.create()
                        .setSkipHeaderRecord(true)
                        .setHeader("email", "chosen-times")
                        .build())) {
            return csvParser.stream().map(record ->
                    new Person.Builder()
                            .email(record.get("email"))
                            .chosenTimes(loadTimeOfChosen ? Integer.parseInt(record.get("chosen-times")) : 0)
                            .build()).collect(Collectors.toCollection(LinkedList::new));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> loadPeople() {
        InputStream inputStream = DataSource.class.getResourceAsStream("/people.csv");

        try (CSVParser csvParser = CSVParser.parse(new InputStreamReader(inputStream), CSVFormat.DEFAULT)) {

            return csvParser.stream()
                    .map(record -> new Person(record.get(0)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void saveChosen(List<Person> chosen) {


        try (CSVPrinter printer = new CSVPrinter(new FileWriter(PersonUtility.choosesPath), CSVFormat.DEFAULT)) {
            printer.printRecord("email","chosen-times");
            for (Person person : chosen) {
                printer.printRecord(person.getEmail(), person.getChosenTimes());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("There was a problem during saving the file: " + ex.getMessage());
        }
    }

    public void savePeople(List<Person> people) {
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(PersonUtility.peoplePath), CSVFormat.DEFAULT)) {
            for (Person person : people) {
                printer.printRecord(person.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was a problem during saving the file: " + e.getMessage());
        }
    }
}
