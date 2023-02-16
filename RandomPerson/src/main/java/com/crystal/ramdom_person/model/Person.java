package com.crystal.ramdom_person.model;

import com.crystal.ramdom_person.validator.Validator;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private int chosenTimes;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Person(String email, int chosenTimes) {
        if (!Validator.validEmail(email))
            throw new IllegalArgumentException("Not valid Email: " + email);
        this.email = email;
        this.chosenTimes = chosenTimes;
        this.firstName = extractFirstName(email);
        this.lastName = extractLastName(email);
    }

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person(String email) {
        this(email, 0);
    }

    private Person(Builder builder) {
        this.chosenTimes = builder.chosenTimes;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
    }

    private static String extractFirstName(String email) {
        String firstName;
        if (email.contains("."))
            firstName = email.split("\\.")[0];
        else {
            firstName = toPascalCasing(email.split("@")[0]);
        }
        if (firstName.contains("-")) {
            firstName = Stream.of(firstName)
                    .map(n -> {
                        String[] parts = n.split("-");
                        return toPascalCasing(parts[0]) + " " +
                                toPascalCasing(parts[1]);
                    })
                    .collect(Collectors.joining());
        }
        return firstName;
    }

    private static String extractLastName(String email) {
        String lastName;
        email = email.split("@")[0];
        if (email.contains(".")) {
            lastName = email.split("\\.")[1];
            lastName = toPascalCasing(lastName);
        } else {
            lastName = email;
        }
        return lastName;
    }

    private static String toPascalCasing(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName +
                " {" + email + "}";
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private int chosenTimes;

        private void setEmail(String email) {
            if(!Validator.validEmail(email))
                throw new IllegalArgumentException("Not valid email: " + email);
            this.email = email;
            this.firstName = extractFirstName(email);
            this.lastName = extractLastName(email);
        }

        public Builder email(String email) {
            setEmail(email);
            return this;
        }
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }public Builder lastName(String lastName) {
           this.lastName = lastName;
            return this;
        }

        public Builder chosenTimes(int chosenTimes) {
            this.chosenTimes = chosenTimes;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}


