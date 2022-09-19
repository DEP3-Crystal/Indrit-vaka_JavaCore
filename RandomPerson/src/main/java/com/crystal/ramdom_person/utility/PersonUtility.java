package com.crystal.ramdom_person.utility;

import com.crystal.ramdom_person.dao.DataFromFile;
import com.crystal.ramdom_person.dao.DataSource;

import java.util.Scanner;

public class PersonUtility {
    public static boolean FIRST_RUN = true;
    public static DataSource dataSource = new DataFromFile();
    public static Scanner scanner = new Scanner(System.in);
    public static String choosesPath = "RandomPerson/src/main/resources/chosen.csv";
    public static String peoplePath = "RandomPerson/src/main/resources/people.csv";
}
