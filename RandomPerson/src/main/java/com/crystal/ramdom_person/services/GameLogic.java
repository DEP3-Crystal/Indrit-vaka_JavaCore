package com.crystal.ramdom_person.services;

import com.crystal.ramdom_person.model.Person;

import java.util.LinkedList;
import java.util.List;

public interface GameLogic {
    Person choseOne(List<Person> people, LinkedList<Person> chosen);
}
