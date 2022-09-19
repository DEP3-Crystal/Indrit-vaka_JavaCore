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
            throw new IllegalArgumentException("Not valid Email.");
        this.email = email;
        this.chosenTimes = chosenTimes;
        String fullName = extractFullName(email);
        this.firstName = extractFirstName(fullName);
        this.lastName = extractLastName(fullName);
    }
    public Person(String email) {
        this(email, 0);
    }
    private Person(Builder builder){
        this.chosenTimes = builder.chosenTimes;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
    }

    private static String extractFullName(String email) {
        return email.split("@")[0];
    }

    private static String extractFirstName(String fullName) {
        String firstName;
        if (fullName.contains("."))
            firstName = fullName.split("\\.")[0];
        else {
            firstName = toPascalCasing(fullName);
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

    private static String extractLastName(String fullName) {
        String lastName;
        if (fullName.contains(".")) {
            lastName = fullName.split("\\.")[1];
            lastName = toPascalCasing(lastName);
        } else {
            lastName = fullName;
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

    public static class Builder{
        private String firstName;
        private String lastName;
        private String email;
        private int chosenTimes;

        private void setEmail(String email) {
            this.email = email;
            String fullName = extractFullName(email);
            this.firstName = extractFirstName(fullName);
            this.lastName = extractLastName(fullName);
        }

        public Builder email(String email){
            setEmail(email);
            return this;
        }
        public Builder chosenTimes(int chosenTimes){
            this.chosenTimes = chosenTimes;
            return this;
        }
        public Person build(){
            return new Person(this);
        }
    }
}


