package com.crystal.ramdom_person.model;

import com.crystal.ramdom_person.validator.Validator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Person {
    private String firstName;
    private String lastName;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Person(String email) {
        if (!Validator.validEmail(email))
            throw new IllegalArgumentException("Not valid Email.");
        this.email = email;
        this.firstName = extractFirstName();
        this.lastName = extractLastName();
    }

    private String extractFirstName() {
        String name = email.split("@")[0];

        if (name.contains(".")) {
            String firstName = name.split("\\.")[0];
            name = toPascalCasing(firstName);
        }
        if (name.contains("-")) {
            name = Stream.of(name)
                    .map(n -> {
                        String[] parts = n.split("-");
                        return toPascalCasing(parts[0]) + " " +
                                toPascalCasing(parts[1]);
                    })
                    .collect(Collectors.joining());
        }
        return name;
    }

    private String extractLastName() {
        String name = email.split("@")[0];

        if (name.contains(".")) {
            String lastName = name.split("\\.")[1];
            name = toPascalCasing(lastName);
        }
        if (name.contains("-")) {
            name = Stream.of(name)
                    .map(n -> {
                        String[] parts = n.split("-");
                        return toPascalCasing(parts[0]) + " " +
                                toPascalCasing(parts[1]);
                    })
                    .collect(Collectors.joining());
        }
        return name;
    }

    private String toPascalCasing(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName +
                " {" + email + "}";
    }
}
