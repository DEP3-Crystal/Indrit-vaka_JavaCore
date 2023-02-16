package com.crystal.ramdom_person.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PersonTest {
    @Test
    public void extractPersonFirstName() {
        Person person = new Person("florin-adrian.dumitru@crystal-system.eu");
        Assertions.assertEquals("Florin Adrian", person.getFirstName());

        Person p2 = new Person("florin-adrian.dumitru@crystal-system.eu");
        Assertions.assertNotEquals("Florin-Adrian", p2.getFirstName());
    }

    @Test
    public void extractPersonLastName() {
        Person person = new Person("florin-adrian.dumitru@crystal-system.eu");
        Assertions.assertEquals("Dumitru", person.getLastName());
    }

}